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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PcgenSkillImporter {
	private static final Log LOG = LogFactory.getLog(PcgenSkillImporter.class);
	
	private static final String ARMOR_CHECK_PENALTY_TAG_NAME = "ACHECK";
	private static final String USABLE_UNTRAINED_TAG_NAME = "USEUNTRAINED";
	private static final String ABILITY_TAG_NAME = "KEYSTAT";

	private static final String TRUE_TAG_VALUE = "YES";
	
//    private final SkillFactory skillFactory;
//    private final SkillDao skillDao;
//
//    public PcgenSkillImporter(SkillFactory skillFactory, SkillDao skillDao) {
//        this.skillFactory = skillFactory;
//        this.skillDao = skillDao;
//    }
//
    public Collection importSkills(File file) throws Exception {
        Collection results = new ArrayList();
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

        while (reader.ready()) {
            String line = reader.readLine();
            if (isUsableRow(line)) {
                parseLine(line);
            }
        }

        reader.close();
        return results;
    }

    private void parseLine(String line) {
        StringTokenizer tokens = new StringTokenizer(line, "\t");

        String name = tokens.nextToken().trim();
        Map tags = new HashMap();

        while (tokens.hasMoreTokens()) {
            String token = tokens.nextToken().trim();

            String[] splitTags = token.split(":");
            String tagName = splitTags[0].trim();
            String tagValue = splitTags[1].trim();

            tags.put(tagName, tagValue);
        }

        handleParsedLine(name, tags);
    }

    private void handleParsedLine(String name, Map tags) {
    	boolean isUsableUntrained = getBooleanTagValue(USABLE_UNTRAINED_TAG_NAME, tags, true);
    	boolean hasArmorCheckPenalty = getBooleanTagValue(ARMOR_CHECK_PENALTY_TAG_NAME, tags, false);

        //Skill skill = skillFactory.createSkill(name);
        //return skillDao.saveSkill(skill));
    }

    private boolean getBooleanTagValue(String tagName, Map tags, boolean defaultValue) {
    	String value = (String) tags.get(tagName);
    	if (null == value) {
    		LOG.info("No value found for tag " + tagName + " defaulting to " + defaultValue);
    		return defaultValue;
    	}
    	return (TRUE_TAG_VALUE.equals(value));
	}

	private boolean isUsableRow(String value) {
        return (!isEmpty(value) && !value.startsWith("SOURCE"));
    }

    private boolean isEmpty(String value) {
        return (null == value || value.trim().length() < 1  || value.startsWith("#"));
    }
}
