package com.codecrate.shard.dice;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class AtLeastDice implements Dice {
	private static final Log LOG = LogFactory.getLog(AtLeastDice.class);

	private final Dice delegate;
	private final int minValue;

	public AtLeastDice(Dice delegate, int minValue) {
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
