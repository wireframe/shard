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

public class DefaultCurrency implements Currency {

    public static final Currency COPPER = new DefaultCurrency("CP", "c", 0);
    public static final Currency SILVER = new DefaultCurrency("SP", "s", 0);
    public static final Currency GOLD = new DefaultCurrency("GP", "g", 0);
    
    
    private final String currencyCode;
    private final String symbol;
    private final int fractionalDigits;

    public DefaultCurrency(String currencyCode, String symbol, int fractionalDigits) {
        this.currencyCode = currencyCode;
        this.symbol = symbol;
        this.fractionalDigits = fractionalDigits;
    }
    
    public String toString() {
        return currencyCode;
    }
    
    public String getCurrencyCode() {
        return currencyCode;
    }
    public int getDefaultFractionDigits() {
        return fractionalDigits;
    }
    public String getSymbol() {
        return symbol;
    }
}
