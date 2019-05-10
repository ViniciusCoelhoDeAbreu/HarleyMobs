package br.com.kickpost.harleymobs.customspawner;

import br.com.kickpost.harleymobs.HarleyMobs;
import br.com.kickpost.harleymobs.customspawner.command.SpawnerGiveCommand;
import br.com.kickpost.harleymobs.customspawner.loader.MobsConfigurationLoader;
import br.com.kickpost.harleymobs.factory.Main;

public class CustomSpawnerMain extends Main {
	@Override
	public void initialize() {
		new MobsConfigurationLoader();
	}

	@Override
	public void registerCommands() {
		HarleyMobs.getPlugin().getCommand("mgive").setExecutor(new SpawnerGiveCommand());
	}

	@Override
	public void registerListeners() {
	}
}
