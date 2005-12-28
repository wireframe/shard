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
import java.util.StringTokenizer;

import com.codecrate.shard.dice.Dice;
import com.codecrate.shard.dice.RandomDice;
import com.codecrate.shard.kit.CharacterClass;
import com.codecrate.shard.kit.CharacterClassDao;
import com.codecrate.shard.kit.CharacterClassFactory;
import com.codecrate.shard.skill.Skill;
import com.codecrate.shard.skill.SkillDao;
import com.codecrate.shard.source.Source;
import com.codecrate.shard.transfer.pcgen.tag.PcgenTokenTagParser;

public class PcgenCharacterClassLineHandler extends AbstractPcgenLineHandler {
    private static final String NAME = "CLASS";
    private static final String HIT_DICE = "HD";
	private static final String ABBREVIATION = "ABB";
	private static final String SKILL_LIST_TAG_NAME = "CSKILL";

	private final CharacterClassFactory kitFactory;
    private final CharacterClassDao kitDao;
	private final SkillDao skillDao;

    public PcgenCharacterClassLineHandler(CharacterClassFactory kitFactory,
			CharacterClassDao kitDao, SkillDao skillDao) {
        this.kitFactory = kitFactory;
        this.kitDao = kitDao;
		this.skillDao = skillDao;
    }

    public Object handleLine(String line, Source source) {
    	Map tags = new PcgenTokenTagParser("\t").parseTags(line);
    	String name = getStringTagValue(NAME, tags);

    	return handleParsedLine(name, tags, source);
    }

    public Object handleParsedLine(String name, Map tags, Source source) {
    	if (isFirstLine(tags)) {
        	Dice hitDice = new RandomDice(getIntTagValue(HIT_DICE, tags));
        	String abbreviation = getStringTagValue(ABBREVIATION, tags);

            CharacterClass kit = kitFactory.createClass(name, abbreviation, hitDice);
            return kitDao.saveClass(kit);
    	} else if (isSecondLine(tags)) {
    		CharacterClass kit = kitDao.getCharacterClass(name);

    		String skills = getStringTagValue(SKILL_LIST_TAG_NAME, tags);
    		StringTokenizer tokens = new StringTokenizer(skills, "|");
    		while (tokens.hasMoreTokens()) {
    			String skillName = tokens.nextToken();
        		Skill skill = skillDao.getSkill(skillName);
        		kit.addClassSkill(skill);
    		}

    		kitDao.updateClass(kit);
    		return kit;
    	} else {
    		throw new IllegalStateException("Unable to update class information.");
    	}
    }

	private boolean isSecondLine(Map tags) {
		return (null != tags.get(SKILL_LIST_TAG_NAME));
	}

	private boolean isFirstLine(Map tags) {
		return (null != tags.get(HIT_DICE));
	}
}
