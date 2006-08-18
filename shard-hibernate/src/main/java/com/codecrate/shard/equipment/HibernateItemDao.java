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
import java.util.Collection;

import com.codecrate.shard.BasicHibernateDao;
import com.codecrate.shard.search.HibernateObjectSearcher;
import com.codecrate.shard.source.Source;

/**
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class HibernateItemDao extends BasicHibernateDao implements ItemDao, ItemFactory {

	public HibernateItemDao(HibernateObjectSearcher searcher) {
		super(searcher, DefaultItem.class, "name");
	}

	public Collection getItems() {
		return getAllObjects();
    }

    public Item createItem(String name, BigDecimal weight, Money cost, Source source) {
    	return new DefaultItem(name, weight, cost, source);
    }

    public Item saveItem(Item item) {
    	return (Item) saveObject(item);
    }

    public void updateItem(Item item) {
    	updateItem(item);
    }

    public void deleteItem(Item item) {
    	deleteObject(item);
    }

    public Collection searchItems(String query) {
    	return searchObjects(query);
    }
}