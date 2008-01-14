package com.codecrate.shard.modifier;

import java.util.Collection;

/**
 * return the highest value of any of the modifiers.
 */
public class HighestModifierCalculator implements ModifierCalculator {

	public int calculateModifier(Collection<Modifier> modifiers) {
        if (0 == modifiers.size()) {
            return 0;
        }
        
		int value = Integer.MIN_VALUE;
		for (Modifier modifier : modifiers) {
			int modifierValue = modifier.getModifier();
			if (value < modifierValue) {
				value = modifierValue;
			}
		}
		return value;
	}

}
