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

import com.codecrate.shard.character.PlayerCharacter;

public class BaseAttackBonusPrerequisiteTest extends TestCase {

	public void testPrereqMetWhenBonusEquals() {
		MockControl mockCharacter = MockControl.createControl(PlayerCharacter.class);
		PlayerCharacter character = (PlayerCharacter) mockCharacter.getMock();
		character.getBaseAttackBonus();
		mockCharacter.setReturnValue(2);
		mockCharacter.replay();
		
		BaseAttackBonusPrerequisite prereq = new BaseAttackBonusPrerequisite(2);
		assertTrue(prereq.hasMetPrerequisite(character));
	}
	
	public void testPrereqMetWhenBonusGreater() {
		MockControl mockCharacter = MockControl.createControl(PlayerCharacter.class);
		PlayerCharacter character = (PlayerCharacter) mockCharacter.getMock();
		character.getBaseAttackBonus();
		mockCharacter.setReturnValue(3);
		mockCharacter.replay();
		
		BaseAttackBonusPrerequisite prereq = new BaseAttackBonusPrerequisite(2);
		assertTrue(prereq.hasMetPrerequisite(character));
	}

	public void testPrereqNotMetWhenBaseAttackBonusLess() {
		MockControl mockCharacter = MockControl.createControl(PlayerCharacter.class);
		PlayerCharacter character = (PlayerCharacter) mockCharacter.getMock();
		character.getBaseAttackBonus();
		mockCharacter.setReturnValue(1);
		mockCharacter.replay();
		
		BaseAttackBonusPrerequisite prereq = new BaseAttackBonusPrerequisite(2);
		assertFalse(prereq.hasMetPrerequisite(character));
	}
}
