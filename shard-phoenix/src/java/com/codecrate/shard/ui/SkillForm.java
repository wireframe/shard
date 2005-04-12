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

import javax.swing.JComponent;
import javax.swing.JTextField;

import org.springframework.binding.form.FormModel;
import org.springframework.richclient.form.builder.TableFormBuilder;
import org.springframework.richclient.forms.AbstractForm;

import com.codecrate.shard.ability.Ability;

public class SkillForm extends AbstractForm {
    private static final String SKILL_PAGE = "skillPage";

    private JTextField nameField;
    
    public SkillForm(FormModel formModel) {
        super(formModel, SKILL_PAGE);
    }

    protected JComponent createFormControl() {
        TableFormBuilder formBuilder = new TableFormBuilder(getFormModel());
        this.nameField = (JTextField) formBuilder.add("name")[1];
        formBuilder.row();
        formBuilder.add("armorCheckPenalty");
        formBuilder.row();
        formBuilder.add("usableUntrained");
        //formBuilder.add("ability");
        return formBuilder.getForm();
    }
    
    public String getName() {
        return nameField.getText();
    }

    /**
     * @return
     */
    public boolean isUsableUntrained() {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * @return
     */
    public Ability getAbility() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @return
     */
    public boolean isArmorCheckPenalty() {
        // TODO Auto-generated method stub
        return false;
    }
}