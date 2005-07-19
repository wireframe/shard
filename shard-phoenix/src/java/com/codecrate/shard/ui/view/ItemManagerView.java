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
package com.codecrate.shard.ui.view;

import org.springframework.richclient.command.support.AbstractActionCommandExecutor;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.EventList;

import com.codecrate.shard.equipment.ItemDao;
import com.codecrate.shard.equipment.ItemFactory;

public class ItemManagerView extends AbstractObjectManagerView {

	private ItemDao itemDao;
	private ItemFactory itemFactory;
	private EventList items;
	
	public void setItemDao(ItemDao itemDao) {
		this.itemDao = itemDao;
	}
	
	public void setItemFactory(ItemFactory itemFactory) {
		this.itemFactory = itemFactory;
	}
	
	protected AbstractActionCommandExecutor getPropertiesCommand() {
		// TODO Auto-generated method stub
		return null;
	}

	protected AbstractActionCommandExecutor getDeleteCommand() {
		// TODO Auto-generated method stub
		return null;
	}

	protected AbstractActionCommandExecutor getNewCommand() {
		// TODO Auto-generated method stub
		return null;
	}

	protected String[] getColumnNames() {
		return new String[] {
				"name"
				, "weight"
				, "cost"
		};
	}

	protected EventList getObjects() {
		if (null == items) {
			items = new BasicEventList();
			items.addAll(itemDao.getItems());
		}
		return items;
	}
}
