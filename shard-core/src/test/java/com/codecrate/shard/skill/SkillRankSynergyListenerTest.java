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

/**
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class SkillRankSynergyListenerTest extends TestCase {

    public void testSynergyBonusAddedWhenRankAboveThreshold() {
        MockControl mockChildSkill = MockControl.createControl(Skill.class);
        Skill childSkill = (Skill) mockChildSkill.getMock();
        mockChildSkill.replay();

        MockControl mockSkill = MockControl.createControl(Skill.class);
        Skill skill = (Skill) mockSkill.getMock();
        skill.getChildSkillSynergies();
        mockSkill.setReturnValue(Arrays.asList(new Skill[] {childSkill}));
        mockSkill.replay();

        MockControl mockEntry = MockControl.createControl(SkillEntry.class);
        SkillEntry entry = (SkillEntry) mockEntry.getMock();
        entry.addListener(null);
        mockEntry.setMatcher(MockControl.ALWAYS_MATCHER);
        mockEntry.setVoidCallable();
        entry.getRank();
        mockEntry.setReturnValue(2);
        entry.getRank();
        mockEntry.setReturnValue(5);
        entry.getSkill();
        mockEntry.setReturnValue(skill);
        mockEntry.replay();

        MockControl mockContainer = MockControl.createControl(SkillEntryContainer.class);
        SkillEntryContainer container = (SkillEntryContainer) mockContainer.getMock();
        container.addModifier(null);
        mockContainer.setMatcher(MockControl.ALWAYS_MATCHER);
        mockContainer.setVoidCallable();
        mockContainer.replay();

        SkillRankSynergyListener listener = new SkillRankSynergyListener(entry, container);
        listener.onModify();

        mockContainer.verify();
    }
}
