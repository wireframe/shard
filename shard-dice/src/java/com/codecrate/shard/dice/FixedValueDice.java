/*
 * Copyright 2004 codecrate consulting
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.codecrate.shard.dice;

/**
 * Dice to always roll a fixed value.
 * useful for testing.
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class FixedValueDice implements Dice {

    private Dice dice;
    private int value;
    
    public FixedValueDice(Dice dice, int value) {
    	if (value > dice.getMaxValue() || value < dice.getMinValue()) {
    		throw new IllegalArgumentException("Value must be between " + dice.getMinValue() + " and " + dice.getMaxValue() + ": " + value);
    	}
        this.dice = dice;
        this.value = value;
    }
    
    public int getMaxValue() {
        return dice.getMaxValue();
    }

    public int getMinValue() {
        return dice.getMinValue();
    }
    
    public int roll() {
        return value;
    }
    
    public String toString() {
    	return Integer.toString(value);
    }
}
