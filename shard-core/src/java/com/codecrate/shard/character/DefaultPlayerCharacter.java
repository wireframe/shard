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

    public DefaultPlayerCharacter(Race race, Gender gender) {
    	this.race = race;
    	this.gender = gender;
    }
    
    public int getChallengeRating() {
        return challengeRating;
    }

    public int getEffectiveCharacterLevel() {
    	return 0;
    }

	public Ability getAbility(String name) {
		return abilities.getAbility(name);
	}

	public void setAbility(String name, Ability ability) {
		abilities.setAbility(name, ability);
	}

	public int getBaseAttackBonus() {
		return baseAttackBonus;
	}
	
	public Gender getGender() {
		return gender;
	}
}
