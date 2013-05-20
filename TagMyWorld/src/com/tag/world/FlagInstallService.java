package com.tag.world;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FlagInstall
 */
@WebServlet("/FlagInstallService")
public class FlagInstallService extends DefaultTagMyWorldService {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FlagInstallService() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		install(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	public void install(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter scribe = response.getWriter();

		/* mandatory parameters*/
		String player_id = checkParameter("player_id", "to associate a flag",
				request, scribe);
		if (player_id == null)
			return;

		String lat = checkParameter("lat", "to know the lattitude", request,
				scribe);
		if (lat == null)
			return;

		String lng = checkParameter("lng", "to specified a longitude", request,
				scribe);
		if (lng == null)
			return;
		
		/* optionnal parameters*/
		String info = checkParameter("info", null, request, null);

		
	}

}
