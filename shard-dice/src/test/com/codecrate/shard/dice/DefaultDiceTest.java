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

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

/**
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class DefaultDiceTest extends TestCase {
    private static final int ITERATIONS = 100;

    public void testDiceDoesNotRollHigherThanMaxValue() {
        int maxValue = 6;
        DefaultDice dice = new DefaultDice(maxValue);

        for (int x = 0; x < ITERATIONS; x++) {
            int value = dice.roll();
            if (maxValue < value) {
                fail("Can not roll higher than dice max value: " + value + " > " + maxValue);
            }
        }
    }

    public void testDiceRollsFullRangeOfValues() {
        Map values = new HashMap();
        int maxValue = 6;
        DefaultDice dice = new DefaultDice(maxValue);

        for (int x = 0; x < ITERATIONS; x++) {
            int value = dice.roll();
            values.put(new Integer(value), Boolean.TRUE);
        }
        
        assertEquals(maxValue, values.keySet().size());
    }
    
    public void testCannotCreateDiceLessThanMinValue() {
        try {
            new DefaultDice(0);
            fail(); 
        } catch (IllegalArgumentException expected) {}
    }
}
