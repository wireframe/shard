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
import com.codecrate.shard.equipment.CurrencyConverter;
import com.codecrate.shard.equipment.CurrencyDao;
import com.codecrate.shard.equipment.Item;
import com.codecrate.shard.equipment.ItemDao;
import com.codecrate.shard.equipment.ItemFactory;
import com.codecrate.shard.equipment.Money;
import com.codecrate.shard.source.Source;

public class PcgenItemLineHandler extends AbstractPcgenLineHandler {
	private static final int COST_TO_LOWEST_CURRENCY_MULTIPLIER = 2;
	private static final String COST_TAG_NAME = "COST";
	private static final String WEIGHT_TAG_NAME = "WT";
	private static final String DEFAULT_WEIGHT = "0";
	private static final String DEFAULT_COST = "0";

    private final ItemFactory itemFactory;
    private final ItemDao itemDao;
    private final CurrencyConverter currencyConverter = new CurrencyConverter();
    private final CurrencyDao currencyDao = new CurrencyDao();

    public PcgenItemLineHandler(ItemFactory itemFactory, ItemDao itemDao) {
        this.itemFactory = itemFactory;
        this.itemDao = itemDao;
    }

    public Object handleParsedLine(String name, Map tags, Source source) {
    	BigDecimal weight = new BigDecimal(getStringTagValue(WEIGHT_TAG_NAME, tags, DEFAULT_WEIGHT));
    	BigDecimal amount = new BigDecimal(getStringTagValue(COST_TAG_NAME, tags, DEFAULT_COST)).movePointRight(COST_TO_LOWEST_CURRENCY_MULTIPLIER);
    	Currency currency = currencyDao.getLowestValueCurrency();
    	Money cost = currencyConverter.convertToHighestValueCurrency(new Money(amount, currency, BigDecimal.ROUND_HALF_UP));

        Item item = itemFactory.createItem(name, weight, cost, source);
        return itemDao.saveItem(item);
    }
}
