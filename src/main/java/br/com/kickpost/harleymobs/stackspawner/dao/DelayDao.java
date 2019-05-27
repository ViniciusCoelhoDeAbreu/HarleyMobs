package br.com.kickpost.harleymobs.stackspawner.dao;

import java.util.UUID;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import org.bukkit.entity.Player;

public class DelayDao {

	protected static final WeakHashMap<UUID, Long> DELAY_BY_UUID = new WeakHashMap<>();

	public static void put(Player player) {
		DELAY_BY_UUID.put(player.getUniqueId(), System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(20));
	}

	public static long get(Player player) {
		return TimeUnit.MILLISECONDS.toSeconds(DELAY_BY_UUID.get(player.getUniqueId()) - System.currentTimeMillis());
	}

	public static boolean has(Player player) {
		return DELAY_BY_UUID.containsKey(player.getUniqueId())
				&& DELAY_BY_UUID.get(player.getUniqueId()) > System.currentTimeMillis();
	}

	public static void remove(Player player) {
		DELAY_BY_UUID.remove(player.getUniqueId());
	}
}
