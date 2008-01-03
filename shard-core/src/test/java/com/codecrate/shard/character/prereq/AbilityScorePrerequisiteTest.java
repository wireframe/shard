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

import junit.framework.TestCase;

import org.easymock.MockControl;

import com.codecrate.shard.ability.Ability;
import com.codecrate.shard.ability.AbilityScoreContainer;
import com.codecrate.shard.ability.DefaultAbilityScore;
import com.codecrate.shard.character.PlayerCharacter;

public class AbilityScorePrerequisiteTest extends TestCase {

	public void testPrereqMetWithHigherScore() {
		MockControl mockAbilities = MockControl.createControl(AbilityScoreContainer.class);
		AbilityScoreContainer abilities = (AbilityScoreContainer) mockAbilities.getMock();
		abilities.hasAbilityScore(Ability.STRENGTH);
		mockAbilities.setReturnValue(true);
		abilities.getAbilityScore(Ability.STRENGTH);
		mockAbilities.setReturnValue(new DefaultAbilityScore(Ability.STRENGTH, 5, null));
		mockAbilities.replay();
		
		MockControl mockCharacter = MockControl.createControl(PlayerCharacter.class);
		PlayerCharacter character = (PlayerCharacter) mockCharacter.getMock();
		character.getAbilities();
		mockCharacter.setReturnValue(abilities);
		character.getAbilities();
		mockCharacter.setReturnValue(abilities);
		mockCharacter.replay();
		
		AbilityScorePrerequisite prereq = new AbilityScorePrerequisite(new DefaultAbilityScore(Ability.STRENGTH, 1, null));
		assertTrue(prereq.hasMetPrerequisite(character));
	}
	
	public void testPrereqNotMetWithLowerScore() {
		MockControl mockAbilities = MockControl.createControl(AbilityScoreContainer.class);
		AbilityScoreContainer abilities = (AbilityScoreContainer) mockAbilities.getMock();
		abilities.hasAbilityScore(Ability.STRENGTH);
		mockAbilities.setReturnValue(true);
		abilities.getAbilityScore(Ability.STRENGTH);
		mockAbilities.setReturnValue(new DefaultAbilityScore(Ability.STRENGTH, 0, null));
		mockAbilities.replay();
		
		MockControl mockCharacter = MockControl.createControl(PlayerCharacter.class);
		PlayerCharacter character = (PlayerCharacter) mockCharacter.getMock();
		character.getAbilities();
		mockCharacter.setReturnValue(abilities);
		character.getAbilities();
		mockCharacter.setReturnValue(abilities);
		mockCharacter.replay();
		
		AbilityScorePrerequisite prereq = new AbilityScorePrerequisite(new DefaultAbilityScore(Ability.STRENGTH, 1, null));
		assertFalse(prereq.hasMetPrerequisite(character));
	}
	
	public void testPrereqNotMetWithoutScore() {
		MockControl mockAbilities = MockControl.createControl(AbilityScoreContainer.class);
		AbilityScoreContainer abilities = (AbilityScoreContainer) mockAbilities.getMock();
		abilities.hasAbilityScore(Ability.STRENGTH);
		mockAbilities.setReturnValue(false);
		mockAbilities.replay();
		
		MockControl mockCharacter = MockControl.createControl(PlayerCharacter.class);
		PlayerCharacter character = (PlayerCharacter) mockCharacter.getMock();
		character.getAbilities();
		mockCharacter.setReturnValue(abilities);
		mockCharacter.replay();
		
		AbilityScorePrerequisite prereq = new AbilityScorePrerequisite(new DefaultAbilityScore(Ability.STRENGTH, 1, null));
		assertFalse(prereq.hasMetPrerequisite(character));
	}
}
