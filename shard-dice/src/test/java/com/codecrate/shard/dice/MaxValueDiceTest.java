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
public class MaxValueDiceTest extends TestCase {

	public void testRollReturnsMaxValue() {
    	MaxValueDice dice = new MaxValueDice(RandomDice.d6);
    	assertEquals(6, dice.roll());
    }
	
	public void testMinValueDelegatesToDefaultDice() {
    	MaxValueDice dice = new MaxValueDice(RandomDice.d6);
    	assertEquals(1, dice.getMinValue());
	}
	
	public void testMaxValueDelegatesToDefaultDice() {
	    MaxValueDice dice = new MaxValueDice(RandomDice.d6);
    	assertEquals(6, dice.getMaxValue());
	}
}
