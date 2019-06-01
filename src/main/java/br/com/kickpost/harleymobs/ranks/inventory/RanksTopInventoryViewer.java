package br.com.kickpost.harleymobs.ranks.inventory;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import br.com.kickpost.harleymobs.ranks.factory.RankPlayer;
import br.com.kickpost.harleymobs.ranks.hook.PermissionsExHook;
import br.com.kickpost.harleymobs.ranks.runnable.RanksInfoUpdaterRunnable;

public class RanksTopInventoryViewer {

	private static final String INVENTORY_TITLE = new String(ChatColor.DARK_GRAY + "TOP 10 - Ranks");

	private Player player;
	private Inventory inventory;

	private List<Integer> slots = Arrays.asList(11, 12, 13, 14, 15, 20, 21, 22, 23, 24);

	public RanksTopInventoryViewer(Player player) {
		this.player = player;
		this.inventory = Bukkit.createInventory(player, 36, INVENTORY_TITLE);
	}

	public void open() {
		player.openInventory(inventory);
	}

	public void load() {
		Iterator<Integer> slotsIterator = slots.iterator();

		for (Entry<Integer, RankPlayer> rp : RanksInfoUpdaterRunnable.getPlayers().entrySet()) {
			inventory.setItem(slotsIterator.next(), getHead(rp.getKey(), rp.getValue()));
		}
	}

	private ItemStack getHead(int position, RankPlayer rankPlayer) {

		ItemStack item = new ItemStack(Material.SKULL_ITEM, 1);
		SkullMeta skullMeta = (SkullMeta) item.getItemMeta();
		item.setDurability((short) 3);
		skullMeta.setDisplayName(ChatColor.GREEN.toString() + position + "º Lugar");
		skullMeta.setLore(Arrays.asList(ChatColor.WHITE + "Jogador "
				+ PermissionsExHook.getPrefix(rankPlayer.getPlayer()) + rankPlayer.getPlayer().getName()
				+ ChatColor.WHITE + " no rank " + rankPlayer.getRank().getTag()));
		skullMeta.setOwner(rankPlayer.getPlayer().getName());
		item.setItemMeta(skullMeta);

		return item;
	}

	public static final String getTitle() {
		return INVENTORY_TITLE;
	}
}
