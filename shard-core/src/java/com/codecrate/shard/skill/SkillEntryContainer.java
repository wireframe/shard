package com.codecrate.shard.skill;

import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SkillEntryContainer {
	private static final Log LOG = LogFactory.getLog(SkillEntryContainer.class);

	public Collection getSkills() {
		return null;
	}
	
	public boolean hasSkill(Skill skill) {
		SkillEntry entry = getSkillEntry(skill);
		if (null == entry) {
			return false;
		}
		return true;
	}
	
	public SkillEntry getSkillEntry(Skill skill) {
		SkillEntry entry = null;
		if (null == entry) {
			LOG.debug("No skill entry found for skill: " + skill);
		}
		return entry;
	}
	
	public int getMaxClassSkillLevel() {
		return 0;
	}
	
	public int getMaxCrossClassSkillLevel() {
		return 0;
	}
}
