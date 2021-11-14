package org.eclipse.birt.report.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.birt.report.service.BirtReportServiceFactory;
import org.eclipse.birt.report.service.BirtViewerReportService;
import org.eclipse.birt.report.service.api.InputOptions;
import org.eclipse.birt.report.service.api.ReportServiceException;

import com.google.gson.Gson;

/**
 * @since 3.3
 *
 */
public class ViewerRestServlet extends HttpServlet {

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);

		BirtReportServiceFactory.init(new BirtViewerReportService(config.getServletContext()));
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Gson gson = new Gson();
		Map<String, String[]> splitQuery = req.getParameterMap();

		InputOptions inputOptions = new InputOptions();

		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {

			BirtReportServiceFactory.getReportService().renderReport(splitQuery.get("__report")[0], 0, null,
					inputOptions, out);
		} catch (ReportServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String test = new String(out.toByteArray(), StandardCharsets.UTF_8);
		System.out.print(test);
	}

	public static Map<String, List<String>> splitQuery(String uriAsString) throws Exception {

		URI uri = new URI(uriAsString);

		final Map<String, List<String>> query_pairs = new LinkedHashMap<String, List<String>>();
		final String[] pairs = uri.getQuery().split("&");
		for (String pair : pairs) {
			final int idx = pair.indexOf("=");
			final String key = idx > 0 ? URLDecoder.decode(pair.substring(0, idx), "UTF-8") : pair;
			if (!query_pairs.containsKey(key)) {
				query_pairs.put(key, new LinkedList<String>());
			}
			final String value = idx > 0 && pair.length() > idx + 1
					? URLDecoder.decode(pair.substring(idx + 1), "UTF-8")
					: null;
			query_pairs.get(key).add(value);
		}
		return query_pairs;
	}

}
