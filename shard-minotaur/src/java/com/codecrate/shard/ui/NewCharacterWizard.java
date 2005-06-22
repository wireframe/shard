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
package com.codecrate.shard.ui;

import org.springframework.richclient.command.ActionCommandExecutor;
import org.springframework.richclient.forms.CompoundForm;
import org.springframework.richclient.wizard.AbstractWizard;
import org.springframework.richclient.wizard.FormBackedWizardPage;
import org.springframework.richclient.wizard.WizardDialog;

import com.codecrate.shard.domain.PlayerCharacterModel;

/**
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class NewCharacterWizard extends AbstractWizard implements ActionCommandExecutor {
    private WizardDialog wizardDialog;
    private CompoundForm wizardForm;

    public NewCharacterWizard() {
        super("newCharacterWizard");
    }

    public void addPages() {
        addPage(new FormBackedWizardPage(new AbilityScoreForm(getWizardForm().getFormModel())));
        addPage(new FormBackedWizardPage(new RaceForm(getWizardForm().getFormModel())));
    }
    
    protected boolean onFinish() {
        getWizardForm().commit();
        
        return true;
    }

    public void execute() {
        getWizardForm().setFormObject(new PlayerCharacterModel());
        getWizardDialog().showDialog();
    }
    
    private CompoundForm getWizardForm() {
        if (null == wizardForm) {
            wizardForm = new CompoundForm();
        }
        return wizardForm;
    }
    
    private WizardDialog getWizardDialog() {
        if (null == wizardDialog) {
            wizardDialog = new WizardDialog(this);
        }
        return wizardDialog;
    }
}