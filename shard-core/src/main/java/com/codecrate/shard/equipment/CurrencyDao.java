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

public class CurrencyDao {
    public Currency getCurrency(String currencyCode) {
        if (DefaultCurrency.COPPER.getCurrencyCode().equals(currencyCode)) {
            return DefaultCurrency.COPPER;
        } else if (DefaultCurrency.SILVER.getCurrencyCode().equals(currencyCode)) {
            return DefaultCurrency.SILVER;
        } else if (DefaultCurrency.GOLD.getCurrencyCode().equals(currencyCode)) {
            return DefaultCurrency.GOLD;
        }
        throw new IllegalArgumentException("Unknown currency code: " + currencyCode);
    }

}
