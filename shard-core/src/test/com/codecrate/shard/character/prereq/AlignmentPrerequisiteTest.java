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

public class AlignmentPrerequisiteTest extends TestCase {

	public void testPrereqMetWhenAlignmentMatches() {
		MockControl mockAlignment = MockControl.createControl(Alignment.class);
		Alignment alignment = (Alignment) mockAlignment.getMock();
		alignment.isSame(alignment);
		mockAlignment.setReturnValue(true);
		mockAlignment.replay();
		
		MockControl mockCharacter = MockControl.createControl(PlayerCharacter.class);
		PlayerCharacter character = (PlayerCharacter) mockCharacter.getMock();
		character.getAlignment();
		mockCharacter.setReturnValue(alignment);
		mockCharacter.replay();
		
		AlignmentPrerequisite prereq = new AlignmentPrerequisite(alignment);
		assertTrue(prereq.isSatisfiedBy(character));
	}
	

	public void testPrereqNotMetWhenAlignmentDifferent() {
		MockControl mockAlignment = MockControl.createControl(Alignment.class);
		Alignment alignment = (Alignment) mockAlignment.getMock();
		alignment.isSame(alignment);
		mockAlignment.setReturnValue(false);
		mockAlignment.replay();
		
		MockControl mockCharacter = MockControl.createControl(PlayerCharacter.class);
		PlayerCharacter character = (PlayerCharacter) mockCharacter.getMock();
		character.getAlignment();
		mockCharacter.setReturnValue(alignment);
		mockCharacter.replay();
		
		AlignmentPrerequisite prereq = new AlignmentPrerequisite(alignment);
		assertFalse(prereq.isSatisfiedBy(character));
	}
}
