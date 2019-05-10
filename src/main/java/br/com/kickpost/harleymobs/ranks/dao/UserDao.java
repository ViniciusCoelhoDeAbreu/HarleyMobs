package br.com.kickpost.harleymobs.ranks.dao;

import java.util.*;
import org.bukkit.entity.*;
import br.com.kickpost.harleymobs.ranks.factory.*;

public class UserDao {
	protected static final WeakHashMap<UUID, User> USER_BY_UUID = new WeakHashMap<>();

	public static void put(final Player player, final Rank rank, final double value) {
		UserDao.USER_BY_UUID.put(player.getUniqueId(), new User(player, rank, value));
	}

	public static User get(final Player player) {
		return UserDao.USER_BY_UUID.get(player.getUniqueId());
	}

	public static boolean contains(final Player player) {
		return UserDao.USER_BY_UUID.containsKey(player.getUniqueId());
	}

	public static void remove(final Player player) {
		UserDao.USER_BY_UUID.remove(player.getUniqueId());
	}
}
