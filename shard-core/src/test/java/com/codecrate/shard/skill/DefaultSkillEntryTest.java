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

import junit.framework.TestCase;

import org.easymock.MockControl;

import com.codecrate.shard.modifier.DefaultModifier;
import com.codecrate.shard.modifier.DefaultModifierType;

/**
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class DefaultSkillEntryTest extends TestCase {

    public void testMultipleModifiersUsedForValue() {
        MockControl mockSkill = MockControl.createControl(Skill.class);
        Skill skill = (Skill) mockSkill.getMock();
        mockSkill.replay();

        DefaultSkillEntry entry = new DefaultSkillEntry(skill);
        entry.addModifier(new DefaultModifier(DefaultModifierType.RANK, 1));
        entry.addModifier(new DefaultModifier(DefaultModifierType.RANK, 1));
        assertEquals(2, entry.getModifiedValue());
    }
}
