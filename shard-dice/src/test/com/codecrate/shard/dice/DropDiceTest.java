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
public class DropDiceTest extends TestCase {
    
    public void testLowValuesDropped() {
        DropDice dice = new DropDice(new MultipleDice(new MaxValueDice(DefaultDice.d6), 4), 1, false);
        
        assertEquals(18, dice.getMaxValue());
        assertEquals(18, dice.roll());
    }
    
    public void testHighValuesDropped() {
        DropDice dice = new DropDice(new MultipleDice(new MaxValueDice(DefaultDice.d6), 4), 1, true);
        
        assertEquals(18, dice.getMaxValue());
        assertEquals(18, dice.roll());
    }
    
    public void testLowValueComputedCorrectly() {
        DropDice dice = new DropDice(new MultipleDice(new MaxValueDice(DefaultDice.d6), 4), 1, true);
        
        assertEquals(3, dice.getMinValue());
    }
    
    public void testCannotDropMoreDiceThanRolled() {
        try {
            new DropDice(new MultipleDice(DefaultDice.d6, 4), 4, true);
            fail();
        } catch (IllegalArgumentException expected) {}
    }
}
