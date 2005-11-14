/*
 * Copyright 2002-2004 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.codecrate.shard.ui.command;

import java.io.File;
import java.util.Collection;
import java.util.Collections;

import com.codecrate.shard.equipment.Item;
import com.codecrate.shard.equipment.ItemDao;
import com.codecrate.shard.equipment.ItemFactory;

public class ItemCommandAdapter implements ObjectManagerCommandAdapter {

	private final ItemDao itemDao;
	private final ItemFactory itemFactory;

	private String deleteMessagePropertyName;

	public ItemCommandAdapter(ItemDao itemDao, ItemFactory itemFactory) {
		this.itemDao = itemDao;
		this.itemFactory = itemFactory;
	}

	public String[] getColumnNames() {
		return new String[] {
				"name"
				, "weight"
				, "cost"
		};
	}

	public Collection getObjects() {
		return itemDao.getItems();
	}

	public Object createObject() {
		return itemFactory.createItem("New Item");
	}

	public void saveObject(Object object) {
		itemDao.saveItem((Item) object);
	}

	public void updateObject(Object object) {
		itemDao.updateItem((Item) object);
	}

	public void deleteObject(Object object) {
		itemDao.deleteItem((Item) object);
	}

	public String getDeleteMessagePropertyName() {
		return deleteMessagePropertyName;
	}

	public void setDeleteMessagePropertyName(String deleteMessagePropertyName) {
		this.deleteMessagePropertyName = deleteMessagePropertyName;
	}
	public Collection searchObjects(String query) {
		return itemDao.searchItems(query);
	}

    public Collection importObjects(File file) {
        // TODO Auto-generated method stub
        return Collections.EMPTY_LIST;
    }
}
