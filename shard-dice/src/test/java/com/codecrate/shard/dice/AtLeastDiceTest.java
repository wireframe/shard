package com.codecrate.shard.dice;

import java.util.HashSet;
import java.util.Set;

import junit.framework.TestCase;

public class AtLeastDiceTest extends TestCase {

	public void testDiceMinValueMovedUpIfBelowThreshold() {
		AtLeastDice dice = new AtLeastDice(RandomDice.d6, 4);

		assertEquals(4, dice.getMinValue());
	}

	public void testRollMovedUpIfBelowThreshold() {
		AtLeastDice dice = new AtLeastDice(RandomDice.d6, 4);
		Set results = new HashSet();
		for (int x = 0; x < 100; x++) {
			results.add(new Integer(dice.roll()));
		}

		assertEquals(3, results.size());
	}
}
