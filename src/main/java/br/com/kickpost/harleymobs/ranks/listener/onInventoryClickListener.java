package br.com.kickpost.harleymobs.ranks.listener;

import org.bukkit.event.inventory.*;
import br.com.kickpost.harleymobs.ranks.inventory.*;
import org.bukkit.event.*;
import org.bukkit.entity.*;
import br.com.kickpost.harleymobs.ranks.type.*;
import br.com.kickpost.harleymobs.utils.*;
import org.bukkit.*;

public class onInventoryClickListener implements Listener {
	@EventHandler
	public void onClick(final InventoryClickEvent e) {
		if (e.getCurrentItem() == null || e.getCurrentItem().getType().equals((Object) Material.AIR)
				|| e.getInventory() == null || e.getSlotType().equals((Object) InventoryType.SlotType.OUTSIDE)
				|| e.getAction() == null || e.getInventory().getTitle() == null
				|| (!e.getInventory().getTitle().startsWith(RanksInventoryViewer.getTitle())
						&& !e.getInventory().getTitle().equals(RanksMainInventoryViewer.getTitle()))) {
			return;
		}
		e.setCancelled(true);
		e.setResult(Event.Result.DENY);
	}

	@EventHandler
	public void onSelect(final InventoryClickEvent e) {
		if (e.getCurrentItem() == null || e.getCurrentItem().getType().equals((Object) Material.AIR)
				|| e.getInventory() == null || e.getSlotType().equals((Object) InventoryType.SlotType.OUTSIDE)
				|| e.getAction() == null || e.getInventory().getTitle() == null
				|| !e.getInventory().getTitle().equals(RanksMainInventoryViewer.getTitle())) {
			return;
		}
		final Player player = (Player) e.getWhoClicked();
		switch (e.getRawSlot()) {
		case 11: {
			final RanksInventoryViewer ranksInventoryMiner = new RanksInventoryViewer(player, 0,
					CategoryType.MINERADOR);
			ranksInventoryMiner.load();
			ranksInventoryMiner.open();
			break;
		}
		case 13: {
			if (player.hasPermission("fazendeiro.abrir")) {
				final RanksInventoryViewer ranksInventoryFarmer = new RanksInventoryViewer(player, 0,
						CategoryType.FAZENDEIRO);
				ranksInventoryFarmer.load();
				ranksInventoryFarmer.open();
				break;
			}
			break;
		}
		case 15: {
			if (player.hasPermission("assassino.abrir")) {
				final RanksInventoryViewer ranksInventoryAssassin = new RanksInventoryViewer(player, 0,
						CategoryType.ASSASSINO);
				ranksInventoryAssassin.load();
				ranksInventoryAssassin.open();
				break;
			}
			break;
		}
		}
	}

	@EventHandler
	public void onChangePage(final InventoryClickEvent e) {
		if (e.getCurrentItem() == null || e.getCurrentItem().getType().equals((Object) Material.AIR)
				|| e.getInventory() == null || e.getSlotType().equals((Object) InventoryType.SlotType.OUTSIDE)
				|| e.getAction() == null || e.getInventory().getTitle() == null
				|| !e.getInventory().getTitle().startsWith(RanksInventoryViewer.getTitle())) {
			return;
		}
		final Player player = (Player) e.getWhoClicked();
		if (e.getRawSlot() == 18 || e.getRawSlot() == 26) {
			final RanksInventoryViewer inventoryViewer = new RanksInventoryViewer(player,
					Integer.parseInt(new NBTManager(e.getCurrentItem()).getTag("page")),
					CategoryType.valueOf(new NBTManager(e.getCurrentItem()).getTag("category")));
			if (inventoryViewer.has()) {
				inventoryViewer.load();
				inventoryViewer.open();
			} else {
				player.sendMessage(ChatColor.RED + "Não há esta página.");
			}
		}
	}
}
