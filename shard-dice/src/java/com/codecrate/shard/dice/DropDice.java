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

import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.comparators.ReverseComparator;

/**
 * Rolls a dice a number of times and drops a certain number of them.
 * example: ability scores are usually generated with 4d6 and drop the lowest.
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class DropDice implements Dice {
    private MultipleDice dice;
    private int drop;
    private boolean dropHigh;
    
    public DropDice(MultipleDice dice, int drop, boolean dropHigh) {
        
        if (dice.getIterations() <= drop) {
            throw new IllegalArgumentException("Cannot drop more dice than rolled: " + drop);
        }
        
        this.dice = dice;
        this.drop = drop;
        this.dropHigh = dropHigh;
    }
    
    public int getMaxValue() {
        return dice.getDice().getMaxValue() * (dice.getIterations() - drop);
    }
    
    public int getMinValue() {
        return dice.getDice().getMinValue() * (dice.getIterations() - drop);
    }
    
    public int roll() {
        List values = dice.rollIterations();
        
        if (!dropHigh) {
            //sort from low to high
            Collections.sort(values);
        } else {
            //sort from high to low 
            Collections.sort(values, new ReverseComparator());
        }
        
        int value = 0;
        for (int x = 0; x < dice.getIterations() - drop; x++) {
            value += ((Integer)values.get(x)).intValue();
        }
        return value;
    }
    
    public String toString() {
        return "drop(" + dice.toString() + (dropHigh ?  ", high " : ", low ") + drop + ")";
    }
}
