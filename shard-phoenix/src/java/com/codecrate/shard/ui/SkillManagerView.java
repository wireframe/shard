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
package com.codecrate.shard.ui;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

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
import org.springframework.core.closure.support.Block;
import org.springframework.richclient.application.PageComponentContext;
import org.springframework.richclient.application.support.AbstractView;
import org.springframework.richclient.command.CommandGroup;
import org.springframework.richclient.command.support.AbstractActionCommandExecutor;
import org.springframework.richclient.command.support.GlobalCommandIds;
import org.springframework.richclient.dialog.ConfirmationDialog;
import org.springframework.richclient.dialog.FormBackedDialogPage;
import org.springframework.richclient.dialog.InputApplicationDialog;
import org.springframework.richclient.dialog.TitledPageApplicationDialog;
import org.springframework.richclient.forms.FormModelHelper;
import org.springframework.richclient.table.BeanTableModel;
import org.springframework.richclient.table.SortableTableModel;
import org.springframework.richclient.table.TableUtils;
import org.springframework.richclient.util.PopupMenuMouseListener;

import com.codecrate.shard.ability.Ability;
import com.codecrate.shard.ability.AbilityDao;
import com.codecrate.shard.skill.Skill;
import com.codecrate.shard.skill.SkillDao;

public class SkillManagerView extends AbstractView {
    private JScrollPane scrollPane;
    private JTable table;
    private BeanTableModel model;
    private JPopupMenu popup;

    private PropertiesCommandExecutor propertiesExecutor;
    private DeleteCommandExecutor deleteExecutor;
    private NewCommandExecutor newExecutor;
    
    private List skills;
    private SkillDao skillDao;
    private AbilityDao abilityDao;
    
    /**
     * @return Returns the abilityDao.
     */
    public AbilityDao getAbilityDao() {
        return abilityDao;
    }
    
    /**
     * @param abilityDao The abilityDao to set.
     */
    public void setAbilityDao(AbilityDao abilityDao) {
        this.abilityDao = abilityDao;
    }

    public void setSkillDao(SkillDao skillDao) {
        this.skillDao = skillDao;
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
        if (null == getSelectedSkill()) {
            return false;
        }
        return true;
    }

    private boolean isPropertiesCommandEnabled() {
        if (null == getSelectedSkill()) {
            return false;
        }
        return true;
    }


    private JPopupMenu getPopupContextMenu() {
        if (null == popup) {
            CommandGroup group = getWindowCommandManager().createCommandGroup("skillsCommandGroup", new Object[] {
                    GlobalCommandIds.PROPERTIES, 
                    GlobalCommandIds.DELETE, 
                    ShardCommandIds.NEW});
            popup = group.createPopupMenu();
        }
        return popup;
    }
    
    private BeanTableModel getModel() {
        if (null == model) {
            MessageSource messageSource = (MessageSource) getApplicationContext().getBean("messageSource");

            model = new BeanTableModel(Skill.class, getSkills(), messageSource) {

                protected String[] createColumnPropertyNames() {
                    return new String[] {
                            "name"
                            , "ability.name"
                    };
                }

                protected Class[] createColumnClasses() {
                    return new Class[] {
                      String.class
                      , String.class
                    };
                }
                
            };
            model.setRowNumbers(false);
        }
        return model;
    }
    
    private List getSkills() {
        if (null == skills) {
            skills = new ArrayList(skillDao.getSkills());
        }
        return skills;
    }
    
    private Skill getSelectedSkill() {
        int sortedIndex = getTable().getSelectedRow();
        if (-1 == sortedIndex) {
            return null;
        }
        SortableTableModel sortedModel = (SortableTableModel) getTable().getModel();
        int realIndex = sortedModel.convertSortedIndexToDataIndex(sortedIndex);
        Skill skill = (Skill) getModel().getRow(realIndex);
        return skill;
        
    }
    

    private class DeleteCommandExecutor extends AbstractActionCommandExecutor {
        public void execute() {
            ConfirmationDialog dialog = new ConfirmationDialog() {
                protected void onConfirm() {
                    Skill skill = getSelectedSkill();
                    skillDao.deleteSkill(skill);
                    getSkills().remove(skill);
                    getModel().fireTableDataChanged();
                }
            };
            dialog.setTitle("Delete Skill");
            dialog.setConfirmationMessage(getMessage("confirmDeleteSkillDialog.label"));
            dialog.showDialog();
        }
    }


    private class PropertiesCommandExecutor extends AbstractActionCommandExecutor {
        private Skill skill;
        private NestingFormModel skillFormModel;
        private SkillForm skillForm;
        private FormBackedDialogPage page;

        public void execute() {
            skill = getSelectedSkill();
            skillFormModel = FormModelHelper.createCompoundFormModel(skill);
            skillForm = new SkillForm(skillFormModel);
            page = new FormBackedDialogPage(skillForm);

            TitledPageApplicationDialog dialog = new TitledPageApplicationDialog(page, getWindowControl()) {
                protected void onAboutToShow() {
                    setEnabled(page.isPageComplete());
                }

                protected boolean onFinish() {
                    skillFormModel.commit();
                    skillDao.updateSkill(skill);
                    int index = getSkills().indexOf(skill);
                    getSkills().set(index, skill);
                    getModel().fireTableDataChanged();
                    return true;
                }
            };
            dialog.showDialog();
        }
    }
  
    
    private class NewCommandExecutor extends AbstractActionCommandExecutor {
        private NestingFormModel skillFormModel;
        private SkillForm skillForm;
        private FormBackedDialogPage page;
        private Skill skill;
        
        public void execute() {
            final SkillNameBean skillName = new SkillNameBean();
            InputApplicationDialog skillNameDialog = new InputApplicationDialog(skillName, "name");
            skillNameDialog.setTitle(getMessage("skillNameDialog.title"));
            skillNameDialog.setInputLabelMessage("skillNameDialog.label");
            skillNameDialog.setParent(getWindowControl());
            skillNameDialog.setFinishAction(new Block() {

                public void handle(Object o) {
                    Ability ability = (Ability) abilityDao.getAbilities().iterator().next();
                    skill = skillDao.createSkill(skillName.getName(), false, ability, false);
                    skillFormModel = FormModelHelper.createCompoundFormModel(skill);
                    skillForm = new SkillForm(skillFormModel);
                    page = new FormBackedDialogPage(skillForm);

                    TitledPageApplicationDialog dialog = new TitledPageApplicationDialog(page, getWindowControl()) {
                        protected void onAboutToShow() {
                            setEnabled(page.isPageComplete());
                        }

                        protected void onCancel() {
                            super.onCancel();
                            skillDao.deleteSkill(skill);
                        }
                        
                        protected boolean onFinish() {
                            skillFormModel.commit();
                            skillDao.updateSkill(skill);
                            getSkills().add(skill);
                            getModel().fireTableDataChanged();
                            return true;
                        }
                    };
                    dialog.showDialog();
                }
            });
            skillNameDialog.showDialog();
        }
    }
    

    private class SkillNameBean {
        private String name;
        /**
         * @return Returns the name.
         */
        public String getName() {
            return name;
        }
        /**
         * @param name The name to set.
         */
        public void setName(String name) {
            this.name = name;
        }
    }
}