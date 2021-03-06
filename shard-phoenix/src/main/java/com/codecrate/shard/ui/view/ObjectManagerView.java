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
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.richclient.application.PageComponentContext;
import org.springframework.richclient.application.support.AbstractView;
import org.springframework.richclient.command.CommandGroup;
import org.springframework.richclient.command.support.AbstractActionCommandExecutor;
import org.springframework.richclient.command.support.GlobalCommandIds;
import org.springframework.richclient.dialog.AbstractDialogPage;
import org.springframework.richclient.dialog.ConfirmationDialog;
import org.springframework.richclient.form.AbstractForm;
import org.springframework.richclient.util.PopupMenuMouseListener;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.FilterList;
import ca.odell.glazedlists.SortedList;
import ca.odell.glazedlists.matchers.Matcher;
import ca.odell.glazedlists.swing.EventSelectionModel;
import ca.odell.glazedlists.swing.TableComparatorChooser;

import com.codecrate.shard.ui.ShardPhoenixCommandIds;
import com.codecrate.shard.ui.command.ImportDatasetEvent;
import com.codecrate.shard.ui.command.ObjectManagerCommandAdapter;
import com.codecrate.shard.ui.component.SearchOnPauseJTextField;
import com.codecrate.shard.ui.form.FormFactory;
import com.codecrate.shard.ui.form.FormModelCommittingTitledPageApplicationDialog;
import com.codecrate.shard.ui.table.AlwaysMatchMatcher;
import com.codecrate.shard.ui.table.ReadOnlyEventTableModel;
import com.codecrate.shard.ui.table.StretchWhenEmptyJTable;
import com.codecrate.shard.ui.util.MouseUtil;
import com.codecrate.shard.util.ComparableComparator;
import com.l2fprod.common.springrcp.JTaskPaneCommandGroup;
import com.l2fprod.common.swing.JTaskPane;

public class ObjectManagerView extends AbstractView implements ApplicationEventPublisherAware {
    private static final boolean SINGLE_COLUMN_SORT = false;
    private static final Matcher ALWAYS_MATCH_MATCHER = new AlwaysMatchMatcher();

    private JPanel quickSearchPanel;
    private SearchOnPauseJTextField quickSearchInput;
    private JButton searchButton;
    private JButton clearButton;
    private JTaskPane taskPanel;
    private JScrollPane scrollPane;
    private JTable table;
    private TableModel model;
    private JPopupMenu popup;

    private final ObjectManagerCommandAdapter commandAdapter;
	private final FormFactory formFactory;
	private final ImportCommandExcecutor importCommand;
	private final NewCommandExcecutor newCommand;
	private final DeleteCommandExecutor deleteCommand;
	private final PropertiesCommandExecutor propertiesCommand;
    private final SelectAllCommandExcecutor selectAllCommand;
	private FilterList objects;
	private SortedList sortedObjects;
	private EventSelectionModel selectionModel;
	private ApplicationEventPublisher publisher;

	public ObjectManagerView(ObjectManagerCommandAdapter commandAdapter, FormFactory formFactory) {
		this.commandAdapter = commandAdapter;
		this.formFactory = formFactory;
		this.newCommand = new NewCommandExcecutor();
		this.importCommand = new ImportCommandExcecutor();
		this.propertiesCommand = new PropertiesCommandExecutor();
		this.deleteCommand = new DeleteCommandExecutor();
		this.selectAllCommand = new SelectAllCommandExcecutor();
	}

    protected JComponent createControl() {
        JPanel view = new JPanel(new BorderLayout());
        view.add(getScrollPane(), BorderLayout.CENTER);
        view.add(getQuickSearchPanel(), BorderLayout.NORTH);
        view.add(getTaskPanel(), BorderLayout.WEST);

        return view;
    }

    protected void registerLocalCommandExecutors(PageComponentContext context) {
        context.register(GlobalCommandIds.PROPERTIES, propertiesCommand);
        context.register(GlobalCommandIds.DELETE, deleteCommand);
        context.register(GlobalCommandIds.SELECT_ALL, selectAllCommand);
        context.register(ShardPhoenixCommandIds.NEW, newCommand);
        context.register(ShardPhoenixCommandIds.IMPORT, importCommand);
    }

