package com.codecrate.shard.modifier;

import java.util.Collection;

public class CappedStackableModifierCalculator implements ModifierCalculator {

	private final int maxValue;
	private final ModifierCalculator calculator;
	
	public CappedStackableModifierCalculator(int maxValue) {
		this.maxValue = maxValue;
		this.calculator = new StackableModifierCalculator();
	}
	
	public int calculateModifier(Collection modifiers) {
		int value = calculator.calculateModifier(modifiers);
		if (value > maxValue) {
			return maxValue;
		}
		return value;
	}

}
