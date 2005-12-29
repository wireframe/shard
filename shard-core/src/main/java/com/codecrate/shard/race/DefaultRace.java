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

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.codecrate.shard.kit.CharacterClass;
import com.codecrate.shard.movement.Movement;

/**
 * <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class DefaultRace implements Race, Comparable {
	private String id;
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
    private int baseSkillPointsPerLevel;
    private AgeCategorization ageCategorization;

    /**
     * hibernate constructor.
     */
    private DefaultRace() {
    }

	public DefaultRace(String name, RacialSize size, Movement movement,
			Collection abilityModifiers, Collection skillModifiers,
			int levelAdjustment,
			Collection grantedLanguages, Collection availableLanguages,
			Vision vision, CharacterClass favoredClass, AgeCategorization ageCategory,
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
        this.ageCategorization = ageCategory;
        this.baseSkillPointsPerLevel = baseSkillPointsPerLevel;
	}

	public String toString() {
	    return name;
	}

    public int hashCode() {
    	return new HashCodeBuilder(3, 7)
    	.append(name)
    	.toHashCode();
    }

    public boolean equals(Object object) {
    	if (this == object) {
    		return true;
    	}

    	if (!(object instanceof DefaultRace)) {
    		return false;
    	}
    	DefaultRace target = (DefaultRace) object;
    	return new EqualsBuilder()
	    	.append(name, target.name)
	    	.isEquals();
    }

    public int compareTo(Object object) {
        DefaultRace race = (DefaultRace) object;
        return name.compareTo(race.name);
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

	public int getBaseSkillPointsPerLevel() {
	    return baseSkillPointsPerLevel;
	}

	public void setLevelAdjustment(int levelAdjustment) {
		this.levelAdjustment = levelAdjustment;
	}

	public void setBaseSkillPointsPerLevel(int baseSkillPointsPerLevel) {
		this.baseSkillPointsPerLevel = baseSkillPointsPerLevel;
	}

    public AgeCategorization getAgeCategorization() {
        return ageCategorization;
    }
}