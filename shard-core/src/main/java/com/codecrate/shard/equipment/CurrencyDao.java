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

import java.util.Arrays;
import java.util.Collection;

public class CurrencyDao {
	private static final Collection<Currency> CURRENCIES = Arrays.asList(new Currency[] {
			Currency.COPPER
			, Currency.SILVER
			, Currency.GOLD
	});

	public Currency getCurrency(String currencyCode) {
		for (Currency currency : getCurrencies()) {
			if (currency.getCurrencyCode().equals(currencyCode)) {
				return currency;
			}
		}
        throw new IllegalArgumentException("Unknown currency code: " + currencyCode);
    }

    public Currency getLowestValueCurrency() {
    	Currency cheapestCurrency = null;
		for (Currency currency : getCurrencies()) {
			if (cheapestCurrency == null) {
				cheapestCurrency = currency;
			} else if (cheapestCurrency.getValueInLowestCurrency() > currency.getValueInLowestCurrency()) {
				cheapestCurrency = currency;
			}
		}
    	return cheapestCurrency;
    }

    public Collection<Currency> getCurrencies() {
    	return CURRENCIES;
    }
}
