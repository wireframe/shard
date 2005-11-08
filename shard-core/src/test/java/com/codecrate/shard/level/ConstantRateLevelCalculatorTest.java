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

import java.math.BigDecimal;

import com.codecrate.shard.level.ConstantRateLevelCalculator;

import junit.framework.TestCase;

public class ConstantRateLevelCalculatorTest extends TestCase {

    public void testConstantProgressionCalculation() {
        ConstantRateLevelCalculator calc = new ConstantRateLevelCalculator(0, new BigDecimal(1));
        assertEquals(0, calc.calculateValue(1));
        assertEquals(19, calc.calculateValue(20));
    }
    
    public void testPercentageProgressionCalculation() {
        ConstantRateLevelCalculator calc = new ConstantRateLevelCalculator(2, new BigDecimal(".5"));
        assertEquals(2, calc.calculateValue(1));
        assertEquals(12, calc.calculateValue(20));
    }
}
