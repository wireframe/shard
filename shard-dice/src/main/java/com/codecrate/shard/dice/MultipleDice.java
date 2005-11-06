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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Rolls a dice a number of times.
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class MultipleDice extends DiceSupport implements Dice {
    private Dice dice;
    private int iterations;
    
    public MultipleDice(Dice dice, int iterations) {
        this.dice = dice;
        this.iterations = iterations;
    }
    
    public int getMaxValue() {
        return dice.getMaxValue() * iterations;
    }
    
    public int getMinValue() {
        return dice.getMinValue() * iterations;
    }
    
    public int roll() {
        int value = 0;
        Iterator values = rollIterations().iterator();
        while (values.hasNext()) {
            value += ((Integer) values.next()).intValue();
        }
        return value;
    }
    
    public List rollIterations() {
        List values = new ArrayList();
        for (int x = 0; x < iterations; x++) {
            values.add(new Integer(dice.roll()));
        }
        return values;
    }
    
    public Dice getDice() {
        return dice;
    }
    
    public int getIterations() {
        return iterations;
    }
    
    public String toString() {
        return iterations + dice.toString();
    }
}
