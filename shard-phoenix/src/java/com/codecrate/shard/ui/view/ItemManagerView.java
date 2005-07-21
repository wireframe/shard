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

import org.springframework.binding.form.NestingFormModel;
import org.springframework.richclient.command.support.AbstractActionCommandExecutor;
import org.springframework.richclient.dialog.ConfirmationDialog;
import org.springframework.richclient.dialog.FormBackedDialogPage;
import org.springframework.richclient.dialog.TitledPageApplicationDialog;
import org.springframework.richclient.form.FormModelHelper;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.EventList;

import com.codecrate.shard.equipment.Item;
import com.codecrate.shard.equipment.ItemDao;
import com.codecrate.shard.equipment.ItemFactory;
import com.codecrate.shard.ui.form.ItemForm;

public class ItemManagerView extends AbstractObjectManagerView {

	private ItemDao itemDao;
	private ItemFactory itemFactory;
	private EventList items;

	private DeleteCommandExecutor deleteCommand;
	private NewCommandExecutor newCommand;
	private PropertiesCommandExecutor propertiesCommand;
	
	public void setItemDao(ItemDao itemDao) {
		this.itemDao = itemDao;
	}
	
	public void setItemFactory(ItemFactory itemFactory) {
		this.itemFactory = itemFactory;
	}
	
	protected AbstractActionCommandExecutor getPropertiesCommand() {
		if (null == propertiesCommand) {
			propertiesCommand = new PropertiesCommandExecutor();
		}
		return propertiesCommand;
	}

	protected AbstractActionCommandExecutor getDeleteCommand() {
		if (null == deleteCommand) {
			deleteCommand = new DeleteCommandExecutor();
		}
		return deleteCommand;
	}

	protected AbstractActionCommandExecutor getNewCommand() {
		if (null == newCommand) {
			newCommand = new NewCommandExecutor();
		}
		return newCommand;
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

	private Item getSelectedItem() {
		return (Item) getSelectedObject();
	}
	
    private class DeleteCommandExecutor extends AbstractActionCommandExecutor {
        public void execute() {
        	String title = getMessage("confirmDeleteItemDialog.title");
        	String message = getMessage("confirmDeleteItemDialog.label");
        	ConfirmationDialog dialog = new ConfirmationDialog(title, message) {
                protected void onConfirm() {
                    Item item = getSelectedItem();
                    itemDao.deleteItem(item);
                    getObjects().remove(item);
                }
            };
            dialog.showDialog();
        }
    }


    private class PropertiesCommandExecutor extends AbstractActionCommandExecutor {
        private Item item;
        private NestingFormModel itemFormModel;
        private ItemForm itemForm;
        private FormBackedDialogPage page;

        public void execute() {
            item = getSelectedItem();
            itemFormModel = FormModelHelper.createCompoundFormModel(item);
            itemForm = new ItemForm(itemFormModel);
            page = new FormBackedDialogPage(itemForm);

            TitledPageApplicationDialog dialog = new TitledPageApplicationDialog(page, getWindowControl()) {
                protected void onAboutToShow() {
                    setEnabled(page.isPageComplete());
                }

                protected boolean onFinish() {
                    itemFormModel.commit();
                    itemDao.updateItem(item);
                    int index = getObjects().indexOf(item);
                    getObjects().set(index, item);
                    return true;
                }
            };
            dialog.showDialog();
        }
    }
  
    
    private class NewCommandExecutor extends AbstractActionCommandExecutor {
        private Item item;
        private NestingFormModel itemFormModel;
        private ItemForm itemForm;
        private FormBackedDialogPage page;

        public void execute() {
            item = itemFactory.createItem("New Item");
            itemFormModel = FormModelHelper.createCompoundFormModel(item);
            itemForm = new ItemForm(itemFormModel);
            page = new FormBackedDialogPage(itemForm);

            TitledPageApplicationDialog dialog = new TitledPageApplicationDialog(page, getWindowControl()) {
                protected void onAboutToShow() {
                    setEnabled(page.isPageComplete());
                }

                protected boolean onFinish() {
                    itemFormModel.commit();
                    itemDao.saveItem(item);
                    getObjects().add(item);
                    return true;
                }
            };
            dialog.showDialog();
        }
    }
}
