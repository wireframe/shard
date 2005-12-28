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
package com.codecrate.shard.transfer.pcgen;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import org.easymock.MockControl;

import com.codecrate.shard.ability.AbilityDao;
import com.codecrate.shard.skill.Skill;
import com.codecrate.shard.skill.SkillDao;
import com.codecrate.shard.skill.SkillFactory;

public class PcgenSkillLineHandlerTest extends TestCase {

    public void testBasicImport() throws Exception {
        Map tags = new HashMap();
        tags.put("KEYSTAT", "STR");

        MockControl mockSkill = MockControl.createControl(Skill.class);
        Skill skill = (Skill) mockSkill.getMock();
        mockSkill.replay();

        MockControl mockSkillFactory = MockControl.createControl(SkillFactory.class);
        SkillFactory skillFactory = (SkillFactory) mockSkillFactory.getMock();
        skillFactory.createSkill("Climb", null, true, false, null);
        mockSkillFactory.setReturnValue(skill);
        mockSkillFactory.replay();

        MockControl mockSkillDao = MockControl.createControl(SkillDao.class);
        SkillDao skillDao = (SkillDao) mockSkillDao.getMock();
        skillDao.saveSkill(skill);
        mockSkillDao.setReturnValue(skill);
        mockSkillDao.replay();

        MockControl mockAbilityDao = MockControl.createControl(AbilityDao.class);
        AbilityDao abilityDao = (AbilityDao) mockAbilityDao.getMock();
        abilityDao.getAbilityByAbbreviation("STR");
        mockAbilityDao.setReturnValue(null);
        mockAbilityDao.replay();

        PcgenSkillLineHandler importer = new PcgenSkillLineHandler(skillFactory, skillDao, abilityDao);
		Object result = importer.handleParsedLine("Climb", tags, null);

		assertNotNull(result);
    }
}
