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

/**
 * Data access object to retrieve skills.
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public interface SkillDao {
    
    /**
     * gets untrained skills.
     * convenience method.  can use get all skills and iterate through and 
     * manually check the {@link Skill#isUsableUntrained() isUsableUntrained flag}.
     */
    Collection<Skill> getUntrainedSkills();

    /**
     * gets all skills
     * @return
     */
    Collection<Skill> getSkills();

    /**
     * saves a new skill.
     * @param skill skill to save
     * @return
     */
    Skill saveSkill(Skill skill);

    /**
     * deletes a skill.
     * @param skill
     */
    void deleteSkill(Skill skill);

    /**
     * commit changes to a skill.
     * @param skill
     */
    void updateSkill(Skill skill);

    /**
     * Get a skill by name.
     * @param name
     * @return
     */
    Skill getSkill(String name);

	Collection<Skill> searchSkills(String query);
}