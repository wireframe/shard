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

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nfunk.jep.JEP;

import com.codecrate.shard.dice.Dice;
import com.codecrate.shard.dice.RandomDice;
import com.codecrate.shard.kit.CharacterClass;
import com.codecrate.shard.kit.CharacterClassDao;
import com.codecrate.shard.kit.CharacterClassFactory;
import com.codecrate.shard.skill.Skill;
import com.codecrate.shard.skill.SkillDao;
import com.codecrate.shard.source.Source;
import com.codecrate.shard.transfer.pcgen.tag.PcgenTags;

public class PcgenCharacterClassLineHandler implements PcgenObjectImporter.PcgenLineHandler {
	private static final Log LOG = LogFactory.getLog(PcgenCharacterClassLineHandler.class);
    private static final String CLASS_LEVEL_TOKEN = "CL";
    private static final int MAX_CLASS_LEVEL = 20;
    private static final String NAME = "CLASS";
    private static final String HIT_DICE = "HD";
	private static final String ABBREVIATION = "ABB";
	private static final String SKILL_LIST_TAG_NAME = "CSKILL";
    private static final String BONUS_TAG_NAME = "BONUS";
    private static final String REFLEX_DECLARATION = "Reflex";
    private static final String WILLPOWER_DECLARATION = "Will";
    private static final String FORTITUDE_DECLARATION = "Fortitude";
    private static final String BASE_ATTACK_BONUS_DECLARATION = "BAB";
    private static final String MAX_LEVEL_TAG = "MAXLEVEL";

	private final CharacterClassFactory kitFactory;
    private final CharacterClassDao kitDao;
	private final SkillDao skillDao;

	private String currentKit = null;

	private final Collection parsedLineHandlers = Arrays.asList(new PcgenCharacterClassParsedLineHandler[] {
			new PcgenCharacterClassMasterParsedLineHandler()
			, new PcgenCharacterClassSkillsParsedLineHandler()
	});

    public PcgenCharacterClassLineHandler(CharacterClassFactory kitFactory,
			CharacterClassDao kitDao, SkillDao skillDao) {
        this.kitFactory = kitFactory;
        this.kitDao = kitDao;
		this.skillDao = skillDao;
    }

    public Object handleLine(String line, Source source) {
    	PcgenTags tags = new PcgenTags(line);
    	String name = tags.getStringTagValue(NAME);
    	if (null != name && !name.equals(currentKit)) {
    		currentKit = name;
    	}

    	for (Iterator iter = parsedLineHandlers.iterator(); iter.hasNext();) {
			PcgenCharacterClassParsedLineHandler handler = (PcgenCharacterClassParsedLineHandler) iter.next();
			if (handler.supportsLine(tags)) {
				return handler.handleParsedLine(name, tags, source);
			}
		}
    	LOG.info("No handler found to process line for class " + currentKit + ": " + line);
    	return null;
    }


	public interface PcgenCharacterClassParsedLineHandler {
		boolean supportsLine(PcgenTags tags);

		Object handleParsedLine(String name, PcgenTags tags, Source source);
	}

	public class PcgenCharacterClassMasterParsedLineHandler implements PcgenCharacterClassParsedLineHandler {

		public Object handleParsedLine(String name, PcgenTags tags, Source source) {
        	Dice hitDice = new RandomDice(tags.getIntTagValue(HIT_DICE));
        	String abbreviation = tags.getStringTagValue(ABBREVIATION);
            int maxLevel = tags.getIntTagValue(MAX_LEVEL_TAG, MAX_CLASS_LEVEL);

            String reflexSaveProgression = tags.getTagValueAfterElement(BONUS_TAG_NAME, REFLEX_DECLARATION);
            String willpowerSaveProgression = tags.getTagValueAfterElement(BONUS_TAG_NAME, WILLPOWER_DECLARATION);
            String fortitudeSaveProgression = tags.getTagValueAfterElement(BONUS_TAG_NAME, FORTITUDE_DECLARATION);
            String baseAttackBonusProgression = tags.getTagValueAfterElement(BONUS_TAG_NAME, BASE_ATTACK_BONUS_DECLARATION);

            CharacterClass kit = kitFactory.createClass(name, abbreviation, hitDice, source);
            for (int level = 1; level <= maxLevel; level++) {
                kit.getClassProgression().addLevel(
                        calculateClassLevelExpression(baseAttackBonusProgression, level),
                        calculateClassLevelExpression(fortitudeSaveProgression, level),
                        calculateClassLevelExpression(reflexSaveProgression, level),
                        calculateClassLevelExpression(willpowerSaveProgression, level));
            }

            return kitDao.saveClass(kit);
		}

		public boolean supportsLine(PcgenTags tags) {
			return tags.hasTag(HIT_DICE);
		}

	    private int calculateClassLevelExpression(String expression, int characterLevel) {
	        JEP parser = new JEP();
	        parser.addStandardFunctions();
	        parser.addStandardConstants();
	        parser.addVariable(CLASS_LEVEL_TOKEN, characterLevel);
	        parser.parseExpression(expression);

	        return (int) parser.getValue();
	    }
	}

	public class PcgenCharacterClassSkillsParsedLineHandler implements PcgenCharacterClassParsedLineHandler {

		public Object handleParsedLine(String name, PcgenTags tags, Source source) {
    		CharacterClass kit = kitDao.getCharacterClass(name);

    		StringTokenizer tokens = tags.getTagValues(SKILL_LIST_TAG_NAME);
    		while (tokens.hasMoreTokens()) {
    			String skillName = tokens.nextToken();
                if (isValidSkillName(skillName)) {
                    Skill skill = skillDao.getSkill(skillName);
                    kit.addClassSkill(skill);
                }
    		}

    		kitDao.updateClass(kit);
    		return kit;
		}

		public boolean supportsLine(PcgenTags tags) {
	    	return tags.hasTag(SKILL_LIST_TAG_NAME);
		}

	    private boolean isValidSkillName(String skillName) {
	        return (-1 == skillName.indexOf("."));
	    }
	}
}
