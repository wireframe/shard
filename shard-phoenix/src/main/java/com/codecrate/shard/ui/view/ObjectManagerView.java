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
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.binding.form.FormModel;
import org.springframework.richclient.application.PageComponentContext;
import org.springframework.richclient.application.support.AbstractView;
import org.springframework.richclient.command.CommandGroup;
import org.springframework.richclient.command.support.AbstractActionCommandExecutor;
import org.springframework.richclient.command.support.GlobalCommandIds;
import org.springframework.richclient.dialog.ConfirmationDialog;
import org.springframework.richclient.dialog.FormBackedDialogPage;
import org.springframework.richclient.dialog.TitledPageApplicationDialog;
import org.springframework.richclient.filechooser.DefaultFileFilter;
import org.springframework.richclient.form.AbstractForm;
import org.springframework.richclient.form.FormModelHelper;
import org.springframework.richclient.util.PopupMenuMouseListener;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.FilterList;
import ca.odell.glazedlists.Matcher;
import ca.odell.glazedlists.SortedList;
import ca.odell.glazedlists.swing.EventSelectionModel;
import ca.odell.glazedlists.swing.TableComparatorChooser;

import com.codecrate.shard.ui.ShardCommandIds;
import com.codecrate.shard.ui.command.ObjectManagerCommandAdapter;
import com.codecrate.shard.ui.form.FormFactory;
import com.codecrate.shard.ui.table.ReadOnlyGlazedTableModel;
import com.codecrate.shard.util.ComparableComparator;

public class ObjectManagerView extends AbstractView {
    private static final boolean SINGLE_COLUMN_SORT = false;
	private static final Log LOG = LogFactory.getLog(ObjectManagerView.class);
    private static final Matcher ALWAYS_MATCH_MATCHER = new AlwaysMatchMatcher();
    private static final int SEARCH_DELAY_MILLIS = 300;

    private JPanel quickSearchPanel;
    private JTextField quickSearchInput;
    private JButton searchButton;
    private JButton clearButton;
	private JPanel centerPanel;
    private JScrollPane scrollPane;
    private JTable table;
    private TableModel model;
    private JPopupMenu popup;
	private JButton newButton;

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
        view.add(getCenterPanel(), BorderLayout.CENTER);
        view.add(getQuickSearchPanel(), BorderLayout.NORTH);
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

    private JPanel getCenterPanel() {
    	if (null == centerPanel) {
    		centerPanel = new JPanel();
    		centerPanel.add(getScrollPane(), null);
            centerPanel.add(getNewButton(), null);
    	}
    	return centerPanel;
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
			EventList tempObjects = new BasicEventList();
			tempObjects.addAll(commandAdapter.getObjects());
			objects = new FilterList(tempObjects, ALWAYS_MATCH_MATCHER);
		}
		return objects;
	}

    private Object getSelectedObject() {
    	EventList selected = getSelectionModel().getSelected();
    	if (selected.isEmpty()) {
    		return null;
    	}
        if (1 != selected.size()) {
            LOG.info("Multiple objects are selected.");
            return null;
        }
    	return selected.get(0);
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
        	table = new JTable(getModel());
            table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            table.setSelectionModel(getSelectionModel());
            table.addMouseListener(new PopupMenuMouseListener(getPopupContextMenu()));
            table.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1 && propertiesCommand.isEnabled()) {
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
    		sortedObjects = new SortedList(getFilteredObjects(), new ComparableComparator());    	}
    	return sortedObjects;
    }

    private EventSelectionModel getSelectionModel() {
    	if (null == selectionModel) {
    		selectionModel = new EventSelectionModel(getFilteredObjects());
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

    private JButton getNewButton() {
    	if (null == newButton) {
    		newButton = new JButton("New...");
    		newButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					newCommand.execute();
				}
    		});
    	}
    	return newButton;
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
        return getDescriptor().getId() + "CommandGroup";
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
        }

        private boolean isImportEnabled() {
            return !commandAdapter.getSupportedImportFileExtensions().isEmpty();
        }

        public void execute() {
            if (JFileChooser.APPROVE_OPTION == fileChooser.showOpenDialog(getWindowControl())) {
                File selectedFile = fileChooser.getSelectedFile();

                Collection results = commandAdapter.importObjects(selectedFile);
                getFilteredObjects().addAll(results);
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
            Document document = event.getDocument();
            try {
                String value = document.getText(0, document.getLength()).trim();
                if (0 < value.length()) {
                    TimerTask delaySearchTask = new DelaySearchTask(value);
                    timer.schedule(delaySearchTask, SEARCH_DELAY_MILLIS);
                } else {
                    fireClear();
                }
            } catch (BadLocationException e) {
                LOG.warn("Error getting search query", e);
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
            return !originalInput.equals(getQuickSearchInput().getText());
        }
    }
}
