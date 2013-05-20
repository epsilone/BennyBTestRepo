package com.tag.world;

import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

public abstract class DefaultTagMyWorldService extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected String checkParameter(String paramName, String cause,
			HttpServletRequest request, PrintWriter writter) {
		String param = request.getParameter(paramName);

		if (param == null) {
			if (writter != null)
				writter.append(String.format("need a %s %s", paramName, cause));
			return null;
		}
		return param;
	}

}
