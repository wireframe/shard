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

import java.util.Collection;

import org.springframework.binding.form.NestingFormModel;
import org.springframework.richclient.command.support.AbstractActionCommandExecutor;
import org.springframework.richclient.form.AbstractForm;

import com.codecrate.shard.equipment.Item;
import com.codecrate.shard.equipment.ItemDao;
import com.codecrate.shard.equipment.ItemFactory;
import com.codecrate.shard.ui.form.ItemForm;

public class ItemManagerView extends AbstractObjectManagerView {

	private ItemDao itemDao;
	private ItemFactory itemFactory;

	public void setItemDao(ItemDao itemDao) {
		this.itemDao = itemDao;
	}
	
	public void setItemFactory(ItemFactory itemFactory) {
		this.itemFactory = itemFactory;
	}
	
	protected AbstractActionCommandExecutor createPropertiesCommand() {
		return new AbstractPropertiesCommandExecutor(){

			protected AbstractForm createForm(NestingFormModel formModel) {
				return new ItemForm(formModel);
			}

			protected void updateObject(Object object) {
				itemDao.updateItem((Item) object);
			}
			
		};
	}

	protected AbstractActionCommandExecutor createDeleteCommand() {
    	String title = getMessage("confirmDeleteItemDialog.title");
    	String message = getMessage("confirmDeleteItemDialog.label");
		return new AbstractDeleteCommandExecutor(title, message) {

			protected void deleteObject(Object object) {
				itemDao.deleteItem((Item) object);
			}
		};
	}

	protected AbstractActionCommandExecutor createNewCommand() {
		return new AbstractNewCommandExcecutor() {

			protected Object createObject() {
	            return itemFactory.createItem("New Item");
			}

			protected AbstractForm createForm(NestingFormModel model) {
				return new ItemForm(model);
			}

			protected void saveObject(Object object) {
                itemDao.saveItem((Item) object);
			}
		};
	}

	protected String[] getColumnNames() {
		return new String[] {
				"name"
				, "weight"
				, "cost"
		};
	}

	protected Collection createModelObjects() {
		return itemDao.getItems();
	}
}
