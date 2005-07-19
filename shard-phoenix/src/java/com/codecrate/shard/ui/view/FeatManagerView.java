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
import org.springframework.richclient.form.FormModelHelper;
import org.springframework.richclient.table.TableUtils;
import org.springframework.richclient.table.support.GlazedTableModel;
import org.springframework.richclient.util.PopupMenuMouseListener;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.EventList;

import com.codecrate.shard.feat.Feat;
import com.codecrate.shard.feat.FeatDao;
import com.codecrate.shard.feat.FeatFactory;
import com.codecrate.shard.ui.ShardCommandIds;
import com.codecrate.shard.ui.form.FeatForm;
import com.codecrate.shard.util.ShardTableUtils;

public class FeatManagerView extends AbstractView {
    private JScrollPane scrollPane;
    private JTable table;
    private GlazedTableModel model;
    private JPopupMenu popup;

    private PropertiesCommandExecutor propertiesExecutor;
    private DeleteCommandExecutor deleteExecutor;
    private NewCommandExecutor newExecutor;
    
    private EventList feats;
    private FeatDao featDao;
    private FeatFactory featFactory; 
    
    public void setFeatDao(FeatDao featDao) {
        this.featDao = featDao;
    }
    
    public void setFeatFactory(FeatFactory featFactory) {
    	this.featFactory = featFactory;
    }

    protected JComponent createControl() {
        JPanel view = new JPanel();
        view.add(getScrollPane(), BorderLayout.WEST);
        return view;
    }
    
    protected void registerLocalCommandExecutors(PageComponentContext context) {
        context.register(GlobalCommandIds.PROPERTIES, getPropertiesCommand());
        context.register(GlobalCommandIds.DELETE, getDeleteCommand());
        context.register(ShardCommandIds.NEW, getNewCommand());
    }

    private PropertiesCommandExecutor getPropertiesCommand() {
        if (null == propertiesExecutor) {
            propertiesExecutor = new PropertiesCommandExecutor();
        }
        return propertiesExecutor;
    }
    
    private DeleteCommandExecutor getDeleteCommand() {
        if (null == deleteExecutor) {
            deleteExecutor = new DeleteCommandExecutor();
        }
        return deleteExecutor;
    }

    private NewCommandExecutor getNewCommand() {
        if (null == newExecutor) {
            newExecutor = new NewCommandExecutor();
            newExecutor.setEnabled(true);
        }
        return newExecutor;
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

    private boolean isDeleteCommandEnabled() {
        if (null == getSelectedFeat()) {
            return false;
        }
        return true;
    }

    private boolean isPropertiesCommandEnabled() {
        if (null == getSelectedFeat()) {
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
            String[] columns = new String[] {
                    "name"
                    , "type"
            }; 
            model = new GlazedTableModel(getFeats(), messageSource, columns);
        }
        return model;
    }
    
    private EventList getFeats() {
        if (null == feats) {
            feats = new BasicEventList();
            feats.addAll(featDao.getFeats());
        }
        return feats;
    }
    
    private Feat getSelectedFeat() {
    	int index = ShardTableUtils.getSelectedIndex(getTable());
        if (-1 == index) {
        	return null;
        }
        return (Feat) getFeats().get(index);
    }
    

    private class DeleteCommandExecutor extends AbstractActionCommandExecutor {
        public void execute() {
            ConfirmationDialog dialog = new ConfirmationDialog() {
                protected void onConfirm() {
                    Feat feat = getSelectedFeat();
                    featDao.deleteFeat(feat);
                    getFeats().remove(feat);
                }
            };
            dialog.setTitle("Delete Feat");
            dialog.setConfirmationMessage(getMessage("confirmDeleteFeatDialog.label"));
            dialog.showDialog();
        }
    }


    private class PropertiesCommandExecutor extends AbstractActionCommandExecutor {
        private Feat feat;
        private NestingFormModel featFormModel;
        private FeatForm featForm;
        private FormBackedDialogPage page;

        public void execute() {
            feat = getSelectedFeat();
            featFormModel = FormModelHelper.createCompoundFormModel(feat);
            featForm = new FeatForm(featFormModel);
            page = new FormBackedDialogPage(featForm);

            TitledPageApplicationDialog dialog = new TitledPageApplicationDialog(page, getWindowControl()) {
                protected void onAboutToShow() {
                    setEnabled(page.isPageComplete());
                }

                protected boolean onFinish() {
                    featFormModel.commit();
                    featDao.updateFeat(feat);
                    int index = getFeats().indexOf(feat);
                    getFeats().set(index, feat);
                    return true;
                }
            };
            dialog.showDialog();
        }
    }
  
    
    private class NewCommandExecutor extends AbstractActionCommandExecutor {
        private Feat feat;
        private NestingFormModel featFormModel;
        private FeatForm featForm;
        private FormBackedDialogPage page;

        public void execute() {
            feat = featFactory.createFeat("New Feat");
            featFormModel = FormModelHelper.createCompoundFormModel(feat);
            featForm = new FeatForm(featFormModel);
            page = new FormBackedDialogPage(featForm);

            TitledPageApplicationDialog dialog = new TitledPageApplicationDialog(page, getWindowControl()) {
                protected void onAboutToShow() {
                    setEnabled(page.isPageComplete());
                }

                protected boolean onFinish() {
                    featFormModel.commit();
                    featDao.saveFeat(feat);
                    getFeats().add(feat);
                    return true;
                }
            };
            dialog.showDialog();
        }
    }
}