package com.codecrate.shard.equipment;

import java.util.Currency;
import java.util.Locale;

import junit.framework.TestCase;

public class MoneyTest extends TestCase {

    public void testCurrencyCodeUsedForToString() {
        Money money = new Money(1, Currency.getInstance(Locale.US));
        assertEquals("1.00 USD", money.toString());
    }
}