    public void componentFocusLost() {
        clearSearchableText();
    }

    private void clearSearchableText() {
		getQuickSearchInput().setText("");
    }

    private JTaskPane getTaskPanel() {
        if (null == taskPanel) {
        	JTaskPaneCommandGroup tasks = (JTaskPaneCommandGroup) getWindowCommandManager().getCommandGroup("contextTasks");
        	taskPanel = tasks.createTaskPane();
        }
        return taskPanel;
    }

    private JPanel getQuickSearchPanel() {
        if (null == quickSearchPanel) {
            quickSearchPanel = new JPanel();
            quickSearchPanel.setSize(new Dimension(300, 40));
            quickSearchPanel.add(getQuickSearchInput(), null);
            quickSearchPanel.add(getSearchButton(), null);
            quickSearchPanel.add(getClearButton(), null);
        }
        return quickSearchPanel;
    }

    private SearchOnPauseJTextField getQuickSearchInput() {
        if (quickSearchInput == null) {
            quickSearchInput = new SearchOnPauseJTextField() {

				protected void onClear() {
					fireClear();
				}

				protected void onSearch(String input) {
					fireSearch();
				}

            };
            quickSearchInput.setColumns(10);
        }
        return quickSearchInput;
    }

    private JButton getSearchButton() {
        if (searchButton == null) {
            searchButton = new JButton();
            searchButton.setText("Search");
            searchButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    fireSearch();
                }
            });
        }
        return searchButton;
    }

    private JButton getClearButton() {
        if (clearButton == null) {
            clearButton = new JButton();
            clearButton.setText("Clear");
            clearButton.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent arg0) {
                    clearSearchableText();
                }

            });
        }
        return clearButton;
    }

	private FilterList getFilteredObjects() {
		if (null == objects) {
			objects = new FilterList(new BasicEventList(), ALWAYS_MATCH_MATCHER);
            loadObjects();
		}
		return objects;
	}

    private void loadObjects() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	getFilteredObjects().clear();
            	getFilteredObjects().addAll(commandAdapter.getObjects());
            }
        });
    }

    private Object getSelectedObject() {
    	Collection selectedObjects = getSelectedObjects();
		if (selectedObjects.isEmpty() || selectedObjects.size() != 1) {
    		return null;
    	}
    	return selectedObjects.iterator().next();
    }

    private Collection getSelectedObjects() {
        return getSelectionModel().getSelected();
    }

    private JScrollPane getScrollPane() {
        if (null == scrollPane) {
            scrollPane = new JScrollPane(getTable());
        }
        return scrollPane;
    }

    private JTable getTable() {
        if (null == table) {
        	table = new StretchWhenEmptyJTable(getModel());
            table.setSelectionModel(getSelectionModel());
            table.addMouseListener(new PopupMenuMouseListener(getPopupContextMenu()));
            table.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    if (MouseUtil.isDoubleClick(e) && propertiesCommand.isEnabled()) {
                        propertiesCommand.execute();
                    }
                }
            });


            new TableComparatorChooser(table, getSortedObjects(), SINGLE_COLUMN_SORT);
        }
        return table;
    }

    private SortedList getSortedObjects() {
    	if (null == sortedObjects) {
    		sortedObjects = new SortedList(getFilteredObjects(), new ComparableComparator());
    	}
    	return sortedObjects;
    }

    private EventSelectionModel getSelectionModel() {
    	if (null == selectionModel) {
    		selectionModel = new EventSelectionModel(getSortedObjects());
    	    selectionModel.setSelectionMode(EventSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    	    selectionModel.addListSelectionListener(new ListSelectionListener() {

                public void valueChanged(ListSelectionEvent event) {
                    deleteCommand.setEnabled(isDeleteCommandEnabled());
                    propertiesCommand.setEnabled(isPropertiesCommandEnabled());
                }
            });
    	}
    	return selectionModel;
    }

    private boolean isDeleteCommandEnabled() {
        return !getSelectedObjects().isEmpty();
    }

    private boolean isPropertiesCommandEnabled() {
        if (null == getSelectedObject()) {
            return false;
        }
        return true;
    }


    private JPopupMenu getPopupContextMenu() {
        if (null == popup) {
            CommandGroup group = getWindowCommandManager().getCommandGroup("popUp");
            popup = group.createPopupMenu();
        }
        return popup;
    }

    private TableModel getModel() {
        if (null == model) {
            model = new ReadOnlyEventTableModel(getSortedObjects(), getMessageSource(), commandAdapter.getPropertyNames());
        }
        return model;
    }

    private void fireSearch() {
		final Collection searchResults = commandAdapter.searchObjects(getQuickSearchInput().getSearchableText());
		getFilteredObjects().setMatcher(new Matcher() {

			public boolean matches(Object object) {
				return searchResults.contains(object);
			}
		});
	}

	private void fireClear() {
		getFilteredObjects().setMatcher(ALWAYS_MATCH_MATCHER);
	}

    private class NewCommandExcecutor extends AbstractActionCommandExecutor {
        public NewCommandExcecutor() {
			this.setEnabled(true);
        }

        public void execute() {
            final Object object = commandAdapter.createObject();
            AbstractForm form = formFactory.createInitialForm(object);
            AbstractDialogPage page = formFactory.createPage(form);

            FormModelCommittingTitledPageApplicationDialog dialog = new FormModelCommittingTitledPageApplicationDialog(page, getWindowControl(), form.getFormModel()) {
                protected boolean doOnFinish() {
                    commandAdapter.saveObject(object);
                    getFilteredObjects().add(object);
                    return true;
                }
            };
            dialog.showDialog();
        }
    }

    private class ImportCommandExcecutor extends AbstractActionCommandExecutor {

		public ImportCommandExcecutor() {
			this.setEnabled(true);
        }

        public void execute() {
        	JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            chooser.setAcceptAllFileFilterUsed(false);
            chooser.setDialogTitle("Select the location of the PCGen datasets to import.");
        	int returnVal = chooser.showOpenDialog(ObjectManagerView.this.getControl());
        	if (returnVal == JFileChooser.APPROVE_OPTION) {
                ImportDatasetEvent event = new ImportDatasetEvent(this);
                event.setSelectedDirectory(chooser.getSelectedFile());

                publisher.publishEvent(event);
                JOptionPane.showMessageDialog(ObjectManagerView.this.getControl(), "Please wait while PCGen datasets are imported.  Once complete, please restart the application to view data.", "Import in progress", JOptionPane.INFORMATION_MESSAGE);
        	}
        }
    }

    private class DeleteCommandExecutor extends AbstractActionCommandExecutor {
    	private final String title;
		private final String message;

		public DeleteCommandExecutor() {
			this.title = getMessage(commandAdapter.getDeleteMessagePropertyName()+ ".title");
			this.message = getMessage(commandAdapter.getDeleteMessagePropertyName() + ".message");
    	}

        public void execute() {
        	ConfirmationDialog dialog = new ConfirmationDialog(title, getWindowControl(), message) {
                protected void onConfirm() {
                    for (Iterator selectedObjects = getSelectedObjects().iterator(); selectedObjects.hasNext();) {
                        Object object = (Object) selectedObjects.next();
                        commandAdapter.deleteObject(object);
                    }
                    getFilteredObjects().removeAll(getSelectedObjects());
                }
            };
            dialog.showDialog();
        }
    }

    private class SelectAllCommandExcecutor extends AbstractActionCommandExecutor {
		public SelectAllCommandExcecutor() {
			setEnabled(true);
    	}

        public void execute() {
        	getTable().selectAll();
        }
    }


    private class PropertiesCommandExecutor extends AbstractActionCommandExecutor {
        public void execute() {
            final Object object = getSelectedObject();
            final int index = getFilteredObjects().indexOf(object);
            AbstractForm form = formFactory.createInitialForm(object);
            AbstractDialogPage page = formFactory.createPage(form);

            FormModelCommittingTitledPageApplicationDialog dialog = new FormModelCommittingTitledPageApplicationDialog(page, getWindowControl(), form.getFormModel()) {
                protected boolean doOnFinish() {
                    commandAdapter.updateObject(object);
                    getFilteredObjects().set(index, object);
                    return true;
                }
            };
            dialog.showDialog();
        }
    }


	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
		this.publisher = publisher;
	}
}