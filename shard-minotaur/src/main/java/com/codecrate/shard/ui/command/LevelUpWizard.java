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
package com.codecrate.shard.ui.command;

import org.springframework.richclient.command.ActionCommandExecutor;
import org.springframework.richclient.form.CompoundForm;
import org.springframework.richclient.wizard.AbstractWizard;
import org.springframework.richclient.wizard.FormBackedWizardPage;
import org.springframework.richclient.wizard.WizardDialog;

import com.codecrate.shard.kit.CharacterClass;
import com.codecrate.shard.kit.CharacterClassDao;
import com.codecrate.shard.ui.form.CharacterClassForm;

/**
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class LevelUpWizard extends AbstractWizard implements ActionCommandExecutor {
    private static final String WIZARD_NAME = "levelUpWizard";

    private WizardDialog wizardDialog;
    private CompoundForm wizardForm;

	private CharacterClassDao kitDao;

	private LevelUpOptions levelUpOptions;

    public LevelUpWizard() {
        super(WIZARD_NAME);
    }

    public void addPages() {
        addPage(new FormBackedWizardPage(new CharacterClassForm(getWizardForm().getFormModel(), kitDao)));
    }

    protected boolean onFinish() {
        getWizardForm().commit();
        return true;
    }

    public void execute() {
    	this.levelUpOptions = new LevelUpOptions();
        getWizardForm().setFormObject(levelUpOptions);
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

	public void setKitDao(CharacterClassDao kitDao) {
		this.kitDao = kitDao;
	}
	
	public static class LevelUpOptions {
		private CharacterClass kit;

		public CharacterClass getKit() {
			return kit;
		}

		public void setKit(CharacterClass kit) {
			this.kit = kit;
		}
	}
}
