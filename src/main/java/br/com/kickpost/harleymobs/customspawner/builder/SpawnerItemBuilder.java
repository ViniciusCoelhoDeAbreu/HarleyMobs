package br.com.kickpost.harleymobs.customspawner.builder;

import org.bukkit.entity.*;
import java.util.*;
import org.bukkit.inventory.*;
import org.bukkit.*;
import com.google.common.collect.*;
import org.bukkit.inventory.meta.*;
import br.com.kickpost.harleymobs.utils.*;
import br.com.kickpost.harleymobs.customspawner.loader.*;

public class SpawnerItemBuilder {
	private final String display;
	private final List<String> lore;
	private EntityType entityType;
	private double amount;

	public SpawnerItemBuilder(final EntityType entityType, final double amount) {
		this.display = new String(
				String.valueOf(ChatColor.GOLD.toString()) + ChatColor.BOLD.toString() + "GERADOR DE MONSTRO");
		this.lore = Arrays.asList(ChatColor.WHITE + "Tipo: " + ChatColor.YELLOW + "@mob",
				ChatColor.WHITE + "Stack: " + ChatColor.YELLOW + "@quantidade");
		this.entityType = entityType;
		this.amount = amount;
	}

	public ItemStack build() {
		final ItemStack item = new ItemStack(Material.MOB_SPAWNER);
		final ItemMeta itemMeta = item.getItemMeta();
		itemMeta.setDisplayName(this.display);
		itemMeta.setLore(Lists.transform(lore, l -> l.replace("@mob", MobsConfigurationLoader.getName(entityType))
				.replace("@quantidade", ObjectUtils.getFormatter(amount))));
		item.setItemMeta(itemMeta);
		return new NBTManager(new NBTManager(item).addTag("type", this.entityType.name())).addTag("amount",
				Double.toString(this.amount));
	}
}
