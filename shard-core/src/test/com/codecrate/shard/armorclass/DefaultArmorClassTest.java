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
package com.codecrate.shard.armorclass;

import junit.framework.TestCase;

import org.easymock.MockControl;

import com.codecrate.shard.Modifier;

public class DefaultArmorClassTest extends TestCase {

	public void testMultipleNonStackingModifiersUsesHighestModifier() {
		MockControl firstArmorClassModifierControl = MockControl.createControl(Modifier.class);
		Modifier firstModifier = (Modifier) firstArmorClassModifierControl.getMock();
		firstModifier.getModifierType();
		firstArmorClassModifierControl.setReturnValue(DefaultArmorClass.DEFLECTION);
		firstModifier.getModifier();
		firstArmorClassModifierControl.setReturnValue(5);
		firstArmorClassModifierControl.replay();

		MockControl secondArmorClassModifierControl = MockControl.createControl(Modifier.class);
		Modifier secondModifier = (Modifier) secondArmorClassModifierControl.getMock();
		secondModifier.getModifierType();
		secondArmorClassModifierControl.setReturnValue(DefaultArmorClass.DEFLECTION);
		secondModifier.getModifier();
		secondArmorClassModifierControl.setReturnValue(10);
		secondArmorClassModifierControl.replay();

		DefaultArmorClass armorClass = new DefaultArmorClass();
		armorClass.addModifier(secondModifier);
		armorClass.addModifier(firstModifier);
		
		assertEquals(20, armorClass.getModifiedValue());
	}
	
	public void testMultipleStackingModifiersUsesBothModifiers() {
		MockControl firstArmorClassModifierControl = MockControl.createControl(Modifier.class);
		Modifier firstModifier = (Modifier) firstArmorClassModifierControl.getMock();
		firstModifier.getModifierType();
		firstArmorClassModifierControl.setReturnValue(DefaultArmorClass.DODGE);
		firstModifier.getModifier();
		firstArmorClassModifierControl.setReturnValue(5);
		firstArmorClassModifierControl.replay();

		MockControl secondArmorClassModifierControl = MockControl.createControl(Modifier.class);
		Modifier secondModifier = (Modifier) secondArmorClassModifierControl.getMock();
		secondModifier.getModifierType();
		secondArmorClassModifierControl.setReturnValue(DefaultArmorClass.DODGE);
		secondModifier.getModifier();
		secondArmorClassModifierControl.setReturnValue(10);
		secondArmorClassModifierControl.replay();

		DefaultArmorClass armorClass = new DefaultArmorClass();
		armorClass.addModifier(secondModifier);
		armorClass.addModifier(firstModifier);
		
		assertEquals(25, armorClass.getModifiedValue());
	}
	
	public void testRemovingHighestModifierStillUsesLowerModifier() {
		MockControl lowerArmorClassModifierControl = MockControl.createControl(Modifier.class);
		Modifier lowerModifier = (Modifier) lowerArmorClassModifierControl.getMock();
		lowerModifier.getModifierType();
		lowerArmorClassModifierControl.setReturnValue(DefaultArmorClass.DEFLECTION);
		lowerModifier.getModifier();
		lowerArmorClassModifierControl.setReturnValue(5);
		lowerArmorClassModifierControl.replay();

		MockControl higherArmorClassModifierControl = MockControl.createControl(Modifier.class);
		Modifier higherModifier = (Modifier) higherArmorClassModifierControl.getMock();
		higherModifier.getModifierType();
		higherArmorClassModifierControl.setReturnValue(DefaultArmorClass.DEFLECTION);
		higherModifier.getModifierType();
		higherArmorClassModifierControl.setReturnValue(DefaultArmorClass.DEFLECTION);
		higherModifier.getModifier();
		higherArmorClassModifierControl.setReturnValue(10);
		higherArmorClassModifierControl.replay();

		DefaultArmorClass armorClass = new DefaultArmorClass();
		armorClass.addModifier(higherModifier);
		armorClass.addModifier(lowerModifier);
		armorClass.removeModifier(higherModifier);
		
		assertEquals(15, armorClass.getModifiedValue());
	}
}
