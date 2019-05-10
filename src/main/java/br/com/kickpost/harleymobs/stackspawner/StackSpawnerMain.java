package br.com.kickpost.harleymobs.stackspawner;

import org.bukkit.Bukkit;

import br.com.kickpost.harleymobs.HarleyMobs;
import br.com.kickpost.harleymobs.factory.Main;
import br.com.kickpost.harleymobs.stackspawner.listeners.onSpawnerBreakListener;
import br.com.kickpost.harleymobs.stackspawner.listeners.onSpawnerPutListener;
import br.com.kickpost.harleymobs.stackspawner.mysql.SpawnerStorageManager;

public class StackSpawnerMain extends Main {
	@Override
	public void initialize() {
		new SpawnerStorageManager().load();
	}

	@Override
	public void registerCommands() {
	}

	@Override
	public void registerListeners() {
		Bukkit.getPluginManager().registerEvents(new onSpawnerPutListener(), HarleyMobs.getPlugin());
		Bukkit.getPluginManager().registerEvents(new onSpawnerBreakListener(), HarleyMobs.getPlugin());
	}
}
