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
 * Data access object to retrieve skills.
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public interface SkillDao {
    
    /**
     * gets untrained skills.
     * convenience method.  can use get all skills and iterate through and 
     * manually check the isUsableUntrained flag.
     * @return
     */
    Collection getUntrainedSkills();

    /**
     * gets all skills
     * @return
     */
    Collection getSkills();

    /**
     * creates a new skill.
     * @param name
     * @param usableUntrained
     * @param ability
     * @param armorPenalty
     * @return
     */
    Skill createSkill(String name, boolean usableUntrained, Ability ability, boolean armorPenalty);
}