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
package com.codecrate.shard.skill;

import java.util.Collection;

import com.codecrate.shard.ability.Ability;

/**
 * Definition of a Skill.
 * A skill is something that every character class has access to.  For each class 
 * a skill is either a class skill, or a cross class skill.  If a skill should not
 * be available to a class, it should be redefined as a Feat.
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public interface Skill {
	
    /**
     * gets the ability used for ability bonus for skill checks.
     * @return
     */
	Ability getAbility();
	
	/**
	 * gets the name of the skill.
	 * ex: "Disguise Self"
	 * @return
	 */
	String getName();
	
	boolean isUsableUntrained();
	
	/**
	 * gets the skills that this skill grants a synergy bonus to.
	 * a skill entry needs 5+ ranks in this skill 
	 * to grant a +2 bonus on the child skills.
	 * ex: if 5+ ranks in Tumble, +2 bonus for Jump 
	 * @return
	 */
	Collection getChildSkillSynergies();

    /**
     * boolean flag for if the skill has an armor check penalty.
     * @return
     */
    boolean isPenalizedWithArmor();
}
