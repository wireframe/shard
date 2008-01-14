package com.codecrate.shard.modifier;

import java.util.Collections;

import junit.framework.TestCase;

public class HighestModifierCalculatorTest extends TestCase {

	public void testValueIsZeroIfNoModifiers() {
		HighestModifierCalculator calculator = new HighestModifierCalculator();
		
		assertEquals(0, calculator.calculateModifier(Collections.EMPTY_LIST));
	}
}
