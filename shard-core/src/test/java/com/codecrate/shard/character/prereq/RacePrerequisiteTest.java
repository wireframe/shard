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
import com.codecrate.shard.race.DefaultRace;

public class RacePrerequisiteTest extends TestCase {

	public void testPrereqMetWhenRaceMatches() {
        DefaultRace human = new DefaultRace("human", null, null, 0, null, null, null, 0, null);

		MockControl mockCharacter = MockControl.createControl(PlayerCharacter.class);
		PlayerCharacter character = (PlayerCharacter) mockCharacter.getMock();
		character.getRace();
		mockCharacter.setReturnValue(human);
		mockCharacter.replay();

		RacePrerequisite prereq = new RacePrerequisite(human);
		assertTrue(prereq.hasMetPrerequisite(character));
	}


	public void testPrereqNotMetWhenRaceDifferent() {
        DefaultRace human = new DefaultRace("human", null, null, 0, null, null, null, 0, null);

        DefaultRace elf = new DefaultRace("elf", null, null, 0, null, null, null, 0, null);
        MockControl mockCharacter = MockControl.createControl(PlayerCharacter.class);
		PlayerCharacter character = (PlayerCharacter) mockCharacter.getMock();
		character.getRace();
		mockCharacter.setReturnValue(human);
		mockCharacter.replay();

		RacePrerequisite prereq = new RacePrerequisite(elf);
		assertFalse(prereq.hasMetPrerequisite(character));
	}
}
