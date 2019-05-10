package br.com.kickpost.harleymobs.utils;

import org.bukkit.*;
import com.google.common.collect.*;
import java.util.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;

public class ItemBuilder {
	private ItemManager item;
	private String display;
	private List<String> lore;

	public ItemBuilder(final ItemManager item, final String display, final List<String> lore) {
		this.item = item;
		this.display = ChatColor.translateAlternateColorCodes('&', display);
		this.lore = Lists.transform(lore, l -> ChatColor.translateAlternateColorCodes('&', l));
	}

	public ItemManager getItem() {
		return this.item;
	}

	public String getDisplay() {
		return this.display;
	}

	public List<String> getLore() {
		return this.lore;
	}

	public ItemBuilder addLore(final String lore) {
		final List<String> newLore = new ArrayList<String>();
		newLore.addAll(this.lore);
		newLore.add(lore);
		this.lore = newLore;
		return this;
	}

	public ItemBuilder removeLore(final int index) {
		List<String> newLore = new ArrayList<String>();
		newLore.addAll(this.lore);
		newLore.remove(index);
		this.lore = newLore;
		return this;
	}

	public ItemStack build(final int amount) {
		final ItemStack item = this.item.build();
		final ItemMeta itemMeta = item.getItemMeta();
		itemMeta.setDisplayName(this.display);
		itemMeta.setLore(this.lore);
		item.setItemMeta(itemMeta);
		return item;
	}
}
