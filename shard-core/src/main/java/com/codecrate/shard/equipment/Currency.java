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

/**
 * custom currency class.
 * since the java jdk does not allow us to create custom currencies outside of ISO 
 * standard currencies, we'll use our own to allow our made up currencies.
 * The methods should mirror the jdk class as closely as possible to allow for easy
 * migration to the jdk class in the future if possible. 
 * 
 * @see java.util.Currency
 */
public class Currency implements Comparable<Object> {

    public static final Currency COPPER = new Currency("CP", "c", 0, 1);
    public static final Currency SILVER = new Currency("SP", "s", 0, 10);
    public static final Currency GOLD = new Currency("GP", "g", 0, 100);
    
    
    private final String currencyCode;
    private final String symbol;
    private final int fractionalDigits;
    private final int value;

    public Currency(String currencyCode, String symbol, int fractionalDigits, int value) {
        this.currencyCode = currencyCode;
        this.symbol = symbol;
        this.fractionalDigits = fractionalDigits;
        this.value = value;
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

	public int getValueInLowestCurrency() {
		return value;
	}

	public int compareTo(Object target) {
		return new Integer(value).compareTo(((Currency)target).getValueInLowestCurrency());
	}
}
