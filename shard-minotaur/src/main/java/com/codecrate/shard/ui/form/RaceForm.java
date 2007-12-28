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

import java.util.Collection;

import javax.swing.JComponent;

import org.springframework.binding.form.FormModel;
import org.springframework.richclient.form.AbstractForm;
import org.springframework.richclient.form.binding.swing.SwingBindingFactory;
import org.springframework.richclient.form.builder.TableFormBuilder;

import com.codecrate.shard.character.Alignment;
import com.codecrate.shard.race.RaceDao;

/**
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class RaceForm extends AbstractForm {

    public static final String PAGE_NAME = "racePage";
    private final RaceDao raceDao;

    public RaceForm(FormModel formModel, RaceDao raceDao) {
        super(formModel, PAGE_NAME);
        this.raceDao = raceDao;
    }
    
    protected JComponent createFormControl() {
        SwingBindingFactory bindingFactory = (SwingBindingFactory) getBindingFactory();
        
        TableFormBuilder formBuilder = new TableFormBuilder(bindingFactory);
        formBuilder.add(bindingFactory.createBoundComboBox("race", getRaces()));
        formBuilder.row();
        formBuilder.add(bindingFactory.createBoundComboBox("alignment", getAlignments()));
        return formBuilder.getForm();
    }

    private Collection getRaces() {
        return raceDao.getRaces();
    }
    
    private Collection getAlignments() {
    	return Alignment.values();
    }
}
