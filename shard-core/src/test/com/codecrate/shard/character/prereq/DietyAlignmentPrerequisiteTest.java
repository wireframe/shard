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
		MockControl mockAlignment = MockControl.createControl(Alignment.class);
		Alignment alignment = (Alignment) mockAlignment.getMock();
		alignment.isGood();
		mockAlignment.setReturnValue(true);
		alignment.isGood();
		mockAlignment.setReturnValue(true);
		alignment.isLawful();
		mockAlignment.setReturnValue(true);
		alignment.isLawful();
		mockAlignment.setReturnValue(true);
		mockAlignment.replay();
		
		MockControl mockDeity = MockControl.createControl(Deity.class);
		Deity deity = (Deity) mockDeity.getMock();
		deity.getAlignment();
		mockDeity.setReturnValue(alignment);
		mockDeity.replay();
		
		MockControl mockCharacter = MockControl.createControl(PlayerCharacter.class);
		PlayerCharacter character = (PlayerCharacter) mockCharacter.getMock();
		character.getDeity();
		mockCharacter.setReturnValue(deity);
		character.getAlignment();
		mockCharacter.setReturnValue(alignment);
		mockCharacter.replay();
		
		DeityAlignmentPrerequisite prereq = new DeityAlignmentPrerequisite();
		assertTrue(prereq.hasMetPrerequisite(character));
	}
	

	public void testPrereqMetWhenMoralAlignmentWithinOne() {
		MockControl mockCharacterAlignment = MockControl.createControl(Alignment.class);
		Alignment characterAlignment = (Alignment) mockCharacterAlignment.getMock();
		characterAlignment.isGood();
		mockCharacterAlignment.setReturnValue(false);
		characterAlignment.isMoralNeutral();
		mockCharacterAlignment.setReturnValue(true);
		characterAlignment.isLawful();
		mockCharacterAlignment.setReturnValue(true);
		mockCharacterAlignment.replay();

		MockControl mockDeityAlignment = MockControl.createControl(Alignment.class);
		Alignment deityAlignment = (Alignment) mockDeityAlignment.getMock();
		deityAlignment.isGood();
		mockDeityAlignment.setReturnValue(true);
		deityAlignment.isLawful();
		mockDeityAlignment.setReturnValue(true);
		mockDeityAlignment.replay();

		MockControl mockDeity = MockControl.createControl(Deity.class);
		Deity deity = (Deity) mockDeity.getMock();
		deity.getAlignment();
		mockDeity.setReturnValue(deityAlignment);
		mockDeity.replay();
		
		MockControl mockCharacter = MockControl.createControl(PlayerCharacter.class);
		PlayerCharacter character = (PlayerCharacter) mockCharacter.getMock();
		character.getDeity();
		mockCharacter.setReturnValue(deity);
		character.getAlignment();
		mockCharacter.setReturnValue(characterAlignment);
		mockCharacter.replay();
		
		DeityAlignmentPrerequisite prereq = new DeityAlignmentPrerequisite();
		assertTrue(prereq.hasMetPrerequisite(character));
	}
	
	public void testPrereqMetWhenEthicalAlignmentComponentWithinOne() {
		MockControl mockCharacterAlignment = MockControl.createControl(Alignment.class);
		Alignment characterAlignment = (Alignment) mockCharacterAlignment.getMock();
		characterAlignment.isGood();
		mockCharacterAlignment.setReturnValue(true);
		characterAlignment.isLawful();
		mockCharacterAlignment.setReturnValue(false);
		characterAlignment.isEthicalNeutral();
		mockCharacterAlignment.setReturnValue(true);
		mockCharacterAlignment.replay();

		MockControl mockDeityAlignment = MockControl.createControl(Alignment.class);
		Alignment deityAlignment = (Alignment) mockDeityAlignment.getMock();
		deityAlignment.isGood();
		mockDeityAlignment.setReturnValue(true);
		deityAlignment.isLawful();
		mockDeityAlignment.setReturnValue(true);
		mockDeityAlignment.replay();

		MockControl mockDeity = MockControl.createControl(Deity.class);
		Deity deity = (Deity) mockDeity.getMock();
		deity.getAlignment();
		mockDeity.setReturnValue(deityAlignment);
		mockDeity.replay();
		
		MockControl mockCharacter = MockControl.createControl(PlayerCharacter.class);
		PlayerCharacter character = (PlayerCharacter) mockCharacter.getMock();
		character.getDeity();
		mockCharacter.setReturnValue(deity);
		character.getAlignment();
		mockCharacter.setReturnValue(characterAlignment);
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
		MockControl mockCharacterAlignment = MockControl.createControl(Alignment.class);
		Alignment characterAlignment = (Alignment) mockCharacterAlignment.getMock();
		characterAlignment.isGood();
		mockCharacterAlignment.setReturnValue(false);
		characterAlignment.isMoralNeutral();
		mockCharacterAlignment.setReturnValue(true);
		characterAlignment.isLawful();
		mockCharacterAlignment.setReturnValue(false);
		characterAlignment.isEthicalNeutral();
		mockCharacterAlignment.setReturnValue(true);
		mockCharacterAlignment.replay();

		MockControl mockDeityAlignment = MockControl.createControl(Alignment.class);
		Alignment deityAlignment = (Alignment) mockDeityAlignment.getMock();
		deityAlignment.isGood();
		mockDeityAlignment.setReturnValue(true);
		deityAlignment.isLawful();
		mockDeityAlignment.setReturnValue(true);
		mockDeityAlignment.replay();

		MockControl mockDeity = MockControl.createControl(Deity.class);
		Deity deity = (Deity) mockDeity.getMock();
		deity.getAlignment();
		mockDeity.setReturnValue(deityAlignment);
		mockDeity.replay();
		
		MockControl mockCharacter = MockControl.createControl(PlayerCharacter.class);
		PlayerCharacter character = (PlayerCharacter) mockCharacter.getMock();
		character.getDeity();
		mockCharacter.setReturnValue(deity);
		character.getAlignment();
		mockCharacter.setReturnValue(characterAlignment);
		mockCharacter.replay();
		
		DeityAlignmentPrerequisite prereq = new DeityAlignmentPrerequisite();
		assertFalse(prereq.hasMetPrerequisite(character));
	}
	
}
