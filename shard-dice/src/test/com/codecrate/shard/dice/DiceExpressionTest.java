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
public class DiceExpressionTest extends TestCase {

    public void testDiceParsesSides() {
        DiceExpression dice = new DiceExpression("d4");
        assertEquals(4, dice.getMaxValue());
    }
    
    public void testDiceParsesMultiples() {
        DiceExpression dice = new DiceExpression("2d4");
        assertEquals(8, dice.getMaxValue());
    }
    
    public void testDiceParsesMultiplesWithModifier() {
        DiceExpression dice = new DiceExpression("2d4+1");
        assertEquals(9, dice.getMaxValue());
        assertEquals(3, dice.getMinValue());
    }
    
    public void testDiceParsesModifier() {
        DiceExpression dice = new DiceExpression("d4+1");
        assertEquals(5, dice.getMaxValue());
    }
    
    public void testDiceParsesNegativeModifier() {
        DiceExpression dice = new DiceExpression("d4-1");
        assertEquals(3, dice.getMaxValue());
    }
    
    public void testDiceParsesMultiplier() {
        DiceExpression dice = new DiceExpression("d4x2");
        assertEquals(8, dice.getMaxValue());
    }
    
    public void testCannotParseMultiplierAndModifier() {
        try {
             new DiceExpression("d4x2+1");
            fail("Exception should be thrown");
        } catch (IllegalArgumentException expected) { }
    }
    
    public void testSpacesAreTrimmed() {
        DiceExpression dice = new DiceExpression("d4 +   1");
        assertEquals(5, dice.getMaxValue());
        assertEquals("1d4+1", dice.toString());
    }
    
    public void testExpressionEqualsProgrammaticallyConstructedDice() {
        DiceExpression dice = new DiceExpression("1d4+1");
        Dice other = new ModifiedDice(new RandomDice(4), 1);
        
        assertTrue(dice.equals(other));
    }
}
