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

import com.codecrate.shard.character.Alignment;
import com.codecrate.shard.character.PlayerCharacter;
import com.codecrate.shard.divine.Deity;

public class DietyAlignmentPrerequisiteTest extends TestCase {

	public void testPrereqMetWhenAlignmentMatches() {

		MockControl mockDeity = MockControl.createControl(Deity.class);
		Deity deity = (Deity) mockDeity.getMock();
		deity.getAlignment();
		mockDeity.setReturnValue(Alignment.LAWFUL_GOOD);
		mockDeity.replay();
		
		MockControl mockCharacter = MockControl.createControl(PlayerCharacter.class);
		PlayerCharacter character = (PlayerCharacter) mockCharacter.getMock();
		character.getDeity();
		mockCharacter.setReturnValue(deity);
		character.getAlignment();
		mockCharacter.setReturnValue(Alignment.LAWFUL_GOOD);
		mockCharacter.replay();
		
		DeityAlignmentPrerequisite prereq = new DeityAlignmentPrerequisite();
		assertTrue(prereq.hasMetPrerequisite(character));
	}
	
	public void testPrereqNotMetWhenCharacterDoesNotHaveDeity() {
		MockControl mockCharacter = MockControl.createControl(PlayerCharacter.class);
		PlayerCharacter character = (PlayerCharacter) mockCharacter.getMock();
		character.getDeity();
		mockCharacter.setReturnValue(null);
		mockCharacter.replay();
		
		DeityAlignmentPrerequisite prereq = new DeityAlignmentPrerequisite();
		assertFalse(prereq.hasMetPrerequisite(character));
	}
	
	public void testPrereqNotMetAlignmentNotWithinOne() {
		MockControl mockDeity = MockControl.createControl(Deity.class);
		Deity deity = (Deity) mockDeity.getMock();
		deity.getAlignment();
		mockDeity.setReturnValue(Alignment.LAWFUL_GOOD);
		mockDeity.replay();
		
		MockControl mockCharacter = MockControl.createControl(PlayerCharacter.class);
		PlayerCharacter character = (PlayerCharacter) mockCharacter.getMock();
		character.getDeity();
		mockCharacter.setReturnValue(deity);
		character.getAlignment();
		mockCharacter.setReturnValue(Alignment.CHAOTIC_EVIL);
		mockCharacter.replay();
		
		DeityAlignmentPrerequisite prereq = new DeityAlignmentPrerequisite();
		assertFalse(prereq.hasMetPrerequisite(character));
	}
	
}
