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

import java.util.ArrayList;
import java.util.Arrays;

import junit.framework.TestCase;

import org.easymock.MockControl;

import com.codecrate.shard.character.CharacterLevel;
import com.codecrate.shard.character.CharacterProgression;
import com.codecrate.shard.kit.DefaultCharacterClass;
import com.codecrate.shard.kit.DefaultClassLevel;

public class CharacterProgressionSkillEntryContainerTest extends TestCase {
    
    public void testNewSkillEntryModifiedBySkillRanks() {
        MockControl mockLevel = MockControl.createControl(CharacterLevel.class);
        CharacterLevel level = (CharacterLevel) mockLevel.getMock();
        level.getClassLevel();
        mockLevel.setReturnValue(new DefaultClassLevel(1, DefaultCharacterClass.BARBARIAN, 1, 1, 1, 1));
        level.getSkillRanks();
        mockLevel.setReturnValue(Arrays.asList(new SkillModifier[] {new DefaultSkillModifier(DefaultSkillModifierType.RANK, 1, DefaultSkill.SWIM)}));
        mockLevel.replay();
        
        MockControl mockProgression = MockControl.createControl(CharacterProgression.class);
        CharacterProgression progression = (CharacterProgression) mockProgression.getMock();
        progression.getCharacterLevel();
        mockProgression.setReturnValue(1);
        progression.getCharacterLevels();
        mockProgression.setReturnValue(Arrays.asList(new CharacterLevel[] {level}));
        mockProgression.replay();
        
        CharacterProgressionSkillEntryContainer container = new CharacterProgressionSkillEntryContainer(progression);
        assertTrue(container.hasSkill(DefaultSkill.SWIM));
        assertEquals(1, container.getSkillEntry(DefaultSkill.SWIM).getValue());
    }
    
    public void testSkillEntryModifiedBySkillRanks() {
        MockControl mockLevel1 = MockControl.createControl(CharacterLevel.class);
        CharacterLevel level1 = (CharacterLevel) mockLevel1.getMock();
        level1.getClassLevel();
        mockLevel1.setReturnValue(new DefaultClassLevel(1, DefaultCharacterClass.BARBARIAN, 1, 1, 1, 1));
        level1.getSkillRanks();
        mockLevel1.setReturnValue(Arrays.asList(new SkillModifier[] {new DefaultSkillModifier(DefaultSkillModifierType.RANK, 1, DefaultSkill.SWIM)}));
        mockLevel1.replay();

        MockControl mockLevel2 = MockControl.createControl(CharacterLevel.class);
        CharacterLevel level2 = (CharacterLevel) mockLevel2.getMock();
        level2.getClassLevel();
        mockLevel2.setReturnValue(new DefaultClassLevel(1, DefaultCharacterClass.BARBARIAN, 1, 1, 1, 1));
        level2.getSkillRanks();
        mockLevel2.setReturnValue(Arrays.asList(new SkillModifier[] {new DefaultSkillModifier(DefaultSkillModifierType.RANK, 1, DefaultSkill.SWIM)}));
        mockLevel2.replay();

        MockControl mockProgression = MockControl.createControl(CharacterProgression.class);
        CharacterProgression progression = (CharacterProgression) mockProgression.getMock();
        progression.getCharacterLevel();
        mockProgression.setReturnValue(1);
        progression.getCharacterLevels();
        mockProgression.setReturnValue(Arrays.asList(new CharacterLevel[] {level1, level2}));
        mockProgression.replay();
        
        CharacterProgressionSkillEntryContainer container = new CharacterProgressionSkillEntryContainer(progression);
        assertTrue(container.hasSkill(DefaultSkill.SWIM));
        assertEquals(2, container.getSkillEntry(DefaultSkill.SWIM).getValue());
    }
    
    public void testSkillEntryModifiedByClassSkills() {
        MockControl mockLevel = MockControl.createControl(CharacterLevel.class);
        CharacterLevel level = (CharacterLevel) mockLevel.getMock();
        level.getClassLevel();
        mockLevel.setReturnValue(new DefaultClassLevel(1, DefaultCharacterClass.FIGHTER, 1, 1, 1, 1));
        level.getSkillRanks();
        mockLevel.setReturnValue(new ArrayList());
        mockLevel.replay();

        MockControl mockProgression = MockControl.createControl(CharacterProgression.class);
        CharacterProgression progression = (CharacterProgression) mockProgression.getMock();
        progression.getCharacterLevel();
        mockProgression.setReturnValue(1);
        progression.getCharacterLevels();
        mockProgression.setReturnValue(Arrays.asList(new CharacterLevel[] {level}));
        mockProgression.replay();
        
        CharacterProgressionSkillEntryContainer container = new CharacterProgressionSkillEntryContainer(progression);
        assertTrue(container.hasSkill(DefaultSkill.LITERACY));
        assertEquals(1, container.getSkillEntry(DefaultSkill.LITERACY).getValue());
    }
    
    public void testSkillEntryModifiedByClassSkillsOnlyOnce() {
        MockControl mockLevel1 = MockControl.createControl(CharacterLevel.class);
        CharacterLevel level1 = (CharacterLevel) mockLevel1.getMock();
        level1.getClassLevel();
        mockLevel1.setReturnValue(new DefaultClassLevel(1, DefaultCharacterClass.FIGHTER, 1, 1, 1, 1));
        level1.getSkillRanks();
        mockLevel1.setReturnValue(new ArrayList());
        mockLevel1.replay();

        MockControl mockLevel2 = MockControl.createControl(CharacterLevel.class);
        CharacterLevel level2 = (CharacterLevel) mockLevel2.getMock();
        level2.getClassLevel();
        mockLevel2.setReturnValue(new DefaultClassLevel(1, DefaultCharacterClass.FIGHTER, 1, 1, 1, 1));
        level2.getSkillRanks();
        mockLevel2.setReturnValue(new ArrayList());
        mockLevel2.replay();

        MockControl mockProgression = MockControl.createControl(CharacterProgression.class);
        CharacterProgression progression = (CharacterProgression) mockProgression.getMock();
        progression.getCharacterLevel();
        mockProgression.setReturnValue(1);
        progression.getCharacterLevels();
        mockProgression.setReturnValue(Arrays.asList(new CharacterLevel[] {level1, level2}));
        mockProgression.replay();
        
        CharacterProgressionSkillEntryContainer container = new CharacterProgressionSkillEntryContainer(progression);
        assertTrue(container.hasSkill(DefaultSkill.LITERACY));
        assertEquals(1, container.getSkillEntry(DefaultSkill.LITERACY).getValue());
    }
}
