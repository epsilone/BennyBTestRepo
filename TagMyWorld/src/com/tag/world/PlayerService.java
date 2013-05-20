package com.tag.world;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mongodb.DBObject;
import com.tag.world.model.factory.MongoPlayerFactory;
import com.tag.world.model.factory.MongoPlayerFactory.Fields;

/**
 * Servlet implementation class PlayerService
 */
@WebServlet(description = "service to get players information", urlPatterns = { "/Player" })
public class PlayerService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PlayerService() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String a = request.getParameter("player_id");
		String b = request.getParameter("facebook_id");
		String result = "need parameter player_id or facebook_id";
		Fields key = null;
		Object value = null;
		if (a != null) {
			value = a;
			key = Fields.NICKNAME;
		} else if (b != null) {
			try {
				value = Long.valueOf(b);
				key = Fields.FACEBOOK_ID;
			} catch (Exception e) {
				result = "invalid id";
			}
		}
		if (key != null) {
			result = getResultByPlayerId(key, value);
		}

		if (result == null)
			result = "unknown player";

		PrintWriter scribe = response.getWriter();
		scribe.append(result);
	}

	private String getResultByPlayerId(Fields key, Object a) {
		String rtrn = String.format("a player %s (unknown)", a);
		try {
			MongoPlayerFactory factory = new MongoPlayerFactory();
			DBObject p = factory.getPlayer(key, a);
			rtrn = factory.serializePlayer(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtrn;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		new PlayerServiceInstall().install(request, response);
	}

}
