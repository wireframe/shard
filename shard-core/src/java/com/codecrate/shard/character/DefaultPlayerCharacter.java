package com.codecrate.shard.character;

import java.util.List;

import com.codecrate.shard.character.ability.Ability;
import com.codecrate.shard.character.ability.AbilityContainer;
import com.codecrate.shard.character.ability.DefaultAbilityContainer;
import com.codecrate.shard.character.race.Race;


public class DefaultPlayerCharacter implements PlayerCharacter {
    private int currentHitPoints;
    private int maxHitPoints;
    private int experience;
    private List skills;
    private List feats;
    private List equipment;
    
    private AbilityContainer abilities = new DefaultAbilityContainer();
    
    private int challengeRating;
    private Race race;
    private Gender gender;
    private Alignment alignment;
    
    private int baseAttackBonus;

    public DefaultPlayerCharacter(Race race) {
    	this.race = race;
    }
    
    public int getChallengeRating() {
        return challengeRating;
    }


	public Ability getAbility(String name) {
		return abilities.getAbility(name);
	}

	public void setAbility(String name, Ability ability) {
		abilities.setAbility(name, ability);
	}
}
