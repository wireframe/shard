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
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import junit.framework.TestCase;

/**
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class DiceExpressionTest extends TestCase {

    public void testDiceParsesSides() {
        DiceExpression dice = new DiceExpression("d4");
        assertEquals(1, dice.getMinValue());
        assertEquals(4, dice.getMaxValue());
    }

    public void testDiceParsesMultiples() {
        DiceExpression dice = new DiceExpression("2d4");
        assertEquals(2, dice.getMinValue());
        assertEquals(8, dice.getMaxValue());
    }

    public void testDiceParsesMultiplesWithModifier() {
        DiceExpression dice = new DiceExpression("2d4+1");
        assertEquals(3, dice.getMinValue());
        assertEquals(9, dice.getMaxValue());
    }

    public void testDiceParsesModifier() {
        DiceExpression dice = new DiceExpression("d4+1");
        assertEquals(2, dice.getMinValue());
        assertEquals(5, dice.getMaxValue());
    }

    public void testDiceParsesNegativeModifier() {
        DiceExpression dice = new DiceExpression("d4-1");
        assertEquals(0, dice.getMinValue());
        assertEquals(3, dice.getMaxValue());
    }

    public void testDiceParsesMultiplier() {
        DiceExpression dice = new DiceExpression("d4*2");
        assertEquals(2, dice.getMinValue());
        assertEquals(8, dice.getMaxValue());
    }

    public void testStringRepresentationTrimsSpaces() {
        DiceExpression dice = new DiceExpression("1d4 +   1");
        assertEquals("1d4+1", dice.toString());
    }

    public void testExpressionEqualsProgrammaticallyConstructedDice() {
        DiceExpression dice = new DiceExpression("1d4+1");
        Dice other = new ModifiedDice(new RandomDice(4), 1);

        assertTrue(dice.equals(other));
    }

    public void testExpressionParsingOfLargerDice() {
        DiceExpression dice = new DiceExpression("1d20");
        assertEquals(1, dice.getMinValue());
        assertEquals(20, dice.getMaxValue());
    }

    public void testMultiDiceExpressionsAreEvaluated() {
        DiceExpression dice = new DiceExpression("d4+d6");
        assertEquals(2, dice.getMinValue());
        assertEquals(10, dice.getMaxValue());
    }

    public void testComplexMultiDiceExpressionsAreEvaluated() {
        DiceExpression dice = new DiceExpression("d4+ d6(1d4 + d20)");
        assertEquals(3, dice.getMinValue());
        assertEquals(148, dice.getMaxValue());
    }

    public void testInvalidDiceExpressionThrowsException() {
        try {
            new DiceExpression("asdf");
            fail("Exception should be thrown for invalid dice expression");
        } catch (IllegalArgumentException expected) { }
    }

    public void testExpressionsWithVariablesAreAllowed() {
      Map<String, Double> variables = new HashMap<String, Double>();
      variables.put("STR", 1d);
      Dice dice = new DiceExpression("1d4 + STR", variables);
      assertEquals(2, dice.getMinValue());
      assertEquals(5, dice.getMaxValue());
    }
    
    public void testExceptionWhenUndeclaredVariablesAreUsed() {
      try {
        new DiceExpression("1d4 + STR");
        fail();
      } catch (IllegalArgumentException expected) {}
    }
    
    public void testAllValuesAreRolled() {
    	DiceExpression dice = new DiceExpression("2d4");
    	Set rolls = new HashSet();
    	for (int x = 0; x < 100; x++) {
			rolls.add(new Integer(dice.roll()));
		}

    	assertEquals(7, rolls.size());
    }
}
