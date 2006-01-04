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
package com.codecrate.shard.ui.form;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.Collection;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import org.springframework.binding.form.FormModel;
import org.springframework.richclient.form.AbstractForm;
import org.springframework.richclient.form.binding.swing.SwingBindingFactory;
import org.springframework.richclient.form.builder.TableFormBuilder;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.SortedList;
import ca.odell.glazedlists.swing.TableComparatorChooser;

import com.codecrate.shard.kit.CharacterClass;
import com.codecrate.shard.source.SourceDao;
import com.codecrate.shard.ui.table.ReadOnlyGlazedTableModel;
import com.codecrate.shard.util.ComparableComparator;

public class CharacterClassFormFactory extends AbstractFormFactory implements FormFactory {
    private static final boolean SINGLE_COLUMN_SORT = false;

    private final SourceDao sourceDao;

    public CharacterClassFormFactory(SourceDao sourceDao) {
        this.sourceDao = sourceDao;
    }

	public AbstractForm createForm(FormModel formModel) {
		return new CharacterClassForm(formModel);
	}

    public class CharacterClassForm extends AbstractForm {
        private static final String PAGE_NAME = "characterClassPage";

        private final CharacterClass kit;

        private SortedList levels;

        public CharacterClassForm(FormModel formModel) {
            super(formModel, PAGE_NAME);

            kit = (CharacterClass) getFormObject();
        }

        protected JComponent createFormControl() {
            SwingBindingFactory bindingFactory = (SwingBindingFactory) getBindingFactory();

            TableFormBuilder formBuilder = new TableFormBuilder(bindingFactory);
            formBuilder.add("name");
            formBuilder.row();
            formBuilder.add("abbreviation");
            formBuilder.row();
            formBuilder.add("hitDicePerLevelString");
            formBuilder.row();
            formBuilder.add(bindingFactory.createBoundComboBox("source", getSources()));

            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());
            panel.add(formBuilder.getForm(), BorderLayout.NORTH);
            panel.add(getClassProgressionPanel(), BorderLayout.SOUTH);
            return panel;
        }

        private Component getClassProgressionPanel() {
            JPanel panel = new JPanel();
            panel.add(new JScrollPane(getTable()));

            return panel;
        }

        private Component getTable() {
            JTable table = new JTable(getModel());
            new TableComparatorChooser(table, getSortedLevels(), SINGLE_COLUMN_SORT);

            return table;
        }

        private SortedList getSortedLevels() {
            if (null == levels) {
                EventList temp = new BasicEventList();
                temp.addAll(kit.getClassProgression().getClassLevels());
                levels = new SortedList(temp, new ComparableComparator());
            }
            return levels;
        }

        private TableModel getModel() {
            TableModel model = new ReadOnlyGlazedTableModel(getSortedLevels(), getMessageSource(), new String[] {
                "level",
                "baseAttackBonus",
                "fortitudeSaveBonus",
                "reflexSaveBonus"
            });
            return model;
        }

        private Collection getSources() {
            return sourceDao.getSources();
        }
    }
}
