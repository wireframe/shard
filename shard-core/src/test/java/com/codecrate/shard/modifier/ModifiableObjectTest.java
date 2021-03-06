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
package com.codecrate.shard.modifier;

import junit.framework.TestCase;

import org.easymock.MockControl;

import com.codecrate.shard.modifier.DefaultModifierType;
import com.codecrate.shard.modifier.ModifiableObject;
import com.codecrate.shard.modifier.Modifier;
import com.codecrate.shard.modifier.ModifierType;

public class ModifiableObjectTest extends TestCase {

	public void testMultipleNonStackingModifiersUsesHighestModifier() {
	    ModifierType type = DefaultModifierType.ARMOR;

		MockControl firstModifierControl = MockControl.createControl(Modifier.class);
		Modifier firstModifier = (Modifier) firstModifierControl.getMock();
		firstModifier.getModifierType();
		firstModifierControl.setReturnValue(type);
		firstModifier.getModifierType();
		firstModifierControl.setReturnValue(type);
		firstModifier.getModifier();
		firstModifierControl.setReturnValue(5);
		firstModifierControl.replay();

		MockControl secondModifierControl = MockControl.createControl(Modifier.class);
		Modifier secondModifier = (Modifier) secondModifierControl.getMock();
		secondModifier.getModifierType();
		secondModifierControl.setReturnValue(type);
		secondModifier.getModifierType();
		secondModifierControl.setReturnValue(type);
		secondModifier.getModifier();
		secondModifierControl.setReturnValue(10);
		secondModifierControl.replay();

		ModifiableObject object = new ModifiableObject();
		object.addModifier(secondModifier);
		object.addModifier(firstModifier);

		assertEquals(10, object.getModifiedValue());
	}

	public void testMultipleStackingModifiersUsesBothModifiers() {
	    ModifierType type = DefaultModifierType.DODGE;

		MockControl firstModifierControl = MockControl.createControl(Modifier.class);
		Modifier firstModifier = (Modifier) firstModifierControl.getMock();
		firstModifier.getModifierType();
		firstModifierControl.setReturnValue(type);
		firstModifier.getModifierType();
		firstModifierControl.setReturnValue(type);
		firstModifier.getModifier();
		firstModifierControl.setReturnValue(5);
		firstModifierControl.replay();

		MockControl secondModifierControl = MockControl.createControl(Modifier.class);
		Modifier secondModifier = (Modifier) secondModifierControl.getMock();
		secondModifier.getModifierType();
		secondModifierControl.setReturnValue(type);
		secondModifier.getModifierType();
		secondModifierControl.setReturnValue(type);
		secondModifier.getModifier();
		secondModifierControl.setReturnValue(10);
		secondModifierControl.replay();

		ModifiableObject object = new ModifiableObject();
		object.addModifier(secondModifier);
		object.addModifier(firstModifier);

		assertEquals(15, object.getModifiedValue());
	}

	public void testRemovingHighestModifierStillUsesLowerModifier() {
	    ModifierType type = DefaultModifierType.ARMOR;

		MockControl firstModifierControl = MockControl.createControl(Modifier.class);
		Modifier firstModifier = (Modifier) firstModifierControl.getMock();
		firstModifier.getModifierType();
		firstModifierControl.setReturnValue(type);
		firstModifier.getModifierType();
		firstModifierControl.setReturnValue(type);
		firstModifier.getModifier();
		firstModifierControl.setReturnValue(5);
		firstModifierControl.replay();

		MockControl secondModifierControl = MockControl.createControl(Modifier.class);
		Modifier secondModifier = (Modifier) secondModifierControl.getMock();
		secondModifier.getModifierType();
		secondModifierControl.setReturnValue(type);
		secondModifier.getModifierType();
		secondModifierControl.setReturnValue(type);
		secondModifier.getModifierType();
		secondModifierControl.setReturnValue(type);
		secondModifier.getModifier();
		secondModifierControl.setReturnValue(10);
		secondModifierControl.replay();

		ModifiableObject object = new ModifiableObject();
		object.addModifier(secondModifier);
		object.addModifier(firstModifier);
		object.removeModifier(secondModifier);

		assertEquals(5, object.getModifiedValue());
	}

	public void testChangingBaseValueAfterModifiersAddedKeepsCorrectModifiedValue() {
	    ModifierType type = DefaultModifierType.ARMOR;

		MockControl firstModifierControl = MockControl.createControl(Modifier.class);
		Modifier firstModifier = (Modifier) firstModifierControl.getMock();
		firstModifier.getModifierType();
		firstModifierControl.setReturnValue(type);
		firstModifier.getModifierType();
		firstModifierControl.setReturnValue(type);
		firstModifier.getModifier();
		firstModifierControl.setReturnValue(5);
		firstModifierControl.replay();

		ModifiableObject object = new ModifiableObject();
		object.addModifier(firstModifier);

		object.setValue(5);
		assertEquals(5, object.getValue());
		assertEquals(10, object.getModifiedValue());
	}
}
