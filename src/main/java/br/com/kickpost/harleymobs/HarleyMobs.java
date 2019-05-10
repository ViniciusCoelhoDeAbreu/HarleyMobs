package br.com.kickpost.harleymobs;

import org.bukkit.plugin.java.JavaPlugin;

import br.com.kickpost.harleymobs.customspawner.CustomSpawnerMain;
import br.com.kickpost.harleymobs.mysql.MySQL;
import br.com.kickpost.harleymobs.mysql.MySQLManager;
import br.com.kickpost.harleymobs.ranks.RanksMain;
import br.com.kickpost.harleymobs.stackmobs.StackMobsMain;
import br.com.kickpost.harleymobs.stackspawner.StackSpawnerMain;

public class HarleyMobs extends JavaPlugin {
	protected static MySQLManager mysql;

	public void onEnable() {
		this.initialize();
		this.registerListeners();
		this.registerCommands();
	}

	public void onDisable() {
	}

	private void initialize() {
		this.saveDefaultConfig();
		new RanksMain();
		new StackSpawnerMain();
		new CustomSpawnerMain();
		new StackMobsMain();
		HarleyMobs.mysql = new MySQLManager(this);
	}

	private void registerListeners() {
	}

	private void registerCommands() {
	}

	public static final HarleyMobs getPlugin() {
		return HarleyMobs.getPlugin(HarleyMobs.class);
	}

	public static final MySQL getMySQL() {
		return HarleyMobs.mysql.getMySQLClass();
	}
}
