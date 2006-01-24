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
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.net.URI;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.TransferHandler;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.binding.form.FormModel;
import org.springframework.richclient.application.PageComponentContext;
import org.springframework.richclient.application.support.AbstractView;
import org.springframework.richclient.application.support.ApplicationWindowCommandManager;
import org.springframework.richclient.command.AbstractCommand;
import org.springframework.richclient.command.CommandGroup;
import org.springframework.richclient.command.CommandManager;
import org.springframework.richclient.command.support.AbstractActionCommandExecutor;
import org.springframework.richclient.command.support.GlobalCommandIds;
import org.springframework.richclient.dialog.ConfirmationDialog;
import org.springframework.richclient.dialog.FormBackedDialogPage;
import org.springframework.richclient.dialog.TitledPageApplicationDialog;
import org.springframework.richclient.filechooser.DefaultFileFilter;
import org.springframework.richclient.form.AbstractForm;
import org.springframework.richclient.form.FormModelHelper;
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
import com.codecrate.shard.ui.form.FormFactory;
import com.codecrate.shard.ui.table.ReadOnlyGlazedTableModel;
import com.codecrate.shard.ui.table.StretchWhenEmptyJTable;
import com.codecrate.shard.util.ComparableComparator;
import com.l2fprod.common.swing.JTaskPane;
import com.l2fprod.common.swing.JTaskPaneGroup;

import foxtrot.Job;
import foxtrot.Worker;

public class ObjectManagerView extends AbstractView {
    private final Log LOG = LogFactory.getLog(ObjectManagerView.class);

    private static final boolean SINGLE_COLUMN_SORT = false;
    private static final Matcher ALWAYS_MATCH_MATCHER = new AlwaysMatchMatcher();
    private static final int SEARCH_DELAY_MILLIS = 300;

    private JPanel quickSearchPanel;
    private JTextField quickSearchInput;
    private JButton searchButton;
    private JButton clearButton;
    private JTaskPane taskPanel;
    private JScrollPane scrollPane;
    private JTable table;
    private TableModel model;
    private JPopupMenu popup;
	private Action newAction;
    private JTaskPaneGroup commonTasks;

    private Timer timer = new Timer();
    private final ObjectManagerCommandAdapter commandAdapter;
	private final NewCommandExcecutor newCommand;
	private final DeleteCommandExecutor deleteCommand;
	private final PropertiesCommandExecutor propertiesCommand;
    private final ImportCommandExcecutor importCommand;
	private FilterList objects;
	private SortedList sortedObjects;
	private EventSelectionModel selectionModel;

	public ObjectManagerView(ObjectManagerCommandAdapter commandAdapter, FormFactory formFactory) {
		this.commandAdapter = commandAdapter;
		this.newCommand = new NewCommandExcecutor(formFactory);
        this.importCommand = new ImportCommandExcecutor();
		this.propertiesCommand = new PropertiesCommandExecutor(formFactory);
		this.deleteCommand = new DeleteCommandExecutor();
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
        context.register(ShardCommandIds.NEW, newCommand);
        context.register(ShardCommandIds.IMPORT, importCommand);
    }

    public void componentFocusLost() {
        resetQuickSearchInput();
    }

    private void resetQuickSearchInput() {
        getQuickSearchInput().setText("");
    }

    private JTaskPane getTaskPanel() {
        if (null == taskPanel) {
            taskPanel = new JTaskPane();
            taskPanel.add(getCommonTasks());
        }
        return taskPanel;
    }

