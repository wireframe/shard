package com.codecrate.shard.dice;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Dice that always rolls *at least* a certain value.
 * 
 * @author rsonnek
 */
public class AtLeastDice extends DiceSupport implements Dice {
	private static final Log LOG = LogFactory.getLog(AtLeastDice.class);

	private final Dice delegate;
	private final int minValue;

	public AtLeastDice(Dice delegate, int minValue) {
		DiceAssertion.assertValueInRange(delegate, minValue);
		this.delegate = delegate;
		this.minValue = minValue;
	}

	public int getMaxValue() {
		return atLeastMinValue(delegate.getMaxValue());
	}

	public int getMinValue() {
		return atLeastMinValue(delegate.getMinValue());
	}

	public int roll() {
		return atLeastMinValue(delegate.roll());
	}

    private int atLeastMinValue(int value) {
        if (value < minValue) {
        	LOG.info(value + " is less than minimum allowed value " + minValue);
            return minValue;
        }
        return value;
    }
}
