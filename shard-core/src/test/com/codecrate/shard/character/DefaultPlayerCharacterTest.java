package com.codecrate.shard.character;

import junit.framework.TestCase;

import org.easymock.MockControl;

import com.codecrate.shard.race.Race;

public class DefaultPlayerCharacterTest extends TestCase {

	public void testEffectiveCharacterLevelCalculation() {
		MockControl mockRace = MockControl.createControl(Race.class);
		Race race = (Race) mockRace.getMock();
		race.getLevelAdjustment();
		mockRace.setReturnValue(1);
		mockRace.replay();

		MockControl mockProgression = MockControl.createControl(CharacterProgression.class);
		CharacterProgression levels = (CharacterProgression) mockProgression.getMock();
		levels.getLevel();
		mockProgression.setReturnValue(1);
		mockProgression.replay();
	    
		DefaultPlayerCharacter character = new DefaultPlayerCharacter(race, null, null, null, null, null, null, null, levels);
		assertEquals(2, character.getEffectiveCharacterLevel());
	}
}
