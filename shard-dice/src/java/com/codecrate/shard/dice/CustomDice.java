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

import java.util.StringTokenizer;

/**
 * parses a dice expression into a set of dice.
 * ex: 1d4+2
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class CustomDice implements Dice {
	private static final String DICE_CHARACTER = "d";
    private static final String MULTIPLIER = "x";
    private static final String MODIFIER_NEGATIVE = "-";
    private static final String MODIFIER_POSITIVE = "+";
    
    private static final int DEFAULT_MULTIPLIER = 1;
    private static final int DEFAULT_MODIFIER = 0;
    
    private Dice dice;
    
    public CustomDice(String diceExpression) {
        diceExpression = diceExpression.replaceAll(" ", "");
        dice = new DefaultDice(parseDiceSides(diceExpression));
        dice = new MultipleDice(dice, parseNumberOfDice(diceExpression));

        int modifier = parseModifier(diceExpression);
        int multiplier = parseMultiplier(diceExpression);
        if (DEFAULT_MODIFIER !=  modifier && DEFAULT_MULTIPLIER != multiplier) {
            throw new IllegalArgumentException("Cannot use both modifier and multiplier on dice: " + diceExpression);
        } else if (DEFAULT_MODIFIER != modifier) {
            dice = new ModifiedDice(dice, modifier);
        } else if (DEFAULT_MULTIPLIER != multiplier) {
            dice = new MultipliedDice(dice, multiplier);
        }
    }
    
    public int getMaxValue() {
        return dice.getMaxValue();
    }
    
    public int getMinValue() {
        return dice.getMinValue();
    }
    
    public int roll() {
        return dice.roll();
    }
    
    public String toString() {
        return dice.toString();
    }
    
    private static int parseDiceSides(String diceExpression) {
        int indexOfDice = diceExpression.indexOf(DICE_CHARACTER);
        
        int indexOfNextModifier = diceExpression.indexOf(MODIFIER_POSITIVE);
        if (-1 == indexOfNextModifier) {
            indexOfNextModifier = diceExpression.indexOf(MODIFIER_NEGATIVE);
        }
        if (-1 == indexOfNextModifier) {
            indexOfNextModifier = diceExpression.indexOf(MULTIPLIER);
        }
        if (-1 == indexOfNextModifier) {
            indexOfNextModifier = diceExpression.length();
        }
        
        return Integer.parseInt(diceExpression.substring(indexOfDice + 1, indexOfNextModifier));
    }
    
    private static int parseNumberOfDice(String diceExpression) {
        int multiple = 1;
        
        //get multiple dice
        int indexOfDice = diceExpression.indexOf(DICE_CHARACTER);
        if (0 <  indexOfDice) {
            multiple = Integer.parseInt(diceExpression.substring(0, indexOfDice));
        }
        return multiple;
    }
    
    private static int parseModifier(String diceExpression) {
        int modifier = DEFAULT_MODIFIER;
        int indexOfPositive = diceExpression.indexOf(MODIFIER_POSITIVE);
        int indexOfNegative = diceExpression.indexOf(MODIFIER_NEGATIVE);
        if (0 <  indexOfPositive) {
            modifier = Integer.parseInt(diceExpression.substring(indexOfPositive + 1, diceExpression.length()));
        } else if (0 <  indexOfNegative) {
            modifier = Integer.parseInt(diceExpression.substring(indexOfNegative + 1, diceExpression.length())) * -1;
        }
        return modifier;
    }
    
    private static int parseMultiplier(String diceExpression) {
        int multiplier = DEFAULT_MULTIPLIER;
        int indexOfMultiplier = diceExpression.indexOf(MULTIPLIER);
        if (0 <  indexOfMultiplier) {
            multiplier = Integer.parseInt(diceExpression.substring(indexOfMultiplier + 1, diceExpression.length()));
        }
        return multiplier;
    }
    
    private static String getNextElement(String diceExpression) {
        String DELIMITERS = DICE_CHARACTER + MULTIPLIER + MODIFIER_NEGATIVE + MODIFIER_POSITIVE;
        
    	StringTokenizer tokenizer = new StringTokenizer(diceExpression, DELIMITERS, true);
    	while (tokenizer.hasMoreTokens()) {
    		String token = tokenizer.nextToken();
    		if (-1 != DELIMITERS.indexOf(token)) {
    			String value = tokenizer.nextToken();
    			
    		}
    	}
    	return null;
    }
}
