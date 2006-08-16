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

import org.nfunk.jep.JEP;

import com.codecrate.shard.dice.Dice;
import com.codecrate.shard.dice.RandomDice;
import com.codecrate.shard.kit.CharacterClass;
import com.codecrate.shard.kit.CharacterClassDao;
import com.codecrate.shard.kit.CharacterClassFactory;
import com.codecrate.shard.skill.Skill;
import com.codecrate.shard.skill.SkillDao;
import com.codecrate.shard.source.Source;
import com.codecrate.shard.transfer.pcgen.tag.ConcatTagValueAggregator;
import com.codecrate.shard.transfer.pcgen.tag.PcgenTagParser;

public class PcgenCharacterClassLineHandler extends AbstractPcgenLineHandler {
	private static final String TAG_VALUE_DELIMITER = "|";
    private static final String CLASS_LEVEL_TOKEN = "CL";
    private static final int MAX_CLASS_LEVEL = 20;
    private static final String NAME = "CLASS";
    private static final String HIT_DICE = "HD";
	private static final String ABBREVIATION = "ABB";
	private static final String SKILL_LIST_TAG_NAME = "CSKILL";
    private static final String BONUS_TAG_NAME = "BONUS";
    private static final String REFLEX_DECLARATION = "Reflex";
    private static final String WILLPOWER_DECLARATION = "Willpower";
    private static final String FORTITUDE_DECLARATION = "Fortitude";
    private static final String BASE_ATTACK_BONUS_DECLARATION = "BAB";
    private static final String MAX_LEVEL_TAG = "MAXLEVEL";

	private final CharacterClassFactory kitFactory;
    private final CharacterClassDao kitDao;
	private final SkillDao skillDao;
	private static final ConcatTagValueAggregator tagValueAggregator = new ConcatTagValueAggregator(TAG_VALUE_DELIMITER);

    public PcgenCharacterClassLineHandler(CharacterClassFactory kitFactory,
			CharacterClassDao kitDao, SkillDao skillDao) {
    	super(new PcgenTagParser(tagValueAggregator));
        this.kitFactory = kitFactory;
        this.kitDao = kitDao;
		this.skillDao = skillDao;
    }

    protected String getNameToken(String line) {
		Map tags = getTagParser().parseTags(line);
    	return getStringTagValue(NAME, tags);
	}

	protected Object handleParsedLine(String name, Map tags, Source source) {
    	if (isFirstLine(tags)) {
        	Dice hitDice = new RandomDice(getIntTagValue(HIT_DICE, tags));
        	String abbreviation = getStringTagValue(ABBREVIATION, tags);
            int maxLevel = getIntTagValue(MAX_LEVEL_TAG, tags, MAX_CLASS_LEVEL);

            String bonusTokens = getStringTagValue(BONUS_TAG_NAME, tags);
            String reflexSaveProgression = getTokenAfterElement(REFLEX_DECLARATION, bonusTokens);
            String willpowerSaveProgression = getTokenAfterElement(WILLPOWER_DECLARATION, bonusTokens);
            String fortitudeSaveProgression = getTokenAfterElement(FORTITUDE_DECLARATION, bonusTokens);
            String baseAttackBonusProgression = getTokenAfterElement(BASE_ATTACK_BONUS_DECLARATION, bonusTokens);

            CharacterClass kit = kitFactory.createClass(name, abbreviation, hitDice, source);
            for (int level = 1; level <= maxLevel; level++) {
                kit.getClassProgression().addLevel(
                        calculateClassLevelExpression(baseAttackBonusProgression, level),
                        calculateClassLevelExpression(fortitudeSaveProgression, level),
                        calculateClassLevelExpression(reflexSaveProgression, level),
                        calculateClassLevelExpression(willpowerSaveProgression, level));
            }
            return kitDao.saveClass(kit);
    	} else if (isSecondLine(tags)) {
    		CharacterClass kit = kitDao.getCharacterClass(name);

    		String skills = getStringTagValue(SKILL_LIST_TAG_NAME, tags);
    		StringTokenizer tokens = tagValueAggregator.parseAggregatedValue(skills);
    		while (tokens.hasMoreTokens()) {
    			String skillName = tokens.nextToken();
                if (isValidSkillName(skillName)) {
                    Skill skill = skillDao.getSkill(skillName);
                    kit.addClassSkill(skill);
                }
    		}

    		kitDao.updateClass(kit);
    		return kit;
    	} else {
    		throw new IllegalStateException("Unable to update class information.");
    	}
    }

    private boolean isValidSkillName(String skillName) {
        return (-1 == skillName.indexOf("."));
    }

    private int calculateClassLevelExpression(String expression, int characterLevel) {
        JEP parser = new JEP();
        parser.addStandardFunctions();
        parser.addStandardConstants();
        parser.addVariable(CLASS_LEVEL_TOKEN, characterLevel);
        parser.parseExpression(expression);

        return (int) parser.getValue();
    }

    private String getTokenAfterElement(String element, String line) {
        int index = line.indexOf(element);
        int nextElementStart = line.indexOf("|", index) + 1;
        int nextElementEnd = line.indexOf("|", nextElementStart);
        if (nextElementEnd == -1) {
            nextElementEnd = line.length();
        }
        return line.substring(nextElementStart, nextElementEnd);
    }

    private boolean isSecondLine(Map tags) {
		return (null != tags.get(SKILL_LIST_TAG_NAME));
	}

	private boolean isFirstLine(Map tags) {
		return (null != tags.get(HIT_DICE));
	}
}
