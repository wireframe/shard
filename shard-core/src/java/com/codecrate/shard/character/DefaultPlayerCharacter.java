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

import java.math.BigDecimal;
import java.util.Iterator;

import com.codecrate.shard.ability.AbilityScoreContainer;
import com.codecrate.shard.armorclass.ArmorClass;
import com.codecrate.shard.equipment.ItemEntryContainer;
import com.codecrate.shard.kit.CharacterClass;
import com.codecrate.shard.movement.Encumberance;
import com.codecrate.shard.race.Race;
import com.codecrate.shard.save.SavingThrowContainer;
import com.codecrate.shard.skill.SkillEntryContainer;

/**
 * Default character.
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class DefaultPlayerCharacter implements PlayerCharacter {
    private final int experience;
    private final Age age;
    private final BigDecimal challengeRating;
    private final Race race;
    private final Gender gender;
    private final Alignment alignment;
    private final AbilityScoreContainer abilities;
    private final HitPoints hitPoints;
    private final Encumberance encumberance;
    private final ArmorClass armorClass;
    private final CharacterProgression characterProgression;
    private final SavingThrowContainer savingThrows;
    private final String name;
    private final ItemEntryContainer items;
    private final SkillEntryContainer skills;
    private final Initiative initiative;
    
    /**
     * default constructor.
     * @param skills skills for the character.
     * @param challengeRating challengeRating for the character.
     */
    public DefaultPlayerCharacter(String name, Race race, Gender gender, Alignment alignment, 
    		AbilityScoreContainer abilities, HitPoints hitPoints, ArmorClass armorClass, Encumberance encumberance, 
    		Age age, CharacterProgression characterProgression, SavingThrowContainer savingThrows, 
    		ItemEntryContainer items, int experience, SkillEntryContainer skills, BigDecimal challengeRating, 
    		Initiative initiative) {
    	this.name = name;
        this.race = race;
    	this.gender = gender;
    	this.alignment = alignment;
    	this.abilities = abilities;
    	this.hitPoints = hitPoints;
        this.armorClass = armorClass;
    	this.encumberance = encumberance;
    	this.age = age;
    	this.characterProgression = characterProgression;
        this.savingThrows = savingThrows;
        this.items = items;
        this.experience = experience;
        this.skills = skills;
        this.challengeRating = challengeRating;
        this.initiative = initiative;
    }
    
    public BigDecimal getChallengeRating() {
        return challengeRating;
    }

    public int getEffectiveCharacterLevel() {
    	return characterProgression.getCharacterLevel() + race.getLevelAdjustment();
    }

	public AbilityScoreContainer getAbilities() {
		return abilities;
	}

	public int getBaseAttackBonus() {
		int value = 0;
		Iterator it = characterProgression.getClasses().iterator();
		while (it.hasNext()) {
			CharacterClass kit = (CharacterClass) it.next();
			value += characterProgression.getClassLevel(kit).getBaseAttackBonus();
		}
		value += race.getSize().getBaseAttackBonusModifier();
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
    public String getName() {
        return name;
    }
    public SavingThrowContainer getSavingThrows() {
        return savingThrows;
    }
    
    public ItemEntryContainer getInventory() {
        return items;
    }
    
    public SkillEntryContainer getSkills() {
    	return skills;
    }
    
    public int getExperience() {
        return experience;
    }

    public Initiative getInitiative() {
        return initiative;
    }
}
