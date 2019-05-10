package br.com.kickpost.harleymobs.ranks.loader;

import br.com.kickpost.harleymobs.*;
import org.bukkit.configuration.*;
import java.util.*;
import org.bukkit.*;

public class PlantationConfigurationLoader {
	protected static final HashMap<String, Integer> SIZE_BY_PLANTATION = new HashMap<>();

	public PlantationConfigurationLoader() {
		this.setup();
	}

	private void setup() {
		final ConfigurationSection SECTION = HarleyMobs.getPlugin().getConfig().getConfigurationSection("Plantacoes");
		final Set<String> KEYS = (Set<String>) SECTION.getKeys(false);
		for (final String plantation : KEYS) {
			final String key = plantation;
			final int size = SECTION.getInt(String.valueOf(key) + ".Tamanho");
			PlantationConfigurationLoader.SIZE_BY_PLANTATION.put(plantation.toLowerCase(), size);
		}
	}

	public static final int getSize(final Material material) {
		return PlantationConfigurationLoader.SIZE_BY_PLANTATION.containsKey(material.name().toLowerCase())
				? PlantationConfigurationLoader.SIZE_BY_PLANTATION.get(material.name().toLowerCase())
				: -1;
	}
}
