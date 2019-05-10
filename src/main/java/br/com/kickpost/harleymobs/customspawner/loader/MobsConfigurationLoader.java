package br.com.kickpost.harleymobs.customspawner.loader;

import org.bukkit.entity.*;
import com.google.common.collect.*;
import br.com.kickpost.harleymobs.*;
import org.bukkit.configuration.*;
import java.util.*;

public class MobsConfigurationLoader {
	protected static final HashMap<EntityType, String> ENTITY_TRANSLATED_NAME = Maps.newHashMap();

	public MobsConfigurationLoader() {
		this.setup();
	}

	private void setup() {
		final ConfigurationSection SECTION = HarleyMobs.getPlugin().getConfig().getConfigurationSection("Mobs");
		final Set<String> KEYS = SECTION.getKeys(false);
		for (final String key : KEYS) {
			MobsConfigurationLoader.ENTITY_TRANSLATED_NAME.put(EntityType.valueOf(key),
					SECTION.getString(String.valueOf(key) + ".Nome"));
		}
	}

	public static final String getName(final EntityType entityType) {
		return MobsConfigurationLoader.ENTITY_TRANSLATED_NAME.containsKey(entityType)
				? MobsConfigurationLoader.ENTITY_TRANSLATED_NAME.get(entityType)
				: null;
	}

	public static final EntityType get(final String argument) {
		if (MobsConfigurationLoader.ENTITY_TRANSLATED_NAME.entrySet().stream()
				.anyMatch(c -> c.getValue().equalsIgnoreCase(argument))) {
			return MobsConfigurationLoader.ENTITY_TRANSLATED_NAME.entrySet().stream()
					.filter(c -> c.getValue().equalsIgnoreCase(argument)).findFirst().get().getKey();
		}
		return null;
	}
}
