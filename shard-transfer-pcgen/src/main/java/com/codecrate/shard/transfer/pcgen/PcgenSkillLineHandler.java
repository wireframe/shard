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

import com.codecrate.shard.ability.Ability;
import com.codecrate.shard.ability.AbilityDao;
import com.codecrate.shard.skill.Skill;
import com.codecrate.shard.skill.SkillDao;
import com.codecrate.shard.skill.SkillFactory;
import com.codecrate.shard.source.Source;
import com.codecrate.shard.transfer.pcgen.tag.PcgenTags;

public class PcgenSkillLineHandler extends AbstractPcgenLineHandler {
	private static final String ARMOR_CHECK_PENALTY_TAG_NAME = "ACHECK";
	private static final String USABLE_UNTRAINED_TAG_NAME = "USEUNTRAINED";
	private static final String ABILITY_TAG_NAME = "KEYSTAT";

    private final SkillFactory skillFactory;
    private final SkillDao skillDao;
	private final AbilityDao abilityDao;

    public PcgenSkillLineHandler(SkillFactory skillFactory, SkillDao skillDao, AbilityDao abilityDao) {
        this.skillFactory = skillFactory;
        this.skillDao = skillDao;
		this.abilityDao = abilityDao;
    }

    protected Object handleParsedLine(String name, PcgenTags tags, Source source) {
    	boolean isUsableUntrained = tags.getBooleanTagValue(USABLE_UNTRAINED_TAG_NAME, true);
    	boolean hasArmorCheckPenalty = tags.getBooleanTagValue(ARMOR_CHECK_PENALTY_TAG_NAME, false);
    	String abilityName = tags.getStringTagValue(ABILITY_TAG_NAME);

    	Ability ability = abilityDao.getAbilityByAbbreviation(abilityName);
        Skill skill = skillFactory.createSkill(name, ability, isUsableUntrained, hasArmorCheckPenalty, source);
        return skillDao.saveSkill(skill);
    }
}
