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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class CurrencyDao {
    public Currency getCurrency(String currencyCode) {
    	for (Iterator currencies = getCurrencies().iterator(); currencies.hasNext();) {
			Currency currency = (Currency) currencies.next();
			if (currency.getCurrencyCode().equals(currencyCode)) {
				return currency;
			}
		}
        throw new IllegalArgumentException("Unknown currency code: " + currencyCode);
    }
    
    public Collection getCurrencies() {
    	Collection results = new ArrayList();
    	results.add(DefaultCurrency.COPPER);
    	results.add(DefaultCurrency.SILVER);
    	results.add(DefaultCurrency.GOLD);
    	
    	return results;
    }
}
