package org.eclipse.birt.report.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.util.resource.Resource;

/**
 * @since 3.3
 *
 */
public class StaticServlet extends DefaultServlet {

	protected String pathPrefix = "/static";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//		RequestDispatcher rd = getServletContext().getNamedDispatcher("default");
//
//		HttpServletRequest wrapped = new HttpServletRequestWrapper(request) {
//			public String getServletPath() {
//				return "";
//			}
//		};

//		rd.forward(wrapped, response);

		super.doGet(request, response);
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		if (config.getInitParameter("pathPrefix") != null) {
			pathPrefix = config.getInitParameter("pathPrefix");
		}
	}

	@Override
	public Resource getResource(String pathInContext) {
		return super.getResource(pathInContext);
	}

}
