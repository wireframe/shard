package com.codecrate.shard.equipment;

import junit.framework.TestCase;

public class CurrencyConverterTest extends TestCase {

    public void testConversionFromCopperToGold() {
        Money money = new Money(100, DefaultCurrency.COPPER);
        CurrencyConverter converter = new CurrencyConverter();
        Money value = converter.convert(money, DefaultCurrency.GOLD);
        
        assertEquals(1, value.getAmount().intValue());
    }
    
    public void testConversionFromGoldToCopper() {
        Money money = new Money(1, DefaultCurrency.GOLD);
        CurrencyConverter converter = new CurrencyConverter();
        Money value = converter.convert(money, DefaultCurrency.COPPER);
        
        assertEquals(100, value.getAmount().intValue());
    }
    
    public void testConvertionToSameCurrencyStaysSame() {
        Money money = new Money(1, DefaultCurrency.SILVER);
        CurrencyConverter converter = new CurrencyConverter();
        Money value = converter.convert(money, DefaultCurrency.SILVER);
        
        assertEquals(1, value.getAmount().intValue());
    }
}
