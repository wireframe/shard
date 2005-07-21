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
import org.springframework.richclient.dialog.FormBackedDialogPage;
import org.springframework.richclient.dialog.TitledPageApplicationDialog;
import org.springframework.richclient.form.AbstractForm;
import org.springframework.richclient.form.FormModelHelper;
import org.springframework.richclient.table.TableUtils;
import org.springframework.richclient.table.support.GlazedTableModel;
import org.springframework.richclient.util.PopupMenuMouseListener;

import ca.odell.glazedlists.EventList;

import com.codecrate.shard.ui.ShardCommandIds;
import com.codecrate.shard.util.ShardTableUtils;

public abstract class AbstractObjectManagerView extends AbstractView {
    private JScrollPane scrollPane;
    private JTable table;
    private GlazedTableModel model;
    private JPopupMenu popup;
	private JButton newButton;

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

    protected abstract AbstractActionCommandExecutor getPropertiesCommand();
    
    protected abstract AbstractActionCommandExecutor getDeleteCommand();

    protected abstract AbstractActionCommandExecutor getNewCommand();

    protected abstract String[] getColumnNames();
    
    protected abstract EventList getObjects();
    
    protected Object getSelectedObject() {
    	int index = ShardTableUtils.getSelectedIndex(getTable());
        if (-1 == index) {
        	return null;
        }
        return getObjects().get(index);
    }
    
    private JScrollPane getScrollPane() {
        if (null == scrollPane) {
            scrollPane = new JScrollPane(getTable());
        }
        return scrollPane;
    }
    
    private JTable getTable() {
        if (null == table) {
            table = TableUtils.createStandardSortableTable(getModel());
            table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            table.addMouseListener(new PopupMenuMouseListener(getPopupContextMenu()));
            table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

                public void valueChanged(ListSelectionEvent event) {
                    getDeleteCommand().setEnabled(isDeleteCommandEnabled());
                    getPropertiesCommand().setEnabled(isPropertiesCommandEnabled());
                }

            });
        }
        return table;
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
        
        protected abstract AbstractForm createForm(NestingFormModel model);

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
}