package com.codecrate.shard.skill;

public interface SkillModifier {

	String getType();
	
	int getValue();

	/**
	 * gets the skill that this modifier applies to.
	 * @return
	 */
	Skill getSkill();
}
