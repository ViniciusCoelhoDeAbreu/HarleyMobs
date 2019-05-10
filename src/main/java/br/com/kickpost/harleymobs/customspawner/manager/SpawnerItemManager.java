package br.com.kickpost.harleymobs.customspawner.manager;

import org.bukkit.inventory.*;
import org.bukkit.*;
import br.com.kickpost.harleymobs.utils.*;
import br.com.kickpost.harleymobs.customspawner.factory.*;
import org.bukkit.entity.*;

public class SpawnerItemManager {
	private ItemStack item;

	public SpawnerItemManager(final ItemStack item) {
		this.item = item;
	}

	public boolean isSpawner() {
		return this.item != null && this.item.getType() != null
				&& this.item.getType().equals((Object) Material.MOB_SPAWNER)
				&& !new NBTManager(this.item).getTag("type").isEmpty();
	}

	public Spawner getSpawner() {
		return new Spawner(this.getEntityType(), this.getAmount());
	}

	public double getAmount() {
		return Double.valueOf(new NBTManager(this.item).getTag("amount"));
	}

	public EntityType getEntityType() {
		return EntityType.valueOf(new NBTManager(this.item).getTag("type"));
	}
}
