package com.codecrate.shard.movement;

public interface Encumberance {

	String getName();
	
	/**
	 * gets the max dexterity modifier for character.
	 * heavy armor restricts movement.
	 * @return
	 */
	int getMaxDexterityModifier();
	
	/**
	 * gets the armor check penalty.
	 * ex: Heavy encumberance causes penelty for swim checks.
	 * @return
	 */
	int getArmorCheckModifier();
}
