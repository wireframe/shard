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
public class ModifiedDiceTest extends TestCase {

    public void testModifierAddedToMaxValue() {
        ModifiedDice dice = new ModifiedDice(RandomDice.d4, 5);
        assertEquals(9, dice.getMaxValue());
    }

    public void testModifierAddedToMinValue() {
        ModifiedDice dice = new ModifiedDice(RandomDice.d4, 5);
        assertEquals(6, dice.getMinValue());
    }

    public void testModifierAppliedToRoll() {
        ModifiedDice dice = new ModifiedDice(new MaxValueDice(RandomDice.d6), -5);
        assertEquals(1, dice.roll());
    }
}
