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

import com.codecrate.shard.character.AlignmentDao;
import com.codecrate.shard.character.CharacterDao;
import com.codecrate.shard.character.CharacterFactory;
import com.codecrate.shard.character.PlayerCharacter;
import com.codecrate.shard.race.RaceDao;
import com.codecrate.shard.ui.form.AbilityScoreForm;
import com.codecrate.shard.ui.form.BioForm;
import com.codecrate.shard.ui.form.RaceForm;

/**
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class NewCharacterWizard extends AbstractWizard implements ActionCommandExecutor {
    private static final String WIZARD_NAME = "newCharacterWizard";

    private WizardDialog wizardDialog;
    private CompoundForm wizardForm;
	private CharacterFactory characterFactory;
	private CharacterDao characterDao;

    private RaceDao raceDao;
    private AlignmentDao alignmentDao;

	private PlayerCharacter character;

    public NewCharacterWizard() {
        super(WIZARD_NAME);
    }

    public void addPages() {
        addPage(new FormBackedWizardPage(new AbilityScoreForm(getWizardForm().getFormModel())));
        addPage(new FormBackedWizardPage(new RaceForm(getWizardForm().getFormModel(), raceDao, alignmentDao)));
        addPage(new FormBackedWizardPage(new BioForm(getWizardForm().getFormModel())));
    }

    protected boolean onFinish() {
        getWizardForm().commit();
        character = (PlayerCharacter) getWizardForm().getFormObject();
		characterDao.saveCharacter(character);
        return true;
    }

    public void execute() {
        getWizardForm().setFormObject(characterFactory.createCharacter("New Character"));
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

    public void setCharacterFactory(CharacterFactory characterFactory) {
    	this.characterFactory = characterFactory;
    }

    public void setCharacterDao(CharacterDao characterDao) {
    	this.characterDao = characterDao;
    }

    public void setRaceDao(RaceDao raceDao) {
        this.raceDao = raceDao;
    }

    public void setAlignmentDao(AlignmentDao alignmentDao) {
    	this.alignmentDao = alignmentDao;
    }

    public PlayerCharacter getCharacter() {
    	return character;
    }
}
