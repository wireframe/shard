package com.codecrate.shard.equipment;

import java.math.BigDecimal;

public class CurrencyConverter {
    public Money convert(Money money, Currency currency) {
        BigDecimal rate = new BigDecimal(getFactor(money.getCurrency())).setScale(2);
        BigDecimal rate2 = new BigDecimal(getFactor(currency)).setScale(2);
        rate = rate.divide(rate2, BigDecimal.ROUND_HALF_UP);
        BigDecimal amount = money.getAmount().multiply(rate);
        
        return new Money(amount.doubleValue(), currency);
    }
    
    private int getFactor(Currency currency) {
        if (DefaultCurrency.COPPER.equals(currency)) {
            return 1;
        } else if (DefaultCurrency.SILVER.equals(currency)) {
            return 10;
        } else if (DefaultCurrency.GOLD.equals(currency)) {
            return 100;
        }
        throw new IllegalArgumentException("Unknown currency: " + currency);
    }

}
