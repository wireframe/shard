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

import com.codecrate.shard.ability.Ability;


/**
 * Factory for creating skills.
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public interface SkillFactory {
    
    /**
     * creates a new skill.
     * @param name name of the skill.
     * @param ability ability to use for bonus/penalty modifier
     * @param usableUntrained flag if this skill is usable untrained
     * @param penalizedWithArmor flag if this skill receives a penalty when used with armor
     * @return
     */
    Skill createSkill(String name, Ability ability, boolean usableUntrained, boolean penalizedWithArmor);
}