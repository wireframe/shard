package com.codecrate.shard.modifier;

import java.util.Collection;

/**
 * restrict the maximum modifier allowed.
 */
public class CappedStackableModifierCalculator implements ModifierCalculator {

	private final int maxValue;
	private final ModifierCalculator calculator;
	
	public CappedStackableModifierCalculator(int maxValue) {
		this.maxValue = maxValue;
		this.calculator = new StackableModifierCalculator();
	}
	
	public int calculateModifier(Collection<Modifier> modifiers) {
		int value = calculator.calculateModifier(modifiers);
		if (value > maxValue) {
			return maxValue;
		}
		return value;
	}

}
