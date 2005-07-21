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

import java.util.Collection;

public interface ItemDao {

	/**
	 * gets all items.
	 * @return
	 */
	Collection getItems();

	/**
	 * saves a newly created item.
	 * @param item
	 * @return
	 */
	Item saveItem(Item item);

	/**
	 * delete an item.
	 * @param item
	 */
	void deleteItem(Item item);

	/**
	 * save changes to an updated item.
	 * @param item
	 */
	void updateItem(Item item);
}
