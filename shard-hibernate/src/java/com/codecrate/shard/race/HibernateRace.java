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
package com.codecrate.shard.race;

import java.util.Collection;

import com.codecrate.shard.Identifiable;
import com.codecrate.shard.dice.Dice;
import com.codecrate.shard.kit.CharacterClass;
import com.codecrate.shard.movement.Movement;

/**
 * <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class HibernateRace implements Race, Identifiable {
	private String name;
	private RacialSize size;
	private Movement movement;
	private Collection abilityModifiers;
	private int levelAdjustment;
	private Collection bonusLanguages;
	private Collection automaticLanguages;
	private Vision vision;
	private Collection skillModifiers;
	private CharacterClass favoredClass;
    private Dice maxAgeDice;
    private int baseSkillPointsPerLevel;
	
    /**
     * constructor for hibernate.
     */
    protected HibernateRace() {
    }
    
	public HibernateRace(String name, RacialSize size, Movement movement, 
			Collection abilityModifiers, Collection skillModifiers, 
			int levelAdjustment, 
			Collection grantedLanguages, Collection availableLanguages,
			Vision vision, CharacterClass favoredClass, Dice maxAgeDice, 
			int baseSkillPointsPerLevel) {
		this.name = name;
		this.size = size;
		this.movement = movement;
		this.abilityModifiers = abilityModifiers;
		this.skillModifiers = skillModifiers;
		this.levelAdjustment = levelAdjustment;
		this.automaticLanguages = grantedLanguages;
		this.bonusLanguages = availableLanguages;
		this.vision = vision;
		this.favoredClass = favoredClass;
        this.maxAgeDice = maxAgeDice;
        this.baseSkillPointsPerLevel = baseSkillPointsPerLevel;
	}

	public String toString() {
	    return name;
	}
	
	public String getId() {
	    return name;
	}
	
	public void setId(String id) {
	    this.name = id;
	}
	
	public boolean isSame(Race race) {
	    return equals(race);
	}
	
	public RacialSize getSize() {
		return size;
	}

	public Movement getMovement() {
		return movement;
	}

	public Collection getAbilityModifiers() {
		return abilityModifiers;
	}
	
	public int getLevelAdjustment() {
		return levelAdjustment;
	}
	
	public void setLevelAdjustment(int levelAdjustment) {
	    this.levelAdjustment = levelAdjustment;
	}
	
	public Collection getAutomaticLanguages() {
		return automaticLanguages;
	}
	
	public Collection getBonusLanguages() {
		return bonusLanguages;
	}
	
	public Vision getVision() {
		return vision;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
	    this.name = name;
	}
	
	public Collection getSkillModifiers() {
		return skillModifiers;
	}
	
	public CharacterClass getFavoredClass() {
		return favoredClass;
	}
	
	public void setFavoredClass(CharacterClass kit) {
	    this.favoredClass = kit;
	}
	
	public Dice getMaxAgeDice() {
	    return maxAgeDice;
	}
	
	public int getBaseSkillPointsPerLevel() {
	    return baseSkillPointsPerLevel;
	}
	
	public void setBaseSkillPointsPerLevel(int points) {
	    this.baseSkillPointsPerLevel = points;
	}
}