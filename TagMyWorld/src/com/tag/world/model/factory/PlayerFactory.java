package com.tag.world.model.factory;

import java.util.List;

import com.tag.world.model.Player;

public interface PlayerFactory {
	// public Player getPlayer(long player_id);

	// public Player getPlayer(String player_id);

	public List<Player> getPlayers(long... player_id);
}
