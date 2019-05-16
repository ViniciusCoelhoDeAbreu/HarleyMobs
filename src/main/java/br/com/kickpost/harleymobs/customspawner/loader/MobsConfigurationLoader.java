package br.com.kickpost.harleymobs.customspawner.loader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.EntityType;

import com.google.common.collect.Maps;

import br.com.kickpost.harleymobs.HarleyMobs;
import br.com.kickpost.harleymobs.customspawner.factory.CustomEntity;
import br.com.kickpost.harleymobs.customspawner.factory.Drop;
import br.com.kickpost.harleymobs.utils.ItemManager;

public class MobsConfigurationLoader {
	protected static final HashMap<EntityType, CustomEntity> ENTITY_TRANSLATED_NAME = Maps.newHashMap();

	public MobsConfigurationLoader() {
		this.setup();
	}

	private void setup() {
		final ConfigurationSection SECTION = HarleyMobs.getPlugin().getConfig().getConfigurationSection("Mobs");
		final Set<String> KEYS = SECTION.getKeys(false);
		for (final String key : KEYS) {
			MobsConfigurationLoader.ENTITY_TRANSLATED_NAME.put(EntityType.valueOf(key),
					new CustomEntity(SECTION.getString(key + ".Nome"), getDrops(SECTION, key)));
		}
	}

	private List<Drop> getDrops(ConfigurationSection section, String key) {
		final ConfigurationSection SECTION = section.getConfigurationSection(key + ".Drops");
		final Set<String> KEYS = SECTION.getKeys(false);

		List<Drop> drops = new ArrayList<>();

		for (String k : KEYS) {
			String name = k;
			ItemManager item = new ItemManager(SECTION.getString(k + ".Item"));

			drops.add(new Drop(name, item));
		}
		return drops;
	}

	public static final CustomEntity get(EntityType entityType) {
		return MobsConfigurationLoader.ENTITY_TRANSLATED_NAME.containsKey(entityType)
				? MobsConfigurationLoader.ENTITY_TRANSLATED_NAME.get(entityType)
				: null;
	}

	public static final String getName(EntityType entityType) {
		return MobsConfigurationLoader.ENTITY_TRANSLATED_NAME.containsKey(entityType)
				? MobsConfigurationLoader.ENTITY_TRANSLATED_NAME.get(entityType).getCustomName()
				: null;
	}

	public static final EntityType get(String argument) {
		if (MobsConfigurationLoader.ENTITY_TRANSLATED_NAME.entrySet().stream()
				.anyMatch(c -> c.getValue().getCustomName().equalsIgnoreCase(argument))) {
			return MobsConfigurationLoader.ENTITY_TRANSLATED_NAME.entrySet().stream()
					.filter(c -> c.getValue().getCustomName().equalsIgnoreCase(argument)).findFirst().get().getKey();
		}
		return null;
	}
}
