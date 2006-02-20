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
import java.io.File;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

import org.springframework.richclient.application.PageComponentContext;
import org.springframework.richclient.application.support.AbstractView;
import org.springframework.richclient.command.CommandGroup;
import org.springframework.richclient.command.support.AbstractActionCommandExecutor;
import org.springframework.richclient.command.support.GlobalCommandIds;
import org.springframework.richclient.dialog.AbstractDialogPage;
import org.springframework.richclient.dialog.ConfirmationDialog;
import org.springframework.richclient.dialog.TitledPageApplicationDialog;
import org.springframework.richclient.filechooser.DefaultFileFilter;
import org.springframework.richclient.form.AbstractForm;
import org.springframework.richclient.progress.BusyIndicator;
import org.springframework.richclient.progress.ProgressMonitor;
import org.springframework.richclient.progress.StatusBar;
import org.springframework.richclient.util.PopupMenuMouseListener;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.FilterList;
import ca.odell.glazedlists.Matcher;
import ca.odell.glazedlists.SortedList;
import ca.odell.glazedlists.swing.EventSelectionModel;
import ca.odell.glazedlists.swing.TableComparatorChooser;

import com.codecrate.shard.ui.ShardCommandIds;
import com.codecrate.shard.ui.command.ObjectManagerCommandAdapter;
import com.codecrate.shard.ui.component.SearchOnPauseJTextField;
import com.codecrate.shard.ui.dragdrop.FileTransferHandler;
import com.codecrate.shard.ui.form.FormFactory;
import com.codecrate.shard.ui.table.AlwaysMatchMatcher;
import com.codecrate.shard.ui.table.ReadOnlyGlazedTableModel;
import com.codecrate.shard.ui.table.StretchWhenEmptyJTable;
import com.codecrate.shard.util.ComparableComparator;
import com.codecrate.shard.util.MouseUtil;
import com.l2fprod.common.springrcp.JTaskPaneCommandGroup;
import com.l2fprod.common.swing.JTaskPane;

import foxtrot.Job;
import foxtrot.Worker;

public class ObjectManagerView extends AbstractView {
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
	private final NewCommandExcecutor newCommand;
	private final DeleteCommandExecutor deleteCommand;
	private final PropertiesCommandExecutor propertiesCommand;
    private final ImportCommandExcecutor importCommand;
    private final SelectAllCommandExcecutor selectAllCommand;
	private FilterList objects;
	private SortedList sortedObjects;
	private EventSelectionModel selectionModel;

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
        context.register(ShardCommandIds.NEW, newCommand);
        context.register(ShardCommandIds.IMPORT, importCommand);
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
                Job loadTask = new Job() {

                    public Object run() {
                        getFilteredObjects().clear();
                        getFilteredObjects().addAll(commandAdapter.getObjects());
                        return null;
                    }

                };
                executeBlockingJobInBackground("Loading...", loadTask);
            }

        });
    }

    private void importFile(final File selectedFile) {
        Job importTask = new Job() {
            public Object run() {
                commandAdapter.importObjects(selectedFile);
                return null;
            }
        };
        executeBlockingJobInBackground("Importing...", importTask);

        loadObjects();
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


            table.setTransferHandler(new FileTransferHandler() {
				protected void onFileDrop(File file) {
					importFile(file);
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
    	    selectionModel.setSelectionMode(EventSelectionModel.MULTIPLE_INTERVAL_SELECTION_DEFENSIVE);
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
            model = new ReadOnlyGlazedTableModel(getSortedObjects(), getMessageSource(), commandAdapter.getColumnNames());
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

    private Object executeBlockingJobInBackground(String description, Job job) {
        ProgressMonitor progressMonitor = getStatusBar().getProgressMonitor();
        BusyIndicator.showAt(getWindowControl());
        progressMonitor.taskStarted(description, StatusBar.UNKNOWN);

        Object result = Worker.post(job);

        BusyIndicator.clearAt(getWindowControl());
        progressMonitor.done();

        return result;
    }

    private class NewCommandExcecutor extends AbstractActionCommandExecutor {
        private Object object;
        private AbstractForm form;
        private AbstractDialogPage page;

        public NewCommandExcecutor() {
			this.setEnabled(true);
        }

        public void execute() {
            object = commandAdapter.createObject();
            form = formFactory.createInitialForm(object);
            page = formFactory.createPage(form);

            TitledPageApplicationDialog dialog = new TitledPageApplicationDialog(page, getWindowControl()) {
                protected void onAboutToShow() {
                    setEnabled(page.isPageComplete());
                }

                protected boolean onFinish() {
                	form.getFormModel().commit();
                    commandAdapter.saveObject(object);
                    getFilteredObjects().add(object);
                    return true;
                }
            };
            dialog.showDialog();
        }
    }

    private class ImportCommandExcecutor extends AbstractActionCommandExecutor {
        private JFileChooser fileChooser = new JFileChooser();

        public ImportCommandExcecutor() {
            this.setEnabled(isImportEnabled());

            DefaultFileFilter filter = new DefaultFileFilter();
            Iterator extensions = commandAdapter.getSupportedImportFileExtensions().iterator();
            while (extensions.hasNext()) {
                String extension = (String) extensions.next();
                filter.addExtension(extension);
            }
            fileChooser.setFileFilter(filter);

            fileChooser.setFileSelectionMode(getFileSelectionMode());
        }

        private int getFileSelectionMode() {
            if (isFileImportSupported() && commandAdapter.isDirectoryImportSupported()) {
                return JFileChooser.FILES_AND_DIRECTORIES;
            }
            if (!isFileImportSupported() && commandAdapter.isDirectoryImportSupported()) {
                return JFileChooser.DIRECTORIES_ONLY;
            }
            return JFileChooser.FILES_ONLY;
        }

        private boolean isImportEnabled() {
            return (isFileImportSupported() ||
                    commandAdapter.isDirectoryImportSupported());
        }

        private boolean isFileImportSupported() {
            return !commandAdapter.getSupportedImportFileExtensions().isEmpty();
        }

        public void execute() {
            if (JFileChooser.APPROVE_OPTION == fileChooser.showOpenDialog(getWindowControl())) {
                final File selectedFile = fileChooser.getSelectedFile();
                importFile(selectedFile);
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
                    Job deleteTask = new Job() {

                        public Object run() {
                            for (Iterator selectedObjects = getSelectedObjects().iterator(); selectedObjects.hasNext();) {
                                Object object = (Object) selectedObjects.next();
                                commandAdapter.deleteObject(object);
                            }
                            return null;
                        }
                    };
                    executeBlockingJobInBackground("Deleting...", deleteTask);
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
        private Object object;
        private AbstractForm form;
        private AbstractDialogPage page;
        private int index;

        public void execute() {
            object = getSelectedObject();
            index = getFilteredObjects().indexOf(object);
            form = formFactory.createInitialForm(object);
            page = formFactory.createPage(form);

            TitledPageApplicationDialog dialog = new TitledPageApplicationDialog(page, getWindowControl()) {
                protected void onAboutToShow() {
                    setEnabled(page.isPageComplete());
                }

                protected boolean onFinish() {
                	form.getFormModel().commit();
                    commandAdapter.updateObject(object);
                    getFilteredObjects().set(index, object);
                    return true;
                }
            };
            dialog.showDialog();
        }
    }
}