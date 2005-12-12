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
import java.util.Iterator;

public class CurrencyConverter {
	private final CurrencyDao currencyDao = new CurrencyDao();

    public Money convert(Money money, Currency currency) {
        BigDecimal rate = getConversionRate(money.getCurrency(), currency);
        BigDecimal amount = money.getAmount().multiply(rate);
        
        return new Money(amount, currency, BigDecimal.ROUND_DOWN);
    }

    public Money convertToHighestValueCurrency(Money amount) {
    	int baseAmount = amount.getAmount().multiply(new BigDecimal(amount.getCurrency().getValueInLowestCurrency())).intValue();

    	Iterator it = currencyDao.getCurrencies().iterator();
    	Currency highestCurrency = null;
    	while (it.hasNext()) {
    		Currency currency = (Currency) it.next();
    		if (baseAmount >= currency.getValueInLowestCurrency()) {
    			if (highestCurrency == null) {
    				highestCurrency = currency;
    			} else {
    				if (highestCurrency.getValueInLowestCurrency() < currency.getValueInLowestCurrency()) {
    					highestCurrency = currency;
    				}
    			}
    		}
    	}
    	return convert(amount, highestCurrency);
    }

    private BigDecimal getConversionRate(Currency from, Currency to) {
        BigDecimal rate = new BigDecimal(from.getValueInLowestCurrency()).setScale(2);
        BigDecimal rate2 = new BigDecimal(to.getValueInLowestCurrency()).setScale(2);
        rate = rate.divide(rate2, BigDecimal.ROUND_HALF_UP);
        
        return rate;
    }
}
