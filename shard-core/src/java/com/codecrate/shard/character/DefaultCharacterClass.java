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

import com.codecrate.shard.dice.Dice;

public class DefaultCharacterClass implements CharacterClass {

	private Collection classSkills;
	private Dice hitDicePerLevel;
	private final int baseSkillPointsPerLevel;

	public DefaultCharacterClass(Dice hitDicePerLevel, Collection classSkills, int baseSkillPointsPerLevel) {
		this.hitDicePerLevel = hitDicePerLevel;
		this.classSkills = classSkills;
		this.baseSkillPointsPerLevel = baseSkillPointsPerLevel;
	}
	
	public Dice getHitDicePerLevel() {
		return hitDicePerLevel;
	}

	public Collection getClassSkills() {
		return classSkills;
	}
	
	public int getBaseSkillPointsPerLevel() {
		return baseSkillPointsPerLevel;
	}

}
