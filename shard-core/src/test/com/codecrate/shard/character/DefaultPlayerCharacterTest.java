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

import java.util.Arrays;

import junit.framework.TestCase;

import org.easymock.MockControl;

import com.codecrate.shard.kit.CharacterClass;
import com.codecrate.shard.kit.ClassLevel;
import com.codecrate.shard.kit.DefaultCharacterClass;
import com.codecrate.shard.race.DefaultRacialSize;
import com.codecrate.shard.race.Race;

public class DefaultPlayerCharacterTest extends TestCase {

	public void testEffectiveCharacterLevelCalculation() {
		MockControl mockRace = MockControl.createControl(Race.class);
		Race race = (Race) mockRace.getMock();
		race.getLevelAdjustment();
		mockRace.setReturnValue(1);
		mockRace.replay();

		MockControl mockProgression = MockControl.createControl(CharacterProgression.class);
		CharacterProgression progression = (CharacterProgression) mockProgression.getMock();
		progression.getCharacterLevel();
		mockProgression.setReturnValue(1);
		mockProgression.replay();
	    
		DefaultPlayerCharacter character = new DefaultPlayerCharacter("name", race, null, null, null, null, null, null, null, progression, null, null, 0, null, null, null, null);
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
		
		MockControl mockProgression = MockControl.createControl(CharacterProgression.class);
		CharacterProgression progression = (CharacterProgression) mockProgression.getMock();
		progression.getClasses();
		mockProgression.setReturnValue(Arrays.asList(new CharacterClass[] {DefaultCharacterClass.BARBARIAN, DefaultCharacterClass.BARD}));
		progression.getClassLevel(DefaultCharacterClass.BARBARIAN);
		mockProgression.setReturnValue(classLevel);
		progression.getClassLevel(DefaultCharacterClass.BARD);
		mockProgression.setReturnValue(classLevel);
		mockProgression.replay();
	    
		MockControl mockRace = MockControl.createControl(Race.class);
		Race race = (Race) mockRace.getMock();
		race.getSize();
		mockRace.setReturnValue(DefaultRacialSize.SMALL);
		mockRace.replay();
		
		DefaultPlayerCharacter character = new DefaultPlayerCharacter("name", race, null, null, null, null, null, null, null, progression, null, null, 0, null, null, null, null);
		assertEquals(3, character.getBaseAttackBonus());
	}
}
