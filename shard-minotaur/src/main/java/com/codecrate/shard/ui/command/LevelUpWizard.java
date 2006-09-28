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

import com.codecrate.shard.character.DefaultCharacterLevel;
import com.codecrate.shard.character.PlayerCharacter;
import com.codecrate.shard.kit.CharacterClassDao;
import com.codecrate.shard.skill.SkillDao;
import com.codecrate.shard.ui.form.CharacterClassForm;
import com.codecrate.shard.ui.form.HitPointForm;
import com.codecrate.shard.ui.form.SkillSelectionForm;
import com.codecrate.shard.ui.view.CharacterManagerView;

/**
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class LevelUpWizard extends AbstractWizard implements ActionCommandExecutor {
    private static final String WIZARD_NAME = "levelUpWizard";

    private WizardDialog wizardDialog;
    private CompoundForm wizardForm;

	private CharacterClassDao kitDao;
	private SkillDao skillDao;

	private DefaultCharacterLevel characterLevel;
	private PlayerCharacter character;

    public LevelUpWizard() {
        super(WIZARD_NAME);
    }

    public void addPages() {
        addPage(new FormBackedWizardPage(new CharacterClassForm(getWizardForm().getFormModel(), kitDao)));
        addPage(new FormBackedWizardPage(new HitPointForm(getWizardForm().getFormModel())));
        addPage(new FormBackedWizardPage(new SkillSelectionForm(getWizardForm().getFormModel(), skillDao)));
    }

	protected boolean onFinish() {
        getWizardForm().commit();
        character.getCharacterProgression().addLevel(characterLevel);
        return true;
    }

    public void execute() {
    	this.character = CharacterManagerView.getSelectedCharacter();
    	this.characterLevel = new DefaultCharacterLevel(character, null, 1);
        getWizardForm().setFormObject(characterLevel);
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
    public void setSkillDao(SkillDao skillDao) {
		this.skillDao = skillDao;
	}
}
