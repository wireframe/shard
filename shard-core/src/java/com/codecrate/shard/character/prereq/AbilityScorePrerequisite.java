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
package com.codecrate.shard.character.prereq;

import com.codecrate.shard.ability.AbilityScore;
import com.codecrate.shard.character.PlayerCharacter;

/**
 * prereq for character must have a certain ability score.
 */
public class AbilityScorePrerequisite implements CharacterPrerequisite {
    private final AbilityScore abilityScore;
	
	public AbilityScorePrerequisite(AbilityScore abilityScore) {
        this.abilityScore = abilityScore;
	}
	
	public boolean hasMetPrerequisite(PlayerCharacter character) {
	    if (!character.getAbilities().hasAbilityScore(abilityScore.getAbility())) {
	        return false;
	    }
	    if (abilityScore.getModifiedValue() > character.getAbilities().getAbilityScore(abilityScore.getAbility()).getModifiedValue()) {
	        return false;
	    }
		return true;
	}
}
