package com.codecrate.shard.character;

import com.codecrate.shard.character.ability.AbilityContainer;

public interface PlayerCharacter extends AbilityContainer {
	int getChallengeRating();
	
	/**
	 * gets the base attack bonus for this character.
	 * value should be the sum of the bonuses for all character classes.
	 * @return
	 */
	int getBaseAttackBonus();
}