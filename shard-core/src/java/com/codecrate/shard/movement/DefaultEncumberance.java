package com.codecrate.shard.movement;

public class DefaultEncumberance implements Encumberance {
	public static final Encumberance LIGHT = new DefaultEncumberance("Light", 100, 0);
	public static final Encumberance MEDIUM = new DefaultEncumberance("Medium", 3, -3);
	public static final Encumberance HEAVY = new DefaultEncumberance("Heavy", 1, -6);

	
	private final int maxDexterityModifier;
	private final int armorCheckModifier;
	private final String name;

	public DefaultEncumberance(String name, int maxDexterityModifier, int armorCheckModifier) {
		this.name = name;
		this.maxDexterityModifier = maxDexterityModifier;
		this.armorCheckModifier = armorCheckModifier;
	}
	
	public String getName() {
		return name;
	}
	
	public int getMaxDexterityModifier() {
		return maxDexterityModifier;
	}

	public int getArmorCheckModifier() {
		return armorCheckModifier;
	}

}
