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
package com.codecrate.shard.transfer.pcgen;

import java.math.BigDecimal;
import java.util.Map;

import com.codecrate.shard.equipment.Currency;
import com.codecrate.shard.equipment.DefaultCurrency;
import com.codecrate.shard.equipment.Item;
import com.codecrate.shard.equipment.ItemDao;
import com.codecrate.shard.equipment.ItemFactory;
import com.codecrate.shard.equipment.Money;

public class PcgenItemLineHandler extends AbstractPcgenLineHandler {
	private static final String COST_TAG_NAME = "COST";
	private static final String WEIGHT_TAG_NAME = "WT";

    private final ItemFactory itemFactory;
    private final ItemDao itemDao;

    public PcgenItemLineHandler(ItemFactory itemFactory, ItemDao itemDao) {
        this.itemFactory = itemFactory;
        this.itemDao = itemDao;
    }

    public Object handleParsedLine(String name, Map tags) {
    	BigDecimal weight = new BigDecimal(getStringTagValue(WEIGHT_TAG_NAME, tags));
    	BigDecimal amount = new BigDecimal(getStringTagValue(COST_TAG_NAME, tags));
    	amount = amount.movePointRight(2);
    	Currency currency = DefaultCurrency.COPPER;
    	if (amount.compareTo(new BigDecimal(100)) > 0) {
    		amount = amount.movePointLeft(2);
    		currency = DefaultCurrency.GOLD;
    	} else if (amount.compareTo(new BigDecimal(10)) > 0) {
    		amount = amount.movePointLeft(1);
    		currency = DefaultCurrency.SILVER;
    	}
    	Money cost = new Money(amount, currency, BigDecimal.ROUND_HALF_UP);

        Item item = itemFactory.createItem(name, weight, cost);
        return itemDao.saveItem(item);
    }
}
