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
package com.codecrate.shard.kit;

import java.util.Collection;

import com.codecrate.shard.character.prereq.CharacterPrerequisite;
import com.codecrate.shard.dice.Dice;
import com.codecrate.shard.skill.Skill;

/**
 * Definition of a character class.
 * ex: Ranger
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public interface CharacterClass {

	/**
	 * gets the dice used for each level to generate hit points (before constitution modifier)
	 * ex: d8
	 * @return
	 */
	Dice getHitDicePerLevel();
	
	/**
	 * gets the skills that are considered class skills.
	 * all other skills are considered cross class.
	 * @return
	 */
	Collection getClassSkills();
	
	/**
	 * certain classes have additional languages available to them.
	 * @return
	 */
	Collection getBonusLanguages();
	
	/**
	 * gets the feats automatically granted for this class.
	 * @return
	 */
	Collection getFeats();
	
	/**
	 * gets the skill bonuses automatically granted to this class.
	 * @return
	 */
	Collection getSkills();
	
	/**
	 * gets the number of base skill points per level.
	 * this is before the intelligence modifier is added on.
	 * @return number of skill points per level (before intelligence modifier).
	 */
	int getBaseSkillPointsPerLevel();

    /**
     * gets the name of the class.
     * ex: Ranger
     * @return
     */
    String getName();

    /**
     * get the abbreviation for this class.
     * @return
     */
	String getAbbreviation();

    /**
     * gets the progression of levels for this class.
     * @return
     */
    ClassProgression getClassProgression();
    
    /**
     * gets the prereqs for this class.
     * this is used mainly for presige classes, but some core classes need 
     * this as well (ex: Barbarian cannot be lawful)
     * @return
     */
    CharacterPrerequisite getPrerequisite();

	void addClassSkill(Skill skill);
}
