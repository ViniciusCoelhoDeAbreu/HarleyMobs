package br.com.kickpost.harleymobs.stackmobs.manager;

import org.bukkit.entity.*;
import org.bukkit.block.*;
import org.bukkit.*;
import br.com.kickpost.harleymobs.*;
import org.bukkit.metadata.*;
import org.bukkit.plugin.*;
import br.com.kickpost.harleymobs.utils.*;
import br.com.kickpost.harleymobs.customspawner.loader.*;
import java.util.*;

public class EntityManager {
	private final String ENTITY_NAME;
	private Entity entity;

	public EntityManager(final Entity entity) {
		this.ENTITY_NAME = new String(String.valueOf(ChatColor.WHITE.toString()) + ChatColor.BOLD.toString()
				+ "@quantidade" + ChatColor.GOLD.toString() + ChatColor.BOLD.toString() + " @mob");
		this.entity = entity;
	}

	public boolean isValidEntity() {
		return this.entity.hasMetadata("amount") && this.entity.hasMetadata("spawner")
				&& new LocationManager(this.entity.getMetadata("spawner").get(0).asString()).toLocation().getBlock()
						.getState() instanceof CreatureSpawner;
	}

	public double getAmount() {
		return this.entity.hasMetadata("amount") ? this.entity.getMetadata("amount").get(0).asDouble() : 0.0;
	}

	public CreatureSpawner getFromSpawner() {
		final Location location = new LocationManager(this.entity.getMetadata("spawner").get(0).asString())
				.toLocation();
		return (CreatureSpawner) location.getBlock().getState();
	}

	public void setAmount(final double newAmount, final Location fromSpawner) {
		this.entity.setMetadata("amount",
				(MetadataValue) new FixedMetadataValue((Plugin) HarleyMobs.getPlugin(), (Object) newAmount));
		this.entity.setMetadata("spawner", (MetadataValue) new FixedMetadataValue((Plugin) HarleyMobs.getPlugin(),
				(Object) new LocationManager(fromSpawner.getBlock().getLocation()).toString()));
		this.entity.setCustomName(this.ENTITY_NAME.replace("@quantidade", ObjectUtils.getFormatter(newAmount))
				.replace("@mob", MobsConfigurationLoader.getName(this.entity.getType())));
	}

	public Entity getMostProximityEntity() {
		try {
			return (Entity) this.entity.getNearbyEntities(5.0, 5.0, 5.0).stream()
					.filter(r -> r.getType().equals((Object) this.entity.getType())).filter(r -> !r.isDead())
					.filter(r -> r.hasMetadata("amount")).findFirst().get();
		} catch (NoSuchElementException e) {
			return null;
		}
	}
}
