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
import ca.odell.glazedlists.FilterList;
import ca.odell.glazedlists.Matcher;
import ca.odell.glazedlists.swing.EventSelectionModel;

import com.codecrate.shard.ui.ShardCommandIds;
import com.codecrate.shard.ui.command.DeleteCommand;
import com.codecrate.shard.ui.command.ImportCommand;
import com.codecrate.shard.ui.command.NewCommand;
import com.codecrate.shard.ui.command.PropertiesCommand;
import com.codecrate.shard.ui.command.SearchObjectsCommand;
import com.codecrate.shard.ui.command.ViewObjectsCommand;
import com.codecrate.shard.ui.component.SearchComponent;
import com.codecrate.shard.ui.form.FormFactory;

public class ObjectManagerView extends AbstractView implements SearchComponent.SearchAction {
	private static final Matcher ALWAYS_MATCH_MATCHER = new AlwaysMatchMatcher();

	private JPanel centerPanel;
    private JScrollPane scrollPane;
    private JTable table;
    private GlazedTableModel model;
    private JPopupMenu popup;
	private JButton newButton;

	private final AbstractActionCommandExecutor newCommand;
	private final AbstractActionCommandExecutor deleteCommand;
	private final AbstractActionCommandExecutor propertiesCommand;
    private final AbstractActionCommandExecutor importCommand;
	private final ViewObjectsCommand viewCommand;
	private final SearchObjectsCommand searchCommand;
	private final SearchComponent searchComponent;
	private FilterList objects;
	private EventSelectionModel selectionModel;

	public ObjectManagerView(ViewObjectsCommand viewCommand, NewCommand newCommand,
			PropertiesCommand propertiesCommand, DeleteCommand deleteCommand, SearchObjectsCommand searchCommand,
            ImportCommand importCommand, FormFactory formFactory, SearchComponent searchComponent) {
		this.viewCommand = viewCommand;
		this.searchCommand = searchCommand;
		this.searchComponent = searchComponent;
		this.newCommand = new NewCommandExcecutor(newCommand, formFactory);
        this.importCommand = new ImportCommandExcecutor(importCommand);
		this.propertiesCommand = new PropertiesCommandExecutor(propertiesCommand, formFactory);
		this.deleteCommand = new DeleteCommandExecutor(deleteCommand);

		searchComponent.addSearchListener(this);
	}

    protected JComponent createControl() {
        JPanel view = new JPanel(new BorderLayout());
        view.add(getCenterPanel(), BorderLayout.CENTER);
        view.add(searchComponent.getPanel(), BorderLayout.NORTH);
        return view;
    }

    private JPanel getCenterPanel() {
    	if (null == centerPanel) {
    		centerPanel = new JPanel();
    		centerPanel.add(getScrollPane(), null);
            centerPanel.add(getNewButton(), null);
    	}
    	return centerPanel;
    }

    protected void registerLocalCommandExecutors(PageComponentContext context) {
        context.register(GlobalCommandIds.PROPERTIES, getPropertiesCommand());
        context.register(GlobalCommandIds.DELETE, getDeleteCommand());
        context.register(ShardCommandIds.NEW, getNewCommand());
        context.register(ShardCommandIds.IMPORT, getImportCommand());
    }

    private AbstractActionCommandExecutor getPropertiesCommand() {
    	return propertiesCommand;
    }

    private AbstractActionCommandExecutor getDeleteCommand() {
    	return deleteCommand;
    }

    private AbstractActionCommandExecutor getNewCommand() {
    	return newCommand;
    }

    private AbstractActionCommandExecutor getImportCommand() {
        return importCommand;
    }

	private FilterList getObjects() {
		if (null == objects) {
			EventList tempObjects = new BasicEventList();
			tempObjects.addAll(viewCommand.getObjects());
			objects = new FilterList(tempObjects, ALWAYS_MATCH_MATCHER);
		}
		return objects;
	}

    private Object getSelectedObject() {
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
            model = new GlazedTableModel(getObjects(), messageSource, viewCommand.getColumnNames());
        }
        return model;
    }

	public void search(String query) {
		final Collection searchResults = searchCommand.searchObjects(query);
		getObjects().setMatcher(new Matcher() {

			public boolean matches(Object object) {
				return searchResults.contains(object);
			}

		});
	}

	public void clear() {
		getObjects().setMatcher(ALWAYS_MATCH_MATCHER);
	}


    private class NewCommandExcecutor extends AbstractActionCommandExecutor {
        private Object object;
        private NestingFormModel formModel;
        private AbstractForm form;
        private FormBackedDialogPage page;

		private final NewCommand command;
		private final FormFactory formFactory;

        public NewCommandExcecutor(NewCommand newCommand, FormFactory formFactory) {
			this.command = newCommand;
			this.formFactory = formFactory;

			this.setEnabled(true);
        }

        public void execute() {
            object = command.createObject();
            formModel = FormModelHelper.createCompoundFormModel(object);
            form = formFactory.createForm(formModel);
            page = new FormBackedDialogPage(form);

            TitledPageApplicationDialog dialog = new TitledPageApplicationDialog(page, getWindowControl()) {
                protected void onAboutToShow() {
                    setEnabled(page.isPageComplete());
                }

                protected boolean onFinish() {
                    formModel.commit();
                    command.saveObject(object);
                    getObjects().add(object);
                    return true;
                }
            };
            dialog.showDialog();
        }
    }

    private class ImportCommandExcecutor extends AbstractActionCommandExecutor {
        private final ImportCommand command;

        public ImportCommandExcecutor(ImportCommand importCommand) {
            this.command = importCommand;

            this.setEnabled(true);
        }

        public void execute() {
            System.out.println("IMPORT NOW!");
            command.importObjects();
        }
    }

    private class DeleteCommandExecutor extends AbstractActionCommandExecutor {
    	private final String title;
		private final String message;
		private final DeleteCommand command;

		public DeleteCommandExecutor(DeleteCommand command) {
			this.title = getMessage(command.getDeleteMessagePropertyName()+ ".title");
			this.message = getMessage(command.getDeleteMessagePropertyName() + ".message");
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


    private class PropertiesCommandExecutor extends AbstractActionCommandExecutor {
        private Object object;
        private AbstractForm form;
        private FormBackedDialogPage page;
        private int index;
		private final FormFactory formFactory;
		private final PropertiesCommand command;

        public PropertiesCommandExecutor(PropertiesCommand command, FormFactory formFactory) {
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

    private static class AlwaysMatchMatcher implements Matcher {

		public boolean matches(Object arg0) {
			return true;
		}

    }
}
