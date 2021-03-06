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

public class CurrencyConverterTest extends TestCase {

    public void testConversionFromCopperToGold() {
        Money money = new Money(100, Currency.COPPER);
        CurrencyConverter converter = new CurrencyConverter();
        Money value = converter.convert(money, Currency.GOLD);
        
        assertEquals(1, value.getAmount().intValue());
    }
    
    public void testConversionFromGoldToCopper() {
        Money money = new Money(1, Currency.GOLD);
        CurrencyConverter converter = new CurrencyConverter();
        Money value = converter.convert(money, Currency.COPPER);
        
        assertEquals(100, value.getAmount().intValue());
    }
    
    public void testConvertionToSameCurrencyStaysSame() {
        Money money = new Money(1, Currency.GOLD);
        CurrencyConverter converter = new CurrencyConverter();
        Money value = converter.convert(money, Currency.GOLD);
        
        assertEquals(1, value.getAmount().intValue());
    }
    
    public void testConvertionRoundsDown() {
        Money money = new Money(99, Currency.COPPER);
        Money value = new CurrencyConverter().convert(money, Currency.GOLD);
        
        assertEquals(0, value.getAmount().intValue());
    }
    
    public void testConvertToHighestCurrencyIncreasesCurrency() throws Exception {
    	Money money = Money.valueOf("200 CP");
    	Money value = new CurrencyConverter().convertToHighestValueCurrency(money);
    	
    	assertEquals(Money.valueOf("2 GP"), value);
    }

    public void testConvertToHighestCurrencyKeepsSameCurrencyIfNotEnough() throws Exception {
    	Money money = Money.valueOf("9 CP");
    	Money value = new CurrencyConverter().convertToHighestValueCurrency(money);
    	
    	assertEquals(Money.valueOf("9 CP"), value);
    }

}
