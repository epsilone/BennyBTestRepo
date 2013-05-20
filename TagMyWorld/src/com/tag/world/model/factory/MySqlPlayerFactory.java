package com.tag.world.model.factory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.tag.world.DatabaseConnection;
import com.tag.world.model.Player;

public class MySqlPlayerFactory implements PlayerFactory {

	public static final String GET_PLAYER = "SELECT id, firstName, lastName, installSource, installDate, flags, cash, xp, lastCollectedFlag FROM player WHERE id=%d";
	public static final String GET_PLAYERS = "SELECT id, firstName, lastName, installSource, installDate, flags, cash, xp, lastCollectedFlag FROM player WHERE id=%d";

	public Player getPlayer(long player_id) {
		Connection connection = DatabaseConnection.getConnection();
		Player player = null;
		try {
			Statement stmt = connection.createStatement();
			System.err.println(String.format(GET_PLAYER, player_id));
			ResultSet rs = stmt.executeQuery(String.format(GET_PLAYER,
					player_id));
			if (rs.next()) {
				player = new Player(rs.getLong(1), rs.getString(2),
						rs.getString(3), rs.getString(4), rs.getDate(5),
						rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getDate(9));
			}
		} catch (Exception e) {

		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return player;
	}

	@Override
	public List<Player> getPlayers(long... player_id) {

		return null;
	}
}
