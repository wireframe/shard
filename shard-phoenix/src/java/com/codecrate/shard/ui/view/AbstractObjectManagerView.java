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

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.springframework.binding.form.NestingFormModel;
import org.springframework.context.MessageSource;
import org.springframework.richclient.application.PageComponentContext;
import org.springframework.richclient.application.support.AbstractView;
import org.springframework.richclient.command.CommandGroup;
import org.springframework.richclient.command.support.AbstractActionCommandExecutor;
import org.springframework.richclient.command.support.GlobalCommandIds;
import org.springframework.richclient.dialog.ConfirmationDialog;
import org.springframework.richclient.dialog.FormBackedDialogPage;
import org.springframework.richclient.dialog.TitledPageApplicationDialog;
import org.springframework.richclient.form.AbstractForm;
import org.springframework.richclient.form.FormModelHelper;
import org.springframework.richclient.table.support.GlazedTableModel;
import org.springframework.richclient.util.PopupMenuMouseListener;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.swing.EventSelectionModel;

import com.codecrate.shard.ui.ShardCommandIds;
import com.codecrate.shard.ui.command.DeleteCommand;
import com.codecrate.shard.ui.command.PropertiesCommand;
import com.codecrate.shard.ui.form.FormFactory;

public abstract class AbstractObjectManagerView extends AbstractView {
    private JScrollPane scrollPane;
    private JTable table;
    private GlazedTableModel model;
    private JPopupMenu popup;
	private JButton newButton;

	private AbstractActionCommandExecutor deleteCommand;
	private AbstractActionCommandExecutor newCommand;
	private AbstractActionCommandExecutor propertiesCommand;
	private BasicEventList objects;
	private EventSelectionModel selectionModel;

    protected JComponent createControl() {
        JPanel view = new JPanel();
        view.add(getScrollPane(), BorderLayout.WEST);
        view.add(getNewButton(), BorderLayout.EAST);
        return view;
    }
    
    protected void registerLocalCommandExecutors(PageComponentContext context) {
        context.register(GlobalCommandIds.PROPERTIES, getPropertiesCommand());
        context.register(GlobalCommandIds.DELETE, getDeleteCommand());
        context.register(ShardCommandIds.NEW, getNewCommand());
    }
    
    private AbstractActionCommandExecutor getPropertiesCommand() {
    	if (null == propertiesCommand) {
    		propertiesCommand = createPropertiesCommand();
    	}
    	return propertiesCommand;
    }
    
    private AbstractActionCommandExecutor getDeleteCommand() {
    	if (null == deleteCommand) {
    		deleteCommand = createDeleteCommand();
    	}
    	return deleteCommand;
    }

    private AbstractActionCommandExecutor getNewCommand() {
    	if (null == newCommand) {
    		newCommand = createNewCommand();
    		newCommand.setEnabled(true);
    	}
    	return newCommand;
    }

    protected abstract AbstractActionCommandExecutor createDeleteCommand();

    protected abstract AbstractActionCommandExecutor createPropertiesCommand();
    
    protected abstract AbstractActionCommandExecutor createNewCommand();

    protected abstract String[] getColumnNames();
    
    protected abstract Collection createModelObjects();
    
	private EventList getObjects() {
		if (null == objects) {
			objects = new BasicEventList();
			objects.addAll(createModelObjects());
		}
		return objects;
	}
    
    protected Object getSelectedObject() {
    	EventList selected = getSelectionModel().getSelected();
    	if (selected.isEmpty()) {
    		return null;
    	}
    	return selected.iterator().next();
    }
    
    private JScrollPane getScrollPane() {
        if (null == scrollPane) {
            scrollPane = new JScrollPane(getTable());
        }
        return scrollPane;
    }
    
    private JTable getTable() {
        if (null == table) {
        	table = new JTable(getModel());
            table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            table.setSelectionModel(getSelectionModel());
            table.addMouseListener(new PopupMenuMouseListener(getPopupContextMenu()));
        }
        return table;
    }
    
