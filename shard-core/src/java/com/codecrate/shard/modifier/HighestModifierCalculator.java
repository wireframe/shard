package com.codecrate.shard.modifier;

import java.util.Collection;
import java.util.Iterator;

public class HighestModifierCalculator implements ModifierCalculator {

	public int calculateModifier(Collection modifiers) {
        if (0 == modifiers.size()) {
            return 0;
        }
        
		int value = Integer.MIN_VALUE;
		Iterator mods = modifiers.iterator();
		while (mods.hasNext()) {
			Modifier modifier = (Modifier) mods.next();
			int modifierValue = modifier.getModifier();
			if (value < modifierValue) {
				value = modifierValue;
			}
		}
		return value;
	}

}
