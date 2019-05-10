package br.com.kickpost.harleymobs.stackspawner.dao;

import java.util.*;
import org.bukkit.*;
import br.com.kickpost.harleymobs.stackspawner.factory.*;
import com.google.common.collect.*;
import br.com.kickpost.harleymobs.stackspawner.mysql.*;

public class SpawnerDao {
	protected static final HashMap<Location, Spawner> SPAWNER_BY_LOCATION = Maps.newHashMap();

	public static void put(final Location location, final Spawner spawner) {
		SpawnerDao.SPAWNER_BY_LOCATION.put(location, spawner);
	}

	public static boolean contains(final Location location) {
		return SpawnerDao.SPAWNER_BY_LOCATION.containsKey(location);
	}

	public static Spawner get(final Location location) {
		return SpawnerDao.SPAWNER_BY_LOCATION.get(location);
	}

	public static void remove(final Location location) {
		new SpawnerStorageManager().remove(get(location));
		SpawnerDao.SPAWNER_BY_LOCATION.remove(location);
	}
}
