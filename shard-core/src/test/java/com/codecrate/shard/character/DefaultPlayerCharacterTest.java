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
package com.codecrate.shard.character;

import java.util.ArrayList;
import java.util.Collections;

import junit.framework.TestCase;

import org.easymock.MockControl;

import com.codecrate.shard.ability.AbilityScoreContainer;
import com.codecrate.shard.ability.DefaultAbilityScoreContainer;
import com.codecrate.shard.kit.CharacterClass;
import com.codecrate.shard.kit.ClassLevel;
import com.codecrate.shard.kit.DefaultCharacterClass;
import com.codecrate.shard.movement.DefaultEncumberance;
import com.codecrate.shard.movement.Encumberance;
import com.codecrate.shard.race.Race;
import com.codecrate.shard.race.RacialSize;

public class DefaultPlayerCharacterTest extends TestCase {

	public void testEffectiveCharacterLevelCalculation() {
		MockControl mockRace = MockControl.createControl(Race.class);
		Race race = (Race) mockRace.getMock();
		race.getLevelAdjustment();
		mockRace.setReturnValue(1);
		mockRace.replay();

		AbilityScoreContainer abilities = DefaultAbilityScoreContainer.averageScores(null);
		Encumberance encumberance = DefaultEncumberance.LIGHT;

        DefaultCharacterClass kit = new DefaultCharacterClass("test kit", "", null, 0, null);
        kit.getClassProgression().addLevel(1, 2, 3, 4);

		DefaultPlayerCharacter character = new DefaultPlayerCharacter(abilities, race, null, encumberance, null, null, null);
        character.getCharacterProgression().addLevel(kit, 1, new ArrayList());

		assertEquals(2, character.getEffectiveCharacterLevel());
	}

	public void testBaseAttackBonusCalculation() {
		MockControl mockClassLevel = MockControl.createControl(ClassLevel.class);
		ClassLevel classLevel = (ClassLevel) mockClassLevel.getMock();
		classLevel.getBaseAttackBonus();
		mockClassLevel.setReturnValue(1);
		classLevel.getBaseAttackBonus();
		mockClassLevel.setReturnValue(1);
		mockClassLevel.replay();

        DefaultCharacterClass kit = new DefaultCharacterClass("test kit", "", null, 0, null);
        kit.getClassProgression().addLevel(2, 2, 3, 4);

        MockControl mockSize = MockControl.createControl(RacialSize.class);
        RacialSize size = (RacialSize) mockSize.getMock();
        size.getBaseAttackBonusModifier();
        mockSize.setReturnValue(1);
        mockSize.replay();

		MockControl mockRace = MockControl.createControl(Race.class);
		Race race = (Race) mockRace.getMock();
		race.getSize();
		mockRace.setReturnValue(size);
		mockRace.replay();

		AbilityScoreContainer abilities = DefaultAbilityScoreContainer.averageScores(null);
		Encumberance encumberance = DefaultEncumberance.LIGHT;
		DefaultPlayerCharacter character = new DefaultPlayerCharacter(abilities, race, null, encumberance, null, null, null);
        character.getCharacterProgression().addLevel(kit, 1, new ArrayList());

		assertEquals(3, character.getBaseAttackBonus());
	}
}
