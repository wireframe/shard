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
import java.util.HashMap;
import java.util.Iterator;

import com.codecrate.shard.character.CharacterLevel;
import com.codecrate.shard.character.CharacterProgression;
import com.codecrate.shard.kit.CharacterClass;
import com.codecrate.shard.modifier.KeyedModifier;

/**
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class CharacterProgressionSkillEntryContainer implements SkillEntryContainer {

    private final SkillEntryContainer delegate;

    public CharacterProgressionSkillEntryContainer(CharacterProgression progression) {
        delegate = new DefaultSkillEntryContainer(new HashMap(), progression.getCharacterLevel());

        Iterator kits = progression.getClasses().iterator();
        while (kits.hasNext()) {
            CharacterClass kit = (CharacterClass) kits.next();
            Collection grantedSkills = kit.getSkills();
            populateEntries(grantedSkills);
        }

        Iterator levels = progression.getCharacterLevels().iterator();
        while (levels.hasNext()) {
            CharacterLevel level = (CharacterLevel) levels.next();

            Collection ranks = level.getSkillRanks();
            populateEntries(ranks);
        }
    }

    private void populateEntries(Collection modifiers) {
        Iterator skills = modifiers.iterator();
        while (skills.hasNext()) {
            KeyedModifier rank = (KeyedModifier) skills.next();
            addModifier(rank);
        }
    }

    public int getMaxClassSkillLevel() {
        return delegate.getMaxClassSkillLevel();
    }
    public int getMaxCrossClassSkillLevel() {
        return delegate.getMaxCrossClassSkillLevel();
    }
    public SkillEntry getSkillEntry(Skill skill) {
        return delegate.getSkillEntry(skill);
    }
    public Collection getSkills() {
        return delegate.getSkills();
    }
    public boolean hasSkill(Skill skill) {
        return delegate.hasSkill(skill);
    }
    public void addModifier(KeyedModifier modifier) {
        delegate.addModifier(modifier);
    }
    public void removeModifier(KeyedModifier modifier) {
        delegate.removeModifier(modifier);
    }
	public boolean rollSkillCheck(Skill skill, DifficultyClass dc) {
		return delegate.rollSkillCheck(skill, dc);
	}
}
