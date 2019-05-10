package br.com.kickpost.harleymobs.ranks.inventory;

import org.bukkit.entity.*;
import org.bukkit.*;
import org.bukkit.inventory.*;
import java.util.*;
import br.com.kickpost.harleymobs.utils.*;

public class RanksMainInventoryViewer {
	private static final String INVENTORY_NAME = new String(
			ChatColor.DARK_GRAY.toString() + ChatColor.BOLD.toString() + "RANKS");
	private Player player;
	private Inventory inventory;

	public RanksMainInventoryViewer(final Player player) {
		this.player = player;
		this.inventory = Bukkit.createInventory((InventoryHolder) player, 27, RanksMainInventoryViewer.INVENTORY_NAME);
	}

	public void open() {
		this.player.openInventory(this.inventory);
	}

	public void load() {
		this.inventory.setItem(11,
				new ItemBuilder(new ItemManager("278"),
						String.valueOf(ChatColor.GOLD.toString()) + ChatColor.BOLD.toString() + "MINERADOR",
						Arrays.asList(ChatColor.WHITE + "Veja os ranks minerador.")).build(1));
		this.inventory.setItem(13,
				new ItemBuilder(new ItemManager("293"),
						String.valueOf(ChatColor.GOLD.toString()) + ChatColor.BOLD.toString() + "FAZENDEIRO",
						Arrays.asList(ChatColor.WHITE + "Veja os ranks fazendeiro.")).build(1));
		this.inventory.setItem(15,
				new ItemBuilder(new ItemManager("276"),
						String.valueOf(ChatColor.GOLD.toString()) + ChatColor.BOLD.toString() + "ASSASSINO",
						Arrays.asList(ChatColor.WHITE + "Veja os ranks assassino.")).build(1));
	}

	public static final String getTitle() {
		return RanksMainInventoryViewer.INVENTORY_NAME;
	}
}
