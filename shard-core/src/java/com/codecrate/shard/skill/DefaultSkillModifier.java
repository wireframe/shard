package com.codecrate.shard.skill;

public class DefaultSkillModifier implements SkillModifier {
	public static final String TYPE_RACE = "race";
	public static final String TYPE_RANK = "rank";
	public static final String TYPE_ABILITY = "ability";
	
	
	private final String type;
	private final int value;
	private final Skill skill;

	public DefaultSkillModifier(String type, int value, Skill skill) {
		this.type = type;
		this.value = value;
		this.skill = skill;
		
	}
	
	public String getType() {
		return type;
	}

	public int getValue() {
		return value;
	}

	public Skill getSkill() {
		return skill;
	}
}
