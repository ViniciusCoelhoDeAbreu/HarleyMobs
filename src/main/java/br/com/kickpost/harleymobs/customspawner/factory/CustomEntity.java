package br.com.kickpost.harleymobs.customspawner.factory;

import java.util.List;

public class CustomEntity {

	private String customName;
	private List<Drop> drops;

	public CustomEntity(String customName, List<Drop> drops) {
		this.customName = customName;
		this.drops = drops;
	}

	public String getCustomName() {
		return customName;
	}

	public List<Drop> getDrops() {
		return drops;
	}

}
