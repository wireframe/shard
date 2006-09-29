package com.codecrate.shard.dice;

import junit.framework.TestCase;

public class AtLeastDiceTest extends TestCase {

	public void testDiceMinValueMovedUpIfBelowThreshold() {
		AtLeastDice dice = new AtLeastDice(RandomDice.d6, 4);

		assertEquals(4, dice.getMinValue());
	}

	public void testDiceMaxValueMovedUpIfBelowThreshold() {
		AtLeastDice dice = new AtLeastDice(RandomDice.d6, 7);

		assertEquals(7, dice.getMaxValue());
	}

	public void testRollMovedUpIfBelowThreshold() {
		AtLeastDice dice = new AtLeastDice(RandomDice.d6, 7);

		assertEquals(7, dice.roll());
	}
}
