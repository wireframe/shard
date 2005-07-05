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
import org.easymock.MockControl;

/**
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class DropDiceTest extends TestCase {
    
    public void testLowValuesDropped() {
        MockControl mockDice = MockControl.createControl(Dice.class);
        Dice dice = (Dice) mockDice.getMock();
        dice.roll();
        mockDice.setReturnValue(6);
        dice.roll();
        mockDice.setReturnValue(6);
        dice.roll();
        mockDice.setReturnValue(6);
        dice.roll();
        mockDice.setReturnValue(5);
        mockDice.replay();
        
        DropDice dice = new DropDice(new MultipleDice(dice, 4), 1);
        
        assertEquals(18, dice.roll());
    }
    
    public void testMinValueComputedCorrectly() {
        DropDice dice = new DropDice(new MultipleDice(new MaxValueDice(RandomDice.d6), 4), 1, true);
        
        assertEquals(3, dice.getMinValue());
    }

    public void testMaxValueComputedCorrectly() {
        DropDice dice = new DropDice(new MultipleDice(new MaxValueDice(RandomDice.d6), 4), 1, true);
        
        assertEquals(18, dice.getMaxValue());
    }

    public void testCannotDropMoreDiceThanRolled() {
        try {
            new DropDice(new MultipleDice(RandomDice.d6, 4), 4, true);
            fail();
        } catch (IllegalArgumentException expected) {}
    }
}
