package com.codecrate.shard.skill;

public class SkillEntry {

	private final Skill skill;
	private final int ranks;

	public SkillEntry(Skill skill, int ranks) {
		this.skill = skill;
		this.ranks = ranks;
	}
	
	public int getModifier() {
		return ranks;
	}
}
