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

import java.util.Collection;
import java.util.Iterator;

import com.codecrate.shard.ability.AbilityScoreContainer;
import com.codecrate.shard.ability.DefaultAbility;
import com.codecrate.shard.kit.CharacterClass;
import com.codecrate.shard.modifier.DefaultKeyedModifier;
import com.codecrate.shard.modifier.DefaultModifierType;
import com.codecrate.shard.modifier.KeyedModifier;
import com.codecrate.shard.race.Race;
import com.codecrate.shard.skill.Skill;

/**
 *
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class DefaultCharacterLevel implements CharacterLevel {
    private String id;
    private int level;
    private int hitpoints;
    private CharacterClass kit;
	private Collection skillRanks;
	private PlayerCharacter character;

    /**
     * hibernate constructor
     */
    private DefaultCharacterLevel() {
    }

    public DefaultCharacterLevel(PlayerCharacter character, CharacterClass kit, int hitpoints) {
        this.character = character;
		this.level = character.getCharacterProgression().getNextCharacterLevel();
        this.kit = kit;
    	this.hitpoints = hitpoints;
    }

    public String toString() {
        return character + " " + level;
    }
    public int getLevel() {
        return level;
    }

	public int getHitpoints() {
		return hitpoints;
	}

	public void setHitpoints(int hitpoints) {
		this.hitpoints = hitpoints;
	}

	public Collection getSkillRanks() {
		return skillRanks;
	}

	public void setSkillRanks(Collection skillRanks) {
		this.skillRanks = skillRanks;
	}

	public void addSkillRank(Skill skill) {
		int cost = getPointCost(skill);
		if (getNumberOfUsedSkillPoints() + cost > getSkillPoints()) {
			throw new IllegalArgumentException("Can not exceed " + getSkillPoints() + " skill points.");
		}
		skillRanks.add(new DefaultKeyedModifier(skill, DefaultModifierType.RANK, 1));
	}

	private int getNumberOfUsedSkillPoints() {
		int total = 0;
		for (Iterator iter = skillRanks.iterator(); iter.hasNext();) {
			KeyedModifier rank = (KeyedModifier) iter.next();
			Skill skill = (Skill) rank.getKey();
			total += getPointCost(skill);
		}
		return total;
	}

	private int getPointCost(Skill skill) {
		return (kit.getClassSkills().contains(skill) ? 1 : 2);
	}

    public int getSkillPoints() {
        int intBonus = 0;
        int modifier = 1;
        if (1 == level) {
            modifier = 4;
        }

        AbilityScoreContainer abilities = character.getAbilities();
        if (abilities.hasAbilityScore(DefaultAbility.INTELLIGENCE)) {
            intBonus = abilities.getIntelligence().getModifier();
        }

        Race race = character.getRace();
        return modifier * (kit.getBaseSkillPointsPerLevel() + race.getBaseSkillPointsPerLevel() + intBonus);
    }

	public CharacterClass getCharacterClass() {
		return kit;
	}

	public void setCharacterClass(CharacterClass kit) {
		this.kit = kit;
	}
}
