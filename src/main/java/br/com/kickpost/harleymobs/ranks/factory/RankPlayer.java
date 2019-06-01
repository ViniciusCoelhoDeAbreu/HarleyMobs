package br.com.kickpost.harleymobs.ranks.factory;

import org.bukkit.OfflinePlayer;

public class RankPlayer {

	private OfflinePlayer player;
	private Rank rank;

	public RankPlayer(OfflinePlayer player, Rank rank) {
		this.player = player;
		this.rank = rank;
	}

	public OfflinePlayer getPlayer() {
		return player;
	}

	public Rank getRank() {
		return rank;
	}
}
