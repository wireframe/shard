package com.codecrate.shard.equipment;

import junit.framework.TestCase;

public class MoneyTest extends TestCase {

    public void testCurrencyCodeUsedForToString() {
        Money money = new Money(1, DefaultCurrency.COPPER);
        assertEquals("1 CP ", money.toString());
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
    	Money.valueOf("1 CP ");
    }
}
