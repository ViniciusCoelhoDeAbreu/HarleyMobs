package br.com.kickpost.harleymobs.ranks.factory;

import java.util.*;
import com.google.common.collect.*;
import org.bukkit.inventory.*;
import br.com.kickpost.harleymobs.utils.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.*;

public class Item {
	private String id;
	private int slot;
	private int amount;
	private List<String> lore;

	public Item(final String id, final int slot, final int amount, final List<String> lore) {
		this.id = id;
		this.slot = slot;
		this.amount = amount;
		this.lore = Lists.transform(lore, l -> ChatColor.translateAlternateColorCodes('&', l));
	}

	public ItemStack build(final Rank rank) {
		if (this.id.matches("[0-9]+")) {
			return new ItemBuilder(
					new ItemManager(this.id), rank.getTag(), Lists
							.transform(lore,
									l -> l.replace("@CustoMoney", ObjectUtils.getFormatter(rank.getMoneyCost()))
											.replace("@Custo", ObjectUtils.getFormatter(rank.getCost()))))
													.build(this.amount);
		}
		final ItemStack skull = new ItemStack(Material.SKULL_ITEM);
		skull.setDurability((short) 3);
		skull.setAmount(this.amount);
		final SkullMeta sm = (SkullMeta) skull.getItemMeta();
		sm.setOwner(this.id);
		sm.setDisplayName(rank.getTag());
		sm.setLore(Lists.transform(lore, l -> l.replace("@CustoMoney", ObjectUtils.getFormatter(rank.getMoneyCost()))
				.replace("@Custo", ObjectUtils.getFormatter(rank.getCost()))));
		skull.setItemMeta((ItemMeta) sm);
		return skull;
	}

	public String getId() {
		return this.id;
	}

	public int getSlot() {
		return this.slot;
	}

	public int getAmount() {
		return this.amount;
	}
}
