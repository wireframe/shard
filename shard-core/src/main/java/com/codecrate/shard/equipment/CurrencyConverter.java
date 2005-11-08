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

import java.math.BigDecimal;

public class CurrencyConverter {
    public Money convert(Money money, Currency currency) {
        BigDecimal rate = getConversionRate(money.getCurrency(), currency);
        BigDecimal amount = money.getAmount().multiply(rate);
        
        return new Money(amount.doubleValue(), currency);
    }
    
    public BigDecimal getConversionRate(Currency from, Currency to) {
        BigDecimal rate = new BigDecimal(getFactor(from)).setScale(2);
        BigDecimal rate2 = new BigDecimal(getFactor(to)).setScale(2);
        rate = rate.divide(rate2, BigDecimal.ROUND_HALF_UP);
        
        return rate;
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
