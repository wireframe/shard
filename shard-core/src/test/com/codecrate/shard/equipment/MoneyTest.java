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
package com.codecrate.shard.equipment;

import junit.framework.TestCase;

public class MoneyTest extends TestCase {

    public void testCurrencyCodeUsedForToString() {
        Money money = new Money(1, DefaultCurrency.COPPER);
        assertEquals("1 CP", money.toString());
    }
    
    public void testCanNotAddDifferentCurrencies() {
        Money copper = new Money(1, DefaultCurrency.COPPER);
        Money gold = new Money(1, DefaultCurrency.GOLD);
        
        try {
            copper.add(gold);
            fail();
        } catch (IllegalArgumentException expected) {}
    }
    
    public void testValueOf() throws Exception {
    	Money money = Money.valueOf("1 CP");
    	assertEquals("1", money.getAmount().toString());
    	assertEquals(DefaultCurrency.COPPER, money.getCurrency());
    }
    
    public void testSameCurrencyComparasion() {
        Money oneGP = new Money(1, DefaultCurrency.GOLD);
        Money tenGP = new Money(10, DefaultCurrency.GOLD);
        assertTrue(tenGP.greaterThan(oneGP));
        assertFalse(oneGP.greaterThan(tenGP));
        
        assertTrue(oneGP.lessThan(tenGP));
        assertFalse(tenGP.lessThan(oneGP));
    }
}
