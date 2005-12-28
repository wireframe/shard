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

import com.codecrate.shard.ShardHibernateTestCaseSupport;

/**
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class HibernateItemDaoTest extends ShardHibernateTestCaseSupport {
	private ItemDao itemDao;
	private ItemFactory itemFactory;

	public void setItemFactory(ItemFactory itemFactory) {
		this.itemFactory = itemFactory;
	}

	public void setItemDao(ItemDao dao) {
		this.itemDao = dao;
	}

	protected void onSetUpInTransaction() throws Exception {
		super.onSetUpInTransaction();

		Item basicItem = new DefaultItem("test item", new BigDecimal(10), new Money(10, DefaultCurrency.GOLD), null);
		itemDao.saveItem(basicItem);
	}

	public void testLoadsItems() throws Exception {
        Collection Items = itemDao.getItems();
        assertFalse(Items.isEmpty());
    }

	public void testSaveNewItem() {
		Item item = itemFactory.createItem("New Item", new BigDecimal(0), Coin.COPPER_PIECE.getCost(), null);
		Item item2 = itemDao.saveItem(item);
		assertSame(item, item2);
	}

	public void testSearchItems() throws Exception {
		Collection results = itemDao.searchItems("test");
		assertFalse(results.isEmpty());
	}
}
