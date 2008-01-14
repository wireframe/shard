package com.codecrate.shard.modifier;

import java.util.Collection;

public interface ModifierCalculator {

	/**
	 * calculate the modifier that should be applied.
	 */
	int calculateModifier(Collection<Modifier> modifiers);
}
