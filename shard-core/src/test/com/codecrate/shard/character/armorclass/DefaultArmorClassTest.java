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
