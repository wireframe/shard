package com.codecrate.shard.ui.validation;

import org.springframework.rules.Rules;
import org.springframework.rules.support.DefaultRulesSource;

import com.codecrate.shard.character.DefaultCharacterLevel;
import com.codecrate.shard.character.DefaultPlayerCharacter;

public class ShardRulesSource extends DefaultRulesSource {
	public ShardRulesSource() {
		super();

		addRules(createLevelRules());
		addRules(createCharacterRules());
	}

	private Rules createCharacterRules() {
		return new Rules(DefaultPlayerCharacter.class) {
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
//				add("hitpoints", new Constraint() {
//
//					public boolean test(Object object) {
//						Integer value = (Integer) object;
//						CharacterLevel level = (CharacterLevel) object;
//						return isHitPointsWithinRange(level);
//					}
//
//					private boolean isHitPointsWithinRange(CharacterLevel level) {
//						CharacterClass kit = level.getCharacterClass();
//						if (null == kit) {
//							return true;
//						}
//						if (kit.getHitDicePerLevel().getMinValue() > level.getHitpoints()) {
//							return false;
//						}
//
//						if (kit.getHitDicePerLevel().getMaxValue() < level.getHitpoints()) {
//							return false;
//						}
//						return true;
//					}
//				});
			}
		};
	}
}