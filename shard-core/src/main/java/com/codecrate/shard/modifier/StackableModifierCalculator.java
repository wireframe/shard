package com.codecrate.shard.modifier;

import java.util.Collection;
import java.util.Iterator;

public class StackableModifierCalculator implements ModifierCalculator {

	public int calculateModifier(Collection modifiers) {
		int value = 0;
		Iterator mods = modifiers.iterator();
		while (mods.hasNext()) {
			Modifier modifier = (Modifier) mods.next();
			int modifierValue = modifier.getModifier();
			value += modifierValue;
		}
		return value;
	}
}
