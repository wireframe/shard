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

import java.util.Arrays;

import junit.framework.TestCase;

import org.easymock.MockControl;

import com.codecrate.shard.character.CharacterLevel;
import com.codecrate.shard.character.CharacterProgression;

public class CharacterProgressionSkillEntryContainerTest extends TestCase {
    
    public void testSkillEntryModifiedBySkillRanks() {
        MockControl mockLevel = MockControl.createControl(CharacterLevel.class);
        CharacterLevel level = (CharacterLevel) mockLevel.getMock();
        level.getSkillRanks();
        mockLevel.setReturnValue(Arrays.asList(new SkillModifier[] {new DefaultSkillModifier("rank", 1, DefaultSkill.SWIM)}));
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
}
