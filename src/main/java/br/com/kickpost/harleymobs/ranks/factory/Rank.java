package br.com.kickpost.harleymobs.ranks.factory;

import java.util.*;
import org.bukkit.*;

public class Rank {
	private String name;
	private double moneyCost;
	private double cost;
	private Category category;
	private int position;
	private String actionBarMessage;
	private String broadcastMessage;
	private String tag;
	private Item item;
	private List<String> permissions;
	private int page;

	public Rank(final String name, final double moneyCost, final double cost, final Category category,
			final int position, final String actionBarMessage, final String broadcastMessage, final String tag,
			final Item item, final List<String> permissions, final int page) {
		this.name = name;
		this.moneyCost = moneyCost;
		this.cost = cost;
		this.category = category;
		this.position = position;
		this.actionBarMessage = ChatColor.translateAlternateColorCodes('&', actionBarMessage);
		this.broadcastMessage = ChatColor.translateAlternateColorCodes('&', broadcastMessage);
		this.tag = ChatColor.translateAlternateColorCodes('&', tag);
		this.item = item;
		this.permissions = permissions;
		this.page = page;
	}

	public String getName() {
		return this.name;
	}

	public double getMoneyCost() {
		return this.moneyCost;
	}

	public double getCost() {
		return this.cost;
	}

	public Category getCategory() {
		return this.category;
	}

	public int getPosition() {
		return this.position;
	}

	public String getActionBarMessage() {
		return this.actionBarMessage;
	}

	public String getBroadcastMessage() {
		return this.broadcastMessage;
	}

	public String getTag() {
		return this.tag;
	}

	public Item getItem() {
		return this.item;
	}

	public List<String> getPermissions() {
		return this.permissions;
	}

	public int getPage() {
		return this.page;
	}
}
