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

import java.util.Iterator;
import java.util.List;

import com.codecrate.shard.ability.AbilityScoreContainer;
import com.codecrate.shard.armorclass.ArmorClass;
import com.codecrate.shard.kit.CharacterClass;
import com.codecrate.shard.movement.Encumberance;
import com.codecrate.shard.race.Race;

/**
 * Default character.
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class DefaultPlayerCharacter implements PlayerCharacter {
    private int experience;
    private List skills;
    private List feats;
    private List equipment;
    
    private Age age;
    private int challengeRating;
    private Race race;
    private Gender gender;
    private final Alignment alignment;
    private AbilityScoreContainer abilities;
    private HitPoints hitPoints;
    private Encumberance encumberance;
    private final ArmorClass armorClass;
    private CharacterProgression characterProgression;
    
    /**
     * default constructor.
     */
    public DefaultPlayerCharacter(Race race, Gender gender, Alignment alignment, 
    		AbilityScoreContainer abilities, HitPoints hitPoints, ArmorClass armorClass, Encumberance encumberance, 
    		Age age, CharacterProgression characterProgression) {
    	this.race = race;
    	this.gender = gender;
    	this.alignment = alignment;
    	this.abilities = abilities;
    	this.hitPoints = hitPoints;
        this.armorClass = armorClass;
    	this.encumberance = encumberance;
    	this.age = age;
    	this.characterProgression = characterProgression;
    }
    
    public int getChallengeRating() {
        return challengeRating;
    }

    public int getEffectiveCharacterLevel() {
    	return characterProgression.getMaxCharacterLevel() + race.getLevelAdjustment();
    }

	public AbilityScoreContainer getAbilities() {
		return abilities;
	}

	public int getBaseAttackBonus() {
		int value = 0;
		Iterator it = characterProgression.getClasses().iterator();
		while (it.hasNext()) {
			CharacterClass kit = (CharacterClass) it.next();
			value += characterProgression.getMaxClassLevel(kit).getBaseAttackBonus();
		}
		return value;
	}
	
	public Gender getGender() {
		return gender;
	}
	
	public Alignment getAlignment() {
		return alignment;
	}
	
	public HitPoints getHitPoints() {
		return hitPoints;
	}
	
	public Encumberance getEncumberance() {
		return encumberance;
	}
	
	public Age getAge() {
		return age;
	}
	
	public Race getRace() {
		return race;
	}
	
    public ArmorClass getArmorClass() {
        return armorClass;
    }
    
    public CharacterProgression getCharacterProgression() {
    	return characterProgression;
    }
}
