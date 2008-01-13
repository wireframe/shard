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

public class Coin extends Item {
    public static final Coin COPPER_PIECE = new Coin("Copper Coin", new BigDecimal(".1"), Currency.COPPER);
    public static final Coin SILVER_PIECE = new Coin("Silver Coin", new BigDecimal(".1"), Currency.SILVER);
    public static final Coin GOLD_PIECE = new Coin("Gold Coin", new BigDecimal(".1"), Currency.GOLD);

    private static final int SINGLE_COIN = 1;

    public Coin(String name, BigDecimal weight, Currency currency) {
    	super(name, weight, new Money(SINGLE_COIN, currency), null);
    }
}