    private JTaskPaneGroup getCommonTasks() {
        if (null == commonTasks) {
            commonTasks = new JTaskPaneGroup();
            commonTasks.setExpanded(true);
            commonTasks.setTitle("Common Actions");
            commonTasks.add(getNewAction());
            
            CommandManager commandManager = getApplication().getActiveWindow().getCommandManager();
            if (commandManager instanceof ApplicationWindowCommandManager) {
            	ApplicationWindowCommandManager awcommandManager = (ApplicationWindowCommandManager) commandManager;

            	Iterator it = awcommandManager.getSharedCommands();
            	while (it.hasNext()) {
            		AbstractCommand cmd = (AbstractCommand) it.next();
            		//cmd.addEnabledListener(thePropertyChangeListener);
            	}
            }
        }
        return commonTasks;
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

    private JTextField getQuickSearchInput() {
        if (quickSearchInput == null) {
            quickSearchInput = new JTextField();
            quickSearchInput.setColumns(10);
            quickSearchInput.getDocument().addDocumentListener(new SearchDocumentListener());
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
                    getQuickSearchInput().setText("");
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
                    if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1 && propertiesCommand.isEnabled()) {
                        propertiesCommand.execute();
                    }
                }
            });


            table.setTransferHandler(new FileTransferHandler());

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

    private Action getNewAction() {
    	if (null == newAction) {
            Icon icon = getIconSource().getIcon("newCommand.icon");
            String text = getMessage("newCommand.caption");
            newAction = new AbstractAction(text, icon){
                public void actionPerformed(ActionEvent e) {
                    newCommand.execute();
                }};
    	}
    	return newAction;
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
            CommandGroup group = getWindowCommandManager().createCommandGroup( getCommandGroupName(), new Object[] {
                    GlobalCommandIds.PROPERTIES,
                    GlobalCommandIds.DELETE,
                    ShardCommandIds.NEW});
            popup = group.createPopupMenu();
        }
        return popup;
    }

    private String getCommandGroupName() {
        return getDescriptor().getId() + "PopupCommandGroup";
    }

    private TableModel getModel() {
        if (null == model) {
            model = new ReadOnlyGlazedTableModel(getSortedObjects(), getMessageSource(), commandAdapter.getColumnNames());
        }
        return model;
    }

	private void fireSearch() {
		final Collection searchResults = commandAdapter.searchObjects(getQuickSearchInput().getText());
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

    private String getSearchableText() {
        return getQuickSearchInput().getText().trim();
    }

    private class NewCommandExcecutor extends AbstractActionCommandExecutor {
        private Object object;
        private FormModel formModel;
        private AbstractForm form;
        private FormBackedDialogPage page;

		private final FormFactory formFactory;

        public NewCommandExcecutor(FormFactory formFactory) {
			this.formFactory = formFactory;

			this.setEnabled(true);
        }

        public void execute() {
            object = commandAdapter.createObject();
            formModel = FormModelHelper.createFormModel(object);
            form = formFactory.createForm(formModel);
            page = new FormBackedDialogPage(form);

            TitledPageApplicationDialog dialog = new TitledPageApplicationDialog(page, getWindowControl()) {
                protected void onAboutToShow() {
                    setEnabled(page.isPageComplete());
                }

                protected boolean onFinish() {
                    formModel.commit();
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


    private class PropertiesCommandExecutor extends AbstractActionCommandExecutor {
        private Object object;
        private AbstractForm form;
        private FormBackedDialogPage page;
        private int index;
		private final FormFactory formFactory;

        public PropertiesCommandExecutor(FormFactory formFactory) {
			this.formFactory = formFactory;
        }

        public void execute() {
            object = getSelectedObject();
            index = getFilteredObjects().indexOf(object);
            form = formFactory.createForm(object);
            page = new FormBackedDialogPage(form);

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

    private static class AlwaysMatchMatcher implements Matcher {

		public boolean matches(Object arg0) {
			return true;
		}

    }

    private class SearchDocumentListener implements DocumentListener {
        public void changedUpdate(DocumentEvent event) {
            processEvent(event);
        }

        public void insertUpdate(DocumentEvent event) {
            processEvent(event);
        }

        public void removeUpdate(DocumentEvent event) {
            processEvent(event);
        }

        private void processEvent(DocumentEvent event) {
            String value = getSearchableText();
            if (0 < value.length()) {
                TimerTask delaySearchTask = new DelaySearchTask(value);
                timer.schedule(delaySearchTask, SEARCH_DELAY_MILLIS);
            } else {
                fireClear();
            }
        }
    }


    private class DelaySearchTask extends TimerTask {
        private final String originalInput;

        public DelaySearchTask(String originalInput) {
            this.originalInput = originalInput;
        }
        public void run() {
            if (!hasInputChanged()) {
                fireSearch();
            }
        }
        private boolean hasInputChanged() {
            return !originalInput.equals(getSearchableText());
        }
    }

    private class FileTransferHandler extends TransferHandler {
        private final DataFlavor fileDataFlavor;
        private final DataFlavor uriListFlavor;

        public FileTransferHandler() {
            fileDataFlavor = DataFlavor.javaFileListFlavor;
            try {
                uriListFlavor = new DataFlavor("text/uri-list;class=java.lang.String");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        public boolean canImport(JComponent c, DataFlavor[] flavors) {
            if (hasFileFlavor(flavors)) {
                return true;
            }
            if (hasUriListFlavor(flavors)) {
                return true;
            }

            LOG.info("Can not handle data flavors: " + stringifyFlavors(flavors));
            return false;
        }

        public int getSourceActions(JComponent c) {
            return COPY_OR_MOVE;
        }

        public boolean importData(JComponent c, Transferable t) {
            if (!canImport(c, t.getTransferDataFlavors())) {
                return false;
            }
            try {
                if (hasFileFlavor(t.getTransferDataFlavors())) {
                    List files = (List)t.getTransferData(fileDataFlavor);
                    for (int i = 0; i < files.size(); i++) {
                        File file = (File)files.get(i);
                        importFile(file);
                    }
                }
                if (hasUriListFlavor(t.getTransferDataFlavors())) {
                    String fileNames = (String) t.getTransferData(uriListFlavor);
                    File file = new File(new URI(fileNames.trim()));
                    importFile(file);
                }
                return true;
            } catch (Exception e) {
                LOG.error("Error handling drag/drop", e);
            }
            return false;
        }

        private boolean hasFileFlavor(DataFlavor[] flavors) {
            for (int i = 0; i < flavors.length; i++) {
                if (fileDataFlavor.equals(flavors[i])) {
                    return true;
                }
            }
            return false;
        }
        private boolean hasUriListFlavor(DataFlavor[] flavors) {
            for (int i = 0; i < flavors.length; i++) {
                if (uriListFlavor.equals(flavors[i])) {
                    return true;
                }
            }
            return false;
        }

        private String stringifyFlavors(DataFlavor[] flavors) {
            String results = "";
            for (int i = 0; i < flavors.length; i++) {
                results += "\n\t" + flavors[i];
            }
            return results;
        }

    }
}