/*************************************************************************************
 * Copyright (c) 2004 Actuate Corporation and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Actuate Corporation - Initial implementation.
 ************************************************************************************/

package org.eclipse.birt.report.servlet;

import java.io.IOException;
import java.util.Iterator;
import java.util.Objects;
import java.util.Scanner;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.axis.transport.http.AxisServlet;
import org.eclipse.birt.core.exception.BirtException;
import org.eclipse.birt.report.IBirtConstants;
import org.eclipse.birt.report.context.IContext;
import org.eclipse.birt.report.exception.ViewerException;
import org.eclipse.birt.report.presentation.aggregation.IFragment;
import org.eclipse.birt.report.resource.BirtResources;
import org.eclipse.birt.report.resource.ResourceConstants;
import org.eclipse.birt.report.session.IViewingSession;
import org.eclipse.birt.report.session.ViewingSessionUtil;
import org.eclipse.birt.report.soapengine.api.GetUpdatedObjects;
import org.eclipse.birt.report.soapengine.api.GetUpdatedObjectsResponse;
import org.eclipse.birt.report.soapengine.endpoint.BirtSoapBindingImpl;
import org.eclipse.birt.report.utility.ParameterAccessor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

abstract public class BirtSoapMessageDispatcherServlet extends AxisServlet {

	/**
	 * The JSON content type.
	 */
	public static final String JSON_CONTENT_TYPE = "application/json"; //$NON-NLS-1$

	/**
	 * TODO: what's this?
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Versioning.
	 */
	protected static boolean openSource = true;

	/**
	 * Viewer fragment references.
	 */
	protected IFragment viewer = null;
	protected IFragment run = null;

	/**
	 * Abstract methods.
	 */
	abstract protected void __init(ServletConfig config);

	abstract protected boolean __authenticate(HttpServletRequest request, HttpServletResponse response);

	abstract protected IContext __getContext(HttpServletRequest request, HttpServletResponse response)
			throws BirtException;

	abstract protected void __doGet(IContext context) throws ServletException, IOException, BirtException;

	abstract protected void __doPost(IContext context) throws ServletException, IOException, BirtException;

	abstract protected void __handleNonSoapException(HttpServletRequest request, HttpServletResponse response,
			Exception exception) throws ServletException, IOException;

	/**
	 * Check version.
	 *
	 * @return
	 */
	public static boolean isOpenSource() {
		return openSource;
	}

	/**
	 * Servlet init.
	 *
	 * @param config
	 * @exception ServletException
	 * @return
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		// Workaround for using axis bundle
		Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());

		super.init(config);
		ParameterAccessor.initParameters(config);
		BirtResources.setLocale(ParameterAccessor.getWebAppLocale());
		__init(config);
	}

	/**
	 * @see javax.servlet.http.HttpServlet#service(javax.servlet.ServletRequest,
	 *      javax.servlet.ServletResponse)
	 */
	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		// TODO: since eclipse Jetty doesn't support filter, set it here for
		// workaround
		if (req.getCharacterEncoding() == null) {
			req.setCharacterEncoding(IBirtConstants.DEFAULT_ENCODE);
		}

		// workaround for Jetty
		req.setAttribute("ServletPath", ((HttpServletRequest) req).getServletPath()); //$NON-NLS-1$

