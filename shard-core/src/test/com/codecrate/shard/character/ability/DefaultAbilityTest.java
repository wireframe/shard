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
