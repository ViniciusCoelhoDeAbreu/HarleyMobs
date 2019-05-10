package br.com.kickpost.harleymobs.stackmobs.customevent;

import org.bukkit.event.*;
import org.bukkit.entity.*;
import org.bukkit.*;

public class EntityCustomDeathListener extends Event {
	private static final HandlerList handlers = new HandlerList();

	private Player player;
	private Entity entity;
	private double amount;

	public EntityCustomDeathListener(final Player player, final Entity entity, final double amount) {
		this.player = player;
		this.entity = entity;
		this.amount = amount;
		Bukkit.getPluginManager().callEvent(this);
	}

	public Player getPlayer() {
		return this.player;
	}

	public Entity getEntity() {
		return this.entity;
	}

	public double getAmount() {
		return this.amount;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}
