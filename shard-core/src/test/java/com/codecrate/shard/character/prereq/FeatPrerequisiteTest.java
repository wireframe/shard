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

import java.util.Arrays;

import junit.framework.TestCase;

import org.easymock.MockControl;

import com.codecrate.shard.character.PlayerCharacter;
import com.codecrate.shard.feat.DefaultFeat;
import com.codecrate.shard.feat.FeatContainer;

public class FeatPrerequisiteTest extends TestCase {

	public void testPrereqMetWhenHasFeat() {
	    FeatContainer feats = new FeatContainer(Arrays.asList(new DefaultFeat[]{DefaultFeat.ARMOR_PROFICIENCY_HEAVY}));
	    
		MockControl mockCharacter = MockControl.createControl(PlayerCharacter.class);
		PlayerCharacter character = (PlayerCharacter) mockCharacter.getMock();
		character.getFeats();
		mockCharacter.setReturnValue(feats);
		mockCharacter.replay();
		
		FeatPrerequisite prereq = new FeatPrerequisite(DefaultFeat.ARMOR_PROFICIENCY_HEAVY);
		assertTrue(prereq.hasMetPrerequisite(character));
	}
}
