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
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class CustomDice implements Dice {
    private static final String MULTIPLIER = "x";
    private static final String MODIFIER_NEGATIVE = "-";
    private static final String MODIFIER_POSITIVE = "+";
    
    private static final int DEFAULT_MULTIPLIER = 1;
    private static final int DEFAULT_MODIFIER = 0;
    
    private Dice dice;
    
    public CustomDice(String diceString) {
        diceString = diceString.replaceAll(" ", "");
        dice = new DefaultDice(parseDiceSides(diceString));
        dice = new MultipleDice(dice, parseNumberOfDice(diceString));

        int modifier = parseModifier(diceString);
        int multiplier = parseMultiplier(diceString);
        if (DEFAULT_MODIFIER !=  modifier && DEFAULT_MULTIPLIER != multiplier) {
            throw new IllegalArgumentException("Cannot use both modifier and multiplier on dice: " + diceString);
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
    
    private static int parseDiceSides(String diceString) {
        int indexOfDice = diceString.indexOf("d");
        
        int indexOfNextModifier = diceString.indexOf(MODIFIER_POSITIVE);
        if (-1 == indexOfNextModifier) {
            indexOfNextModifier = diceString.indexOf(MODIFIER_NEGATIVE);
        }
        if (-1 == indexOfNextModifier) {
            indexOfNextModifier = diceString.indexOf(MULTIPLIER);
        }
        if (-1 == indexOfNextModifier) {
            indexOfNextModifier = diceString.length();
        }
        
        return Integer.parseInt(diceString.substring(indexOfDice + 1, indexOfNextModifier));
    }
    
    private static int parseNumberOfDice(String diceString) {
        int multiple = 1;
        
        //get multiple dice
        int indexOfDice = diceString.indexOf("d");
        if (0 <  indexOfDice) {
            multiple = Integer.parseInt(diceString.substring(0, indexOfDice));
        }
        return multiple;
    }
    
    private static int parseModifier(String diceString) {
        int modifier = DEFAULT_MODIFIER;
        int indexOfPositive = diceString.indexOf(MODIFIER_POSITIVE);
        int indexOfNegative = diceString.indexOf(MODIFIER_NEGATIVE);
        if (0 <  indexOfPositive) {
            modifier = Integer.parseInt(diceString.substring(indexOfPositive + 1, diceString.length()));
        } else if (0 <  indexOfNegative) {
            modifier = Integer.parseInt(diceString.substring(indexOfNegative + 1, diceString.length())) * -1;
        }
        return modifier;
    }
    
    private static int parseMultiplier(String diceString) {
        int multiplier = DEFAULT_MULTIPLIER;
        int indexOfMultiplier = diceString.indexOf(MULTIPLIER);
        if (0 <  indexOfMultiplier) {
            multiplier = Integer.parseInt(diceString.substring(indexOfMultiplier + 1, diceString.length()));
        }
        return multiplier;
    }
}
