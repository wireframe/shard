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
import java.util.Map;

import com.codecrate.shard.character.CharacterLevel;
import com.codecrate.shard.character.CharacterProgression;
import com.codecrate.shard.kit.CharacterClass;

/**
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class CharacterProgressionSkillEntryContainer implements SkillEntryContainer {

    private final SkillEntryContainer delegate;

    public CharacterProgressionSkillEntryContainer(CharacterProgression progression) {
        Map classesUsed = new HashMap();
        Map entries = new HashMap();
        Iterator levels = progression.getCharacterLevels().iterator();
        while (levels.hasNext()) {
            CharacterLevel level = (CharacterLevel) levels.next();
            CharacterClass kit = level.getClassLevel().getCharacterClass();
            if (null == classesUsed.get(kit)) {
                Collection grantedSkills = kit.getSkills();
                populateEntries(entries, grantedSkills);
                classesUsed.put(kit, Boolean.TRUE);
            }
            
            Collection ranks = level.getSkillRanks();
            populateEntries(entries, ranks);
        }
        delegate = new DefaultSkillEntryContainer(entries, progression.getCharacterLevel());
    }
    
    private void populateEntries(Map entries, Collection modifiers) {
        Iterator skills = modifiers.iterator();
        while (skills.hasNext()) {
            SkillModifier rank = (SkillModifier) skills.next();
            Skill skill = rank.getSkill();
            if (!entries.containsKey(skill)) {
                SkillEntry entry = new SkillEntry(skill);
                entries.put(skill, entry);
            } 
            SkillEntry skillEntry = (SkillEntry) entries.get(skill);
            skillEntry.addModifier(rank);
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
    public void addEntry(SkillEntry entry) {
        delegate.addEntry(entry);
    }
}
