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

import java.util.Collection;

import javax.swing.JComponent;

import org.springframework.binding.form.FormModel;
import org.springframework.richclient.form.AbstractForm;
import org.springframework.richclient.form.binding.swing.SwingBindingFactory;
import org.springframework.richclient.form.builder.TableFormBuilder;

import com.codecrate.shard.source.SourceDao;

public class SpellFormFactory extends AbstractFormFactory implements FormFactory {

    private final SourceDao sourceDao;

    public SpellFormFactory(SourceDao sourceDao) {
        this.sourceDao = sourceDao;
    }
    
	public AbstractForm createInitialFormWithModel(FormModel formModel) {
		return new SpellForm(formModel);
	}

    public class SpellForm extends AbstractForm {
        private static final String PAGE_NAME = "spellPage";

        public SpellForm(FormModel formModel) {
            super(formModel, PAGE_NAME);
        }

        protected JComponent createFormControl() {
            SwingBindingFactory bindingFactory = (SwingBindingFactory) getBindingFactory();
            TableFormBuilder formBuilder = new TableFormBuilder(bindingFactory);
            formBuilder.add("name");
            formBuilder.row();
            formBuilder.addInScrollPane(bindingFactory.createBoundTextArea("summary", 5, 0));
            formBuilder.row();
            formBuilder.add("school");
            formBuilder.row();
            formBuilder.add("arcane");
            formBuilder.row();
            formBuilder.add("divine");
            formBuilder.row();
            formBuilder.add(bindingFactory.createBoundComboBox("source", getSources()));
            return formBuilder.getForm();
        }
        
        private Collection getSources() {
            return sourceDao.getSources();
        }
    }
}
