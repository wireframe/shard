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

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

public class DefaultAbilityContainerTest extends TestCase {

	public void testGetAbilityReturnsSameObject() {
		Ability ability = new DefaultAbility(DefaultAbilityContainer.STRENGTH, 10);
		Map abilities = new HashMap();
		abilities.put(DefaultAbilityContainer.STRENGTH, ability);
		
		DefaultAbilityContainer container = new DefaultAbilityContainer(abilities);
		assertSame(ability, container.getStrength());
		assertSame(ability, container.getAbility(DefaultAbilityContainer.STRENGTH));
	}
	
	public void testHasAbilityFailsForNoAbility() {
		Map abilities = new HashMap();
		DefaultAbilityContainer container = new DefaultAbilityContainer(abilities);
		assertFalse(container.hasAbility(DefaultAbilityContainer.STRENGTH));
	}
}
