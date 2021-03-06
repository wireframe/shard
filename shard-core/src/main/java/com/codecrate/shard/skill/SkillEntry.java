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

import com.codecrate.shard.modifier.Modifiable;

/**
 * Represents a skill entry for a particular character.
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public interface SkillEntry extends Modifiable {
    /**
     * gets the skill this entry is for.
     * @return
     */
    Skill getSkill();

    /**
     * gets the rank currently held for this skill.
     * @return
     */
    int getRank();

    /**
     * roll a skill check against a difficulty class.
     * @param dc
     * @return
     */
	boolean rollSkillCheck(DifficultyClass dc);
}