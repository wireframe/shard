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
public interface Currency {
    String getCurrencyCode();
    
    int getDefaultFractionDigits();
    
    int getValueInLowestCurrency();

    String getSymbol();
}
