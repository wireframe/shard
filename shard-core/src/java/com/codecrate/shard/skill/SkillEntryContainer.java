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

import java.util.Collection;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SkillEntryContainer {
	private static final Log LOG = LogFactory.getLog(SkillEntryContainer.class);

	private final int characterLevel;
	private final Map skills;
	
	public SkillEntryContainer(Map skills, int characterLevel) {
		this.skills = skills;
		this.characterLevel = characterLevel;
	}
	
	public Collection getSkills() {
		return skills.values();
	}
	
	public boolean hasSkill(Skill skill) {
		SkillEntry entry = getSkillEntry(skill);
		if (null == entry) {
			return false;
		}
		return true;
	}
	
	public SkillEntry getSkillEntry(Skill skill) {
		SkillEntry entry = (SkillEntry) skills.get(skill);
		if (null == entry) {
			LOG.debug("No skill entry found for skill: " + skill);
		}
		return entry;
	}
	
	public int getMaxClassSkillLevel() {
		return 3 + characterLevel;
	}
	
	public int getMaxCrossClassSkillLevel() {
		return getMaxClassSkillLevel() / 2;
	}
}
