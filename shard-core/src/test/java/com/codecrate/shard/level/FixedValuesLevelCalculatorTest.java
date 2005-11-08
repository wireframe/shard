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
package com.codecrate.shard.level;

import java.util.ArrayList;
import java.util.Arrays;

import junit.framework.TestCase;

public class FixedValuesLevelCalculatorTest extends TestCase {

    public void testErrorWhenNoLevelFound() {
        try {
            FixedValuesLevelCalculator calc = new FixedValuesLevelCalculator(new ArrayList());
            calc.calculateValue(1);
            fail();
        } catch (IllegalArgumentException expected) {} 
    }
    
    public void testValueReturnedForLevel() {
        FixedValuesLevelCalculator calc = new FixedValuesLevelCalculator(Arrays.asList(new Integer[] {new Integer(2)}));
        assertEquals(2, calc.calculateValue(1));
    }
}
