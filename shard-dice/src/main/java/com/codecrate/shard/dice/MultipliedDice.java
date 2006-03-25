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
 * multiplies a dice roll by a multiplier.
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class MultipliedDice extends DiceSupport implements Dice {
    private Dice dice;
    private int multiplier;
    
    public MultipliedDice(Dice dice, int multiplier) {
        
        if (1 > multiplier) {
            throw new IllegalArgumentException("Multiplier must be greater than zero: " + multiplier);
        }
        
        this.dice = dice;
        this.multiplier = multiplier;
    }
    
    public int getMaxValue() {
        return dice.getMaxValue() * multiplier;
    }

    public int getMinValue() {
        return dice.getMinValue() * multiplier;
    }

    public int roll() {
        return dice.roll() * multiplier;
    }
    
    public String toString() {
        return dice.toString() + "*" + multiplier;
    }
}
