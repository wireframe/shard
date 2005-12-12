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
import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import org.easymock.MockControl;

import com.codecrate.shard.equipment.Item;
import com.codecrate.shard.equipment.ItemDao;
import com.codecrate.shard.equipment.ItemFactory;
import com.codecrate.shard.equipment.Money;

public class PcgenItemLineHandlerTest extends TestCase {

    public void testBasicImport() throws Exception {
        Map tags = new HashMap();
        tags.put("COST", ".99");
        tags.put("WT", ".99");

        MockControl mockItem = MockControl.createControl(Item.class);
        Item item = (Item) mockItem.getMock();
        mockItem.replay();

        MockControl mockItemFactory = MockControl.createControl(ItemFactory.class);
        ItemFactory itemFactory = (ItemFactory) mockItemFactory.getMock();
        itemFactory.createItem("Brick", new BigDecimal(".99"), Money.valueOf("9 SP"));
        mockItemFactory.setReturnValue(item);
        mockItemFactory.replay();

        MockControl mockItemDao = MockControl.createControl(ItemDao.class);
        ItemDao itemDao = (ItemDao) mockItemDao.getMock();
        itemDao.saveItem(item);
        mockItemDao.setReturnValue(item);
        mockItemDao.replay();

        PcgenItemLineHandler importer = new PcgenItemLineHandler(itemFactory, itemDao);
		Object result = importer.handleParsedLine("Brick", tags);

		assertNotNull(result);
    }
}
