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
package com.codecrate.shard.character.ability;

import junit.framework.TestCase;

import org.easymock.MockControl;

public class DefaultAbilityTest extends TestCase {

	public void testModifierNotAddedIfDifferentName() {
		MockControl abilityModifierControl = MockControl.createControl(AbilityModifier.class);
		AbilityModifier modifier = (AbilityModifier) abilityModifierControl.getMock();
		modifier.getAbilityName();
		abilityModifierControl.setReturnValue("noMatch");
		abilityModifierControl.replay();
		
		DefaultAbility ability = new DefaultAbility("match", 10);
		ability.addAbilityModifier(modifier);
		
		assertEquals(10, ability.getScore());
	}
}
