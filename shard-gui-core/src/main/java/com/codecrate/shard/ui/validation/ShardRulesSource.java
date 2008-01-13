package com.codecrate.shard.ui.validation;

import org.springframework.rules.Rules;
import org.springframework.rules.constraint.LessThanEqualTo;
import org.springframework.rules.support.DefaultRulesSource;

import com.codecrate.shard.character.DefaultCharacterLevel;
import com.codecrate.shard.character.PlayerCharacter;

public class ShardRulesSource extends DefaultRulesSource {
	public ShardRulesSource() {
		super();

		addRules(createLevelRules());
		addRules(createCharacterRules());
	}

	private Rules createCharacterRules() {
		return new Rules(PlayerCharacter.class) {
			protected void initRules() {
				addRequired("bio.name");
				addRequired("race");
			}
		};
	}

	private Rules createLevelRules() {
		return new Rules(DefaultCharacterLevel.class) {

			protected void initRules() {
				addRequired("characterClass");
				add("hitpoints", gt(0));
				add(new LenientPropertiesConstraint("hitpoints", LessThanEqualTo.instance(), "characterClass.hitDicePerLevel.maxValue"));
			}
		};
	}
}