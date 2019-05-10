package br.com.kickpost.harleymobs.ranks.inventory;

import org.bukkit.entity.*;
import br.com.kickpost.harleymobs.ranks.type.*;
import br.com.kickpost.harleymobs.ranks.factory.*;
import br.com.kickpost.harleymobs.ranks.loader.*;
import org.bukkit.*;
import org.bukkit.inventory.*;
import br.com.kickpost.harleymobs.utils.*;
import java.util.*;

public class RanksInventoryViewer {
	private static final String INVENTORY_NAME = new String(ChatColor.DARK_GRAY + "Ranks - ");
	private Player player;
	private Inventory inventory;
	private int page;
	private CategoryType categoryType;
	private Set<Rank> ranks;

	public RanksInventoryViewer(final Player player, final int page, final CategoryType categoryType) {
		this.player = player;
		this.page = page;
		this.ranks = RanksConfigurationLoader.getRanks(categoryType, page);
		this.categoryType = categoryType;
		this.inventory = Bukkit.createInventory(player, 45,
				String.valueOf(RanksInventoryViewer.INVENTORY_NAME) + categoryType.name().toLowerCase());
	}

	public boolean has() {
		return this.ranks.size() > 0;
	}

	public void open() {
		this.player.openInventory(this.inventory);
	}

	public void load() {
		for (final Rank rank : this.ranks) {
			this.inventory.setItem(rank.getItem().getSlot(), rank.getItem().build(rank));
		}
		if (this.page >= 1) {
			this.inventory.setItem(18,
					new NBTManager(new NBTManager(new ItemBuilder(new ItemManager("351:8"),
							ChatColor.RED + "Retroceder página", Arrays.asList("")).build(1)).addTag("page",
									Integer.toString(this.page - 1))).addTag("category", this.categoryType.name()));
		}
		this.inventory
				.setItem(26,
						new NBTManager(new NBTManager(new ItemBuilder(new ItemManager("351:10"),
								ChatColor.RED + "Avançar página", Arrays.asList("")).build(1)).addTag("page",
										Integer.toString(this.page + 1))).addTag("category", this.categoryType.name()));
	}

	public static final String getTitle() {
		return RanksInventoryViewer.INVENTORY_NAME;
	}
}
