package com.codecrate.shard.modifier;

import java.util.Collection;

/**
 * return the sum of all modifiers.
 */
public class StackableModifierCalculator implements ModifierCalculator {

	public int calculateModifier(Collection<Modifier> modifiers) {
		int value = 0;
		
		for (Modifier modifier : modifiers) {
			int modifierValue = modifier.getModifier();
			value += modifierValue;
		}
		return value;
	}
}