    private EventSelectionModel getSelectionModel() {
    	if (null == selectionModel) {
    		selectionModel = new EventSelectionModel(getObjects());
    	    selectionModel.setSelectionMode(EventSelectionModel.MULTIPLE_INTERVAL_SELECTION_DEFENSIVE);
    	    selectionModel.addListSelectionListener(new ListSelectionListener() {

                public void valueChanged(ListSelectionEvent event) {
                    getDeleteCommand().setEnabled(isDeleteCommandEnabled());
                    getPropertiesCommand().setEnabled(isPropertiesCommandEnabled());
                }

            });
    	}
    	return selectionModel;
    }
    
    private JButton getNewButton() {
    	if (null == newButton) {
    		newButton = new JButton("New...");
    		newButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					getNewCommand().execute();
				}
    		});
    	}
    	return newButton;
    }

    private boolean isDeleteCommandEnabled() {
        if (null == getSelectedObject()) {
            return false;
        }
        return true;
    }

    private boolean isPropertiesCommandEnabled() {
        if (null == getSelectedObject()) {
            return false;
        }
        return true;
    }


    private JPopupMenu getPopupContextMenu() {
        if (null == popup) {
            CommandGroup group = getWindowCommandManager().createCommandGroup("featsCommandGroup", new Object[] {
                    GlobalCommandIds.PROPERTIES, 
                    GlobalCommandIds.DELETE, 
                    ShardCommandIds.NEW});
            popup = group.createPopupMenu();
        }
        return popup;
    }
    
    private GlazedTableModel getModel() {
        if (null == model) {
            MessageSource messageSource = (MessageSource) getApplicationContext().getBean("messageSource");
            model = new GlazedTableModel(getObjects(), messageSource, getColumnNames());
        }
        return model;
    }

    
    protected abstract class AbstractNewCommandExcecutor extends AbstractActionCommandExecutor {
        private Object object;
        private NestingFormModel formModel;
        private AbstractForm form;
        private FormBackedDialogPage page;

        protected abstract Object createObject();
        
        protected abstract AbstractForm createForm(NestingFormModel formModel);

        protected abstract void saveObject(Object object);
        
        public void execute() {
            object = createObject();
            formModel = FormModelHelper.createCompoundFormModel(object);
            form = createForm(formModel);
            page = new FormBackedDialogPage(form);

            TitledPageApplicationDialog dialog = new TitledPageApplicationDialog(page, getWindowControl()) {
                protected void onAboutToShow() {
                    setEnabled(page.isPageComplete());
                }

                protected boolean onFinish() {
                    formModel.commit();
                    saveObject(object);
                    getObjects().add(object);
                    return true;
                }
            };
            dialog.showDialog();
        }
    }
    
    
    protected class DeleteCommandExecutor extends AbstractActionCommandExecutor {
    	private final String title;
		private final String message;
		private final DeleteCommand command;

		public DeleteCommandExecutor(String title, String message, DeleteCommand command) {
			this.title = title;
			this.message = message;
			this.command = command;
    	}
		
        public void execute() {
        	ConfirmationDialog dialog = new ConfirmationDialog(title, getWindowControl(), message) {
                protected void onConfirm() {
                    Object object = getSelectedObject();
                    command.deleteObject(object);
                    getObjects().remove(object);
                }
            };
            dialog.showDialog();
        }
    }
    
    
    protected class AbstractPropertiesCommandExecutor extends AbstractActionCommandExecutor {
        private Object object;
        private AbstractForm form;
        private FormBackedDialogPage page;
        private int index;
		private final FormFactory formFactory;
		private final PropertiesCommand command;
        
        public AbstractPropertiesCommandExecutor(FormFactory formFactory, PropertiesCommand command) {
			this.formFactory = formFactory;
			this.command = command;	
        }
        
        public void execute() {
            object = getSelectedObject();
            index = getObjects().indexOf(object);
            form = formFactory.createForm(object);
            page = new FormBackedDialogPage(form);

            TitledPageApplicationDialog dialog = new TitledPageApplicationDialog(page, getWindowControl()) {
                protected void onAboutToShow() {
                    setEnabled(page.isPageComplete());
                }

                protected boolean onFinish() {
                	form.getFormModel().commit();
                    command.updateObject(object);
                    getObjects().set(index, object);
                    return true;
                }
            };
            dialog.showDialog();
        }
    }
}
