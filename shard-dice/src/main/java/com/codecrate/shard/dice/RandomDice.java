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
 * creates a dice with range from 1 to maxValue.
 * randomly generates values between 1 and maxvalue.
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class RandomDice extends DiceSupport implements Dice {
    public static final int MIN_VALUE = 1;
    
    public static final Dice d100 = new RandomDice(100);
    public static final Dice d20 = new RandomDice(20);
    public static final Dice d12 = new RandomDice(12);
    public static final Dice d10 = new RandomDice(10);
    public static final Dice d8 = new RandomDice(8);
    public static final Dice d6 = new RandomDice(6);
    public static final Dice d4 = new RandomDice(4);
    
    private int maxValue;
    
    public RandomDice(int maxValue) {
        
        if (MIN_VALUE > maxValue) {
            throw new IllegalArgumentException("Cannot create dice with max value < " + MIN_VALUE + ": " + maxValue);
        }
        this.maxValue = maxValue;
    }
    
    public int roll() {
        return (int) (Math.random() * maxValue) + 1;
    }
    
    public int getMaxValue() {
        return maxValue;
    }
    
    public int getMinValue() {
        return MIN_VALUE;
    }
    
    public String toString() {
        return "d" + maxValue;
    }
}
