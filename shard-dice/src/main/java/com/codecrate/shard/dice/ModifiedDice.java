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
 * Rolls a dice with a modifier.
 * modifier can be negative, but the minimum value returned from the roll 
 * is 1.
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class ModifiedDice extends DiceSupport implements Dice {
    private Dice dice;
    private int modifier;
    
    public ModifiedDice(Dice dice, int modifier) {
        this.dice = dice;
        this.modifier = modifier;
    }
    
    public int getMaxValue() {
        return atLeastOne(dice.getMaxValue() + modifier);
    }
    
    public int getMinValue() {
        return atLeastOne(dice.getMinValue() + modifier);
    }
    
    public int roll() {
        return atLeastOne(dice.roll() + modifier);
    }
    
    private int atLeastOne(int value) {
        if (value < 1) {
            return 1;
        }
        return value;
    }
    
    public String toString() {
        if (0 == modifier) {
            return dice.toString();
        } else if (0 <= modifier) {
            return dice.toString() + "+" + modifier;
        } else {
            return dice.toString() + "-" + modifier;
        }
    }
}