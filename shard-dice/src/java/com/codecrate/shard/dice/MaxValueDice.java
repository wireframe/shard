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
 * Dice to always roll max value.
 * useful for testing.
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class MaxValueDice implements Dice {

    private Dice dice;
    
    public MaxValueDice(Dice dice) {
        this.dice = dice;
    }
    
    public int getMaxValue() {
        return dice.getMaxValue();
    }

    public int getMinValue() {
        return dice.getMinValue();
    }
    
    public int roll() {
        return dice.getMaxValue();
    }
    
    public String toString() {
    	return "max(" + dice.toString() + ")";
    }
}
