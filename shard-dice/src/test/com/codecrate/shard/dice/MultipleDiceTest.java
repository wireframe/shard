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

import junit.framework.TestCase;

/**
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class MultipleDiceTest extends TestCase {
    public void testMaxValue() {
    	MultipleDice dice = new MultipleDice(DefaultDice.d6, 1);
    	assertEquals(6, dice.getMaxValue());
    }
    
    public void testMinValue() {
    	MultipleDice dice = new MultipleDice(DefaultDice.d6, 1);
    	assertEquals(1, dice.getMinValue());
    }
    
    public void testNumberOfRollsUsed() {
    	MultipleDice dice = new MultipleDice(new MaxValueDice(DefaultDice.d6), 2);
    	assertEquals(12, dice.roll());
    }
}
