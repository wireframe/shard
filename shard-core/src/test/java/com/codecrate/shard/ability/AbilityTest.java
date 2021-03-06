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
package com.codecrate.shard.ability;

import junit.framework.TestCase;

public class AbilityTest extends TestCase {

	public void testAbbreviationSubstringOfName() {
		assertEquals("STR", Ability.STRENGTH.getAbbreviation());
	}
	
	public void testAbleToLookupAbilityByAbbreviation() {
		assertEquals(Ability.STRENGTH, Ability.findByAbbreviation("STR"));
		assertEquals(Ability.DEXTERITY, Ability.findByAbbreviation("DEX"));
		assertEquals(Ability.WISDOM, Ability.findByAbbreviation("WIS"));
		assertEquals(Ability.INTELLIGENCE, Ability.findByAbbreviation("INT"));
		assertEquals(Ability.CONSTITUTION, Ability.findByAbbreviation("CON"));
		assertEquals(Ability.CHARISMA, Ability.findByAbbreviation("CHA"));
	}
	
	public void testErrorThrownLookingUpUnknownAbbreviation() {
		try {
			Ability.findByAbbreviation("ABC");
		} catch (IllegalArgumentException expected) {}
	}
}
