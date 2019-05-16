package br.com.kickpost.harleymobs.customspawner.factory;

import org.bukkit.inventory.ItemStack;

import br.com.kickpost.harleymobs.utils.ItemManager;

public class Drop {

	private String name;
	private ItemManager item;
	private int amount;

	public Drop(String name, ItemManager item) {
		this.name = name;
		this.item = item;
		this.amount = 1;
	}

	public String getName() {
		return name;
	}

	public ItemManager getItem() {
		return item;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public ItemStack build() {
		ItemStack item = getItem().build();
		item.setAmount(getAmount());

		return item;
	}

}
