package com.codecrate.shard.character.armorclass;

/**
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public interface ArmorClass {

	int getValue();
	
	void addArmorClassModifier(ArmorClassModifier modifier);
	
	void removeArmorClassModifier(ArmorClassModifier modifier);
}
