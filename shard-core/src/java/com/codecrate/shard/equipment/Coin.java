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

public class Coin implements Item {
    public static final Coin COPPER_PIECE = new Coin("Copper Coin", new BigDecimal(".1"), 1, DefaultCurrency.COPPER);
    public static final Coin SILVER_PIECE = new Coin("Silver Coin", new BigDecimal(".1"), 1, DefaultCurrency.SILVER);
    public static final Coin GOLD_PIECE = new Coin("Gold Coin", new BigDecimal(".1"), 1, DefaultCurrency.GOLD);
    
    private final BigDecimal weight;
    private final Money money;
	private final String name;

    public Coin(String name, BigDecimal weight, int cost, Currency currency) {
        this.name = name;
		this.weight = weight;
        this.money = new Money(cost, currency);
    }
    
    public String getName() {
    	return name;
    }
    
    public BigDecimal getWeight() {
        return weight;
    }

    public Money getCost() {
        return money;
    }
}
