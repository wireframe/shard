/*
 * Copyright 2004 codecrate consulting
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.codecrate.shard.character;

import java.util.List;

import com.codecrate.shard.ability.AbilityContainer;
import com.codecrate.shard.ability.DefaultAbilityContainer;
import com.codecrate.shard.race.Race;

/**
 * Default character.
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
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

    /**
     * default constructor.
     */
    public DefaultPlayerCharacter(Race race, Gender gender, Alignment alignment, AbilityContainer abilities) {
    	this.race = race;
    	this.gender = gender;
    	this.alignment = alignment;
    	this.abilities = abilities;
    }
    
    public int getChallengeRating() {
        return challengeRating;
    }

    public int getEffectiveCharacterLevel() {
    	return 0;
    }

	public AbilityContainer getAbilities() {
		return abilities;
	}

	public int getBaseAttackBonus() {
		return baseAttackBonus;
	}
	
	public Gender getGender() {
		return gender;
	}
	
	public Alignment getAlignment() {
		return alignment;
	}
}
