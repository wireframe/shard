package com.codecrate.shard.dice;

public class DiceAssertion {

	public static void assertValueInRange(Dice dice, int value) {
    	if (value < dice.getMinValue() || value > dice.getMaxValue()) {
    		throw new IllegalArgumentException("Value must be between " + dice.getMinValue() + " and " + dice.getMaxValue() + ": " + value);
    	}
	}

}