		super.service(req, res);
	}

	/**
	 * Handle HTTP GET method.
	 *
	 * @param request  incoming http request
	 * @param response http response
	 * @exception ServletException
	 * @exception IOException
	 * @return
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!__authenticate(request, response)) {
			return;
		}

		try {
			// create new session
			IViewingSession session = ViewingSessionUtil.createSession(request);
			session.lock();
			try {
				IContext context = __getContext(request, response);

				if (context.getBean().getException() != null) {
					__handleNonSoapException(request, response, context.getBean().getException());
				} else {
					__doGet(context);
				}
			} finally {
				session.unlock();
			}
		} catch (BirtException e) {
			__handleNonSoapException(request, response, e);
		}

	}

	/**
	 * Handle HTTP POST method.
	 *
	 * @param request  incoming http request
	 * @param response http response
	 * @exception ServletException
	 * @exception IOException
	 * @return
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!__authenticate(request, response)) {
			return;
		}

		// create SOAP URL with post parameters
		StringBuilder builder = new StringBuilder();
		Iterator it = request.getParameterMap().keySet().iterator();
		while (it.hasNext()) {
			String paramName = (String) it.next();
			if (paramName != null && paramName.startsWith("__")) //$NON-NLS-1$
			{
				String paramValue = ParameterAccessor.urlEncode(ParameterAccessor.getParameter(request, paramName),
						ParameterAccessor.UTF_8_ENCODE);
				builder.append("&" + paramName + "=" + paramValue); //$NON-NLS-1$//$NON-NLS-2$
			}
		}
		String soapURL = request.getRequestURL().toString();
		if (ParameterAccessor.getBaseURL() != null) {
			soapURL = ParameterAccessor.getBaseURL() + request.getContextPath() + request.getServletPath();
		}

		builder.deleteCharAt(0);
		soapURL += "?" + builder.toString(); //$NON-NLS-1$

		request.setAttribute("SoapURL", soapURL); //$NON-NLS-1$

		String requestType = request.getHeader(ParameterAccessor.HEADER_REQUEST_TYPE);
		boolean isJsonRestRequest = Objects.equals(JSON_CONTENT_TYPE, getContentType(request.getContentType()));
		boolean isSoapRequest = ParameterAccessor.HEADER_REQUEST_TYPE_SOAP.equalsIgnoreCase(requestType)
				&& !isJsonRestRequest;

		// refresh the current BIRT viewing session by accessing it
		IViewingSession session;

		// init context
		IContext context = null;
		try {
			session = ViewingSessionUtil.getSession(request);
			if (session == null && !isSoapRequest) {
				if (ViewingSessionUtil.getSessionId(request) == null) {
					session = ViewingSessionUtil.createSession(request);
				} else {
					// if session id passed through the URL, it means this request
					// was expected to run using a session that has already expired
					throw new ViewerException(
							BirtResources.getMessage(ResourceConstants.GENERAL_ERROR_NO_VIEWING_SESSION));
				}
			}
			context = __getContext(request, response);
		} catch (BirtException e) {
			// throw exception
			__handleNonSoapException(request, response, e);
			return;
		}

		try {
			if (session != null) {
				session.lock();
			}
			__doPost(context);

			if (isSoapRequest) {
				// Workaround for using axis bundle to invoke SOAP request
				Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());

				super.doPost(request, response);
			} else if (isJsonRestRequest) {
				handleJsonRestRequest(request, response);
			} else {
				try {
					if (context.getBean().getException() != null) {
						__handleNonSoapException(request, response, context.getBean().getException());
					} else {
						__doGet(context);
					}
				} catch (BirtException e) {
					__handleNonSoapException(request, response, e);
				}
			}
		} catch (BirtException e) {
			e.printStackTrace();
		} finally {
			if (session != null && !session.isExpired()) {
				session.unlock();
			}
		}
	}

	static String getContentType(String contentType) {
		if (contentType != null) {
			String[] contentTypeParts = contentType.split(";");
			if (contentTypeParts.length > 0) {
				return contentTypeParts[0].trim();
			}
		}

		return null;
	}

	static String extractPostRequestBody(HttpServletRequest request) throws IOException {
		if ("POST".equalsIgnoreCase(request.getMethod())) {
			Scanner s = new Scanner(request.getInputStream(), "UTF-8").useDelimiter("\\A");
			return s.hasNext() ? s.next() : "";
		}
		return "";
	}

	static void handleJsonRestRequest(HttpServletRequest request, HttpServletResponse response) {
		ObjectMapper objectMapper = new ObjectMapper();

		try {
			String bodyContent = extractPostRequestBody(request);
			if (!bodyContent.isBlank()) {
				JsonNode jsonRequest = objectMapper.readTree(bodyContent);
				String serviceOperation = jsonRequest.get("name").asText();
				if (serviceOperation.equals("GetUpdatedObjects")) {
					try {
						JsonNode jsonRequestData = jsonRequest.get("data");
						GetUpdatedObjects getUpdatedObjects = objectMapper.treeToValue(jsonRequestData,
								GetUpdatedObjects.class);
						BirtSoapBindingImpl test = new BirtSoapBindingImpl();
						GetUpdatedObjectsResponse updatedObjects = test.getUpdatedObjects(getUpdatedObjects);
						JsonNode valueToTree = objectMapper.valueToTree(updatedObjects);
						ObjectNode responseObject = objectMapper.createObjectNode();
						responseObject.put("name", updatedObjects.getClass().getSimpleName());
						responseObject.set("data", valueToTree);
						String writeValueAsString = objectMapper.writeValueAsString(responseObject);
						response.getWriter().print(writeValueAsString);
					} catch (Exception e) {
						ObjectNode responseObject = objectMapper.createObjectNode();
						ObjectNode errorNode = objectMapper.createObjectNode();
						errorNode.put("faultstring", e.getMessage());
						errorNode.put("faultcode", 501);
						ArrayNode detailArray = objectMapper.createArrayNode();
						for (StackTraceElement ste : e.getStackTrace()) {
							detailArray.add(ste.toString());
						}

						errorNode.set("detail", detailArray);
						responseObject.set("exception", errorNode);
						String writeValueAsString = objectMapper.writeValueAsString(responseObject);
						response.getWriter().print(writeValueAsString);
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
