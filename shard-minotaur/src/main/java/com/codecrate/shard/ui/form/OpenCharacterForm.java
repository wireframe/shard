package com.codecrate.shard.ui.form;

import javax.swing.JComponent;

import org.springframework.binding.form.FormModel;
import org.springframework.richclient.form.AbstractForm;
import org.springframework.richclient.form.binding.swing.SwingBindingFactory;
import org.springframework.richclient.form.builder.TableFormBuilder;

import com.codecrate.shard.character.CharacterDao;

public class OpenCharacterForm extends AbstractForm {

    private static final String PAGE_NAME = "openCharacterPage";
    private final CharacterDao characterDao;

    public OpenCharacterForm(CharacterDao characterDao, FormModel formModel) {
        super(formModel, PAGE_NAME);
        this.characterDao = characterDao;
    }

    protected JComponent createFormControl() {
        SwingBindingFactory bindingFactory= (SwingBindingFactory) getBindingFactory();

        TableFormBuilder formBuilder = new TableFormBuilder(bindingFactory);
        formBuilder.add(bindingFactory.createBoundComboBox("character", characterDao.getCharacters()));

        return formBuilder.getForm();
    }

}
