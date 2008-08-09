package com.codecrate.shard.defense;

import com.codecrate.shard.ability.Ability;

/**
 * Defense is used to resist attacks.
 */
public enum Defense {
  ARMOR_CLASS("Armor Class", Ability.DEXTERITY, Ability.INTELLIGENCE), 
  REFLEX("Reflex", Ability.DEXTERITY, Ability.INTELLIGENCE), 
  FORTITUDE("Fortitude", Ability.STRENGTH, Ability.CONSTITUTION), 
  WILLPOWER("Willpower", Ability.WISDOM, Ability.CHARISMA);

	private final String name;
	private final Ability primaryScore;
	private final Ability secondaryScore;
	
	Defense(String name, Ability primaryScore, Ability secondaryScore) {
	  this.name = name;
	  this.primaryScore = primaryScore;
	  this.secondaryScore = secondaryScore;
	}
}