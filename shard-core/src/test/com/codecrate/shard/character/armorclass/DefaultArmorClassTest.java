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
package com.codecrate.shard.character.armorclass;

import junit.framework.TestCase;

import org.easymock.MockControl;

public class DefaultArmorClassTest extends TestCase {

	public void testMultipleModifiersUsesLastModifierType() {
		MockControl firstArmorClassModifierControl = MockControl.createControl(ArmorClassModifier.class);
		ArmorClassModifier firstModifier = (ArmorClassModifier) firstArmorClassModifierControl.getMock();
		firstModifier.getModifierType();
		firstArmorClassModifierControl.setReturnValue("typeA");
		firstModifier.getModifier();
		firstArmorClassModifierControl.setReturnValue(5);
		firstArmorClassModifierControl.replay();

		MockControl secondArmorClassModifierControl = MockControl.createControl(ArmorClassModifier.class);
		ArmorClassModifier secondModifier = (ArmorClassModifier) secondArmorClassModifierControl.getMock();
		secondModifier.getModifierType();
		secondArmorClassModifierControl.setReturnValue("typeA");
		secondModifier.getModifier();
		secondArmorClassModifierControl.setReturnValue(10);
		secondArmorClassModifierControl.replay();

		DefaultArmorClass armorClass = new DefaultArmorClass();
		armorClass.addArmorClassModifier(firstModifier);
		armorClass.addArmorClassModifier(secondModifier);
		
		assertEquals(20, armorClass.getValue());
	}
}
