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

import java.util.Map;

import com.codecrate.shard.skill.Skill;
import com.codecrate.shard.skill.SkillDao;
import com.codecrate.shard.skill.SkillFactory;

public class PcgenSkillLineHandler extends AbstractPcgenLineHandler {
	private static final String ARMOR_CHECK_PENALTY_TAG_NAME = "ACHECK";
	private static final String USABLE_UNTRAINED_TAG_NAME = "USEUNTRAINED";
	private static final String ABILITY_TAG_NAME = "KEYSTAT";

    private final SkillFactory skillFactory;
    private final SkillDao skillDao;

    public PcgenSkillLineHandler(SkillFactory skillFactory, SkillDao skillDao) {
        this.skillFactory = skillFactory;
        this.skillDao = skillDao;
    }

    public Object handleParsedLine(String name, Map tags) {
    	boolean isUsableUntrained = getBooleanTagValue(USABLE_UNTRAINED_TAG_NAME, tags, true);
    	boolean hasArmorCheckPenalty = getBooleanTagValue(ARMOR_CHECK_PENALTY_TAG_NAME, tags, false);
    	String abilityName = getStringTagValue(ABILITY_TAG_NAME, tags);

        Skill skill = skillFactory.createSkill(name);
        return skillDao.saveSkill(skill);
    }
}
