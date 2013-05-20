package com.tag.world;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mongodb.DBObject;
import com.tag.world.model.factory.MongoPlayerFactory;
import com.tag.world.model.factory.MongoPlayerFactory.Fields;

/**
 * Servlet implementation class PlayerServiceInstall
 */
@WebServlet("/PlayerServiceInstall")
public class PlayerServiceInstall extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PlayerServiceInstall() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		install(request, response);
	}

	public void install(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String a = request.getParameter("player_id");
		String source = request.getParameter("source");
		if (a == null) {
			response.getWriter()
					.append("need a player_id to create a player (you also can specified a source)");
			return;
		}
		MongoPlayerFactory factory = new MongoPlayerFactory();
		if (factory.containsPlayer(Fields.NICKNAME, a)) {
			response.getWriter().append(
					String.format("player %s already exists", a));
			return;
		}

		DBObject player = factory.createPlayer(a, source);
		response.getWriter().append(factory.serializePlayer(player));
	}
}
