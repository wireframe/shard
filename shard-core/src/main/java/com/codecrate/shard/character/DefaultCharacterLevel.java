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

import com.codecrate.shard.ability.AbilityScoreContainer;
import com.codecrate.shard.ability.DefaultAbility;
import com.codecrate.shard.kit.CharacterClass;
import com.codecrate.shard.race.Race;

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

    public DefaultCharacterLevel(PlayerCharacter character, int level, int hitpoints, CharacterClass kit, Collection skillRanks) {
        this.character = character;
		this.level = level;
        this.kit = kit;
    	this.hitpoints = hitpoints;
		this.skillRanks = skillRanks;
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
