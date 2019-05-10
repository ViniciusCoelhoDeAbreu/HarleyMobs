package br.com.kickpost.harleymobs.stackspawner.factory;

import org.bukkit.entity.*;
import java.util.*;
import org.bukkit.*;
import org.bukkit.block.*;

public class Spawner {
	private EntityType entityType;
	private double amount;
	private UUID owner;
	private Location location;

	public Spawner(EntityType entityType, double amount, UUID owner, Location location) {
		this.entityType = entityType;
		this.amount = amount;
		this.owner = owner;
		this.location = location;
	}

	public EntityType getEntityType() {
		return this.entityType;
	}

	public double getAmount() {
		return this.amount;
	}

	public void setAmount(final double amount) {
		this.amount = amount;
	}

	public UUID getOwner() {
		return this.owner;
	}

	public Location getLocation() {
		return this.location;
	}

	public CreatureSpawner getCreatureSpawner() {
		return (CreatureSpawner) this.getLocation().getBlock().getState();
	}
}
