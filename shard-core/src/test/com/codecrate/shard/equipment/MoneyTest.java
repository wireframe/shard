package com.codecrate.shard.equipment;

import junit.framework.TestCase;

public class MoneyTest extends TestCase {

    public void testCurrencyCodeUsedForToString() {
        Money money = new Money(1, DefaultCurrency.COPPER);
        assertEquals("1 CP ", money.toString());
    }
}
