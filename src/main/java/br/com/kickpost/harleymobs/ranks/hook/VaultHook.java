package br.com.kickpost.harleymobs.ranks.hook;

import net.milkbowl.vault.economy.*;
import org.bukkit.entity.*;
import org.bukkit.*;
import org.bukkit.plugin.*;

public class VaultHook {
	protected static Economy Economy;

	static {
		setupEconomy();
	}

	@SuppressWarnings("deprecation")
	public static boolean has(final Player player, final double d) {
		return VaultHook.Economy.has(Bukkit.getOfflinePlayer(player.getName()), d);
	}

	@SuppressWarnings("deprecation")
	public static double get(final Player player) {
		return VaultHook.Economy.getBalance(Bukkit.getOfflinePlayer(player.getName()));
	}

	@SuppressWarnings("deprecation")
	public static void remove(final Player player, final double d) {
		VaultHook.Economy.withdrawPlayer(Bukkit.getOfflinePlayer(player.getName()), d);
	}

	protected static boolean setupEconomy() {
		if (Bukkit.getServer().getPluginManager().getPlugin("Vault") == null) {
			return false;
		}
		final RegisteredServiceProvider<Economy> rsp = (RegisteredServiceProvider<Economy>) Bukkit.getServer()
				.getServicesManager().getRegistration(Economy.class);
		if (rsp == null) {
			return false;
		}
		VaultHook.Economy = (Economy) rsp.getProvider();
		return VaultHook.Economy != null;
	}
}
