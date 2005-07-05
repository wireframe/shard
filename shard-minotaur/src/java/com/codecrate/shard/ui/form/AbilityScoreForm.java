/*
 * Copyright 2004 codecrate consulting
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.codecrate.shard.ui.form;

import javax.swing.JComponent;

import org.springframework.binding.form.FormModel;
import org.springframework.richclient.form.builder.TableFormBuilder;
import org.springframework.richclient.forms.AbstractForm;

/**
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class AbilityScoreForm extends AbstractForm {

    public static final String PAGE_NAME = "abilityScorePage";

    public AbilityScoreForm(FormModel formModel) {
        super(formModel, PAGE_NAME);
    }

    protected JComponent createFormControl() {
        TableFormBuilder formBuilder = new TableFormBuilder(getBindingFactory());
        formBuilder.add("abilities.strength.value");
        formBuilder.row();
        formBuilder.add("abilities.dexterity.value");
        formBuilder.row();
        formBuilder.add("abilities.constitution.value");
        formBuilder.row();
        formBuilder.add("abilities.wisdom.value");
        formBuilder.row();
        formBuilder.add("abilities.intelligence.value");
        formBuilder.row();
        formBuilder.add("abilities.charisma.value");
        formBuilder.row();
        return formBuilder.getForm();
    }
}
