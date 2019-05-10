package br.com.kickpost.harleymobs.ranks;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

import br.com.kickpost.harleymobs.HarleyMobs;
import br.com.kickpost.harleymobs.factory.Main;
import br.com.kickpost.harleymobs.ranks.command.RanksCommand;
import br.com.kickpost.harleymobs.ranks.command.RankupCommand;
import br.com.kickpost.harleymobs.ranks.listener.onInventoryClickListener;
import br.com.kickpost.harleymobs.ranks.listener.onPlayerChatListener;
import br.com.kickpost.harleymobs.ranks.listener.onPlayerJoinListener;
import br.com.kickpost.harleymobs.ranks.listener.onPlayerQuitListener;
import br.com.kickpost.harleymobs.ranks.listener.onRankActionListener;
import br.com.kickpost.harleymobs.ranks.listener.onRankActionPreventListener;
import br.com.kickpost.harleymobs.ranks.loader.PlantationConfigurationLoader;
import br.com.kickpost.harleymobs.ranks.loader.RanksConfigurationLoader;

public class RanksMain extends Main {
	@Override
	public void initialize() {
		new RanksConfigurationLoader();
		new PlantationConfigurationLoader();
	}

	@Override
	public void registerCommands() {
		HarleyMobs.getPlugin().getCommand("rankup").setExecutor(new RankupCommand());
		HarleyMobs.getPlugin().getCommand("ranks").setExecutor(new RanksCommand());
	}

	@Override
	public void registerListeners() {
		final PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new onRankActionListener(), HarleyMobs.getPlugin());
		pm.registerEvents(new onPlayerJoinListener(), HarleyMobs.getPlugin());
		pm.registerEvents(new onPlayerQuitListener(), HarleyMobs.getPlugin());
		pm.registerEvents(new onInventoryClickListener(), HarleyMobs.getPlugin());
		pm.registerEvents(new onRankActionPreventListener(), HarleyMobs.getPlugin());
		pm.registerEvents(new onPlayerChatListener(), HarleyMobs.getPlugin());
	}
}
