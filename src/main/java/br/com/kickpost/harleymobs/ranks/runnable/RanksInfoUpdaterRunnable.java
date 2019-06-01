package br.com.kickpost.harleymobs.ranks.runnable;

import java.util.HashMap;

import org.bukkit.scheduler.BukkitRunnable;

import com.google.common.collect.Maps;

import br.com.kickpost.harleymobs.HarleyMobs;
import br.com.kickpost.harleymobs.ranks.factory.RankPlayer;
import br.com.kickpost.harleymobs.ranks.mysql.RanksInfoStorageManager;

public class RanksInfoUpdaterRunnable extends BukkitRunnable {

	protected static HashMap<Integer, RankPlayer> player_by_position = Maps.newHashMap();

	public RanksInfoUpdaterRunnable() {
		runTaskTimerAsynchronously(HarleyMobs.getPlugin(), 0L, 60 * 20L);
	}

	@Override
	public void run() {
		player_by_position = new RanksInfoStorageManager().getPlayers();
	}

	public static final HashMap<Integer, RankPlayer> getPlayers() {
		return player_by_position;
	}

}
