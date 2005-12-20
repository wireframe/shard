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

import javax.swing.JComponent;

import org.springframework.binding.form.FormModel;
import org.springframework.richclient.form.AbstractForm;
import org.springframework.richclient.form.binding.swing.SwingBindingFactory;
import org.springframework.richclient.form.builder.TableFormBuilder;

public class CharacterClassFormFactory extends AbstractFormFactory implements FormFactory {

	public AbstractForm createForm(FormModel formModel) {
		return new CharacterClassForm(formModel);
	}

    public class CharacterClassForm extends AbstractForm {
        private static final String PAGE_NAME = "characterClassPage";

        public CharacterClassForm(FormModel formModel) {
            super(formModel, PAGE_NAME);
        }

        protected JComponent createFormControl() {
            SwingBindingFactory bindingFactory = (SwingBindingFactory) getBindingFactory();

            TableFormBuilder formBuilder = new TableFormBuilder(bindingFactory);
            formBuilder.add("name");
            formBuilder.row();
            formBuilder.add("abbreviation");
            formBuilder.row();
            formBuilder.add("hitDicePerLevelString");
            
            return formBuilder.getForm();
        }
    }
}
