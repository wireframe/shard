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
import javax.swing.JFileChooser;

import org.springframework.binding.form.FormModel;
import org.springframework.richclient.filechooser.DefaultFileFilter;
import org.springframework.richclient.form.AbstractForm;
import org.springframework.richclient.form.binding.swing.SwingBindingFactory;
import org.springframework.richclient.form.builder.TableFormBuilder;

import com.codecrate.shard.ui.binding.JFileChooserBinding;

/**
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class BioForm extends AbstractForm {

    public static final String PAGE_NAME = "bioPage";

    public BioForm(FormModel formModel) {
        super(formModel, PAGE_NAME);
    }

    protected JComponent createFormControl() {
        SwingBindingFactory bindingFactory = (SwingBindingFactory) getBindingFactory();

        JFileChooser fileChooser = new JFileChooser();
        DefaultFileFilter filter = new DefaultFileFilter();
        filter.addExtension("jpg");
        filter.addExtension("gif");
        filter.addExtension("png");

		fileChooser.setFileFilter(filter);

        TableFormBuilder formBuilder = new TableFormBuilder(getBindingFactory());
        formBuilder.add("bio.name");
        formBuilder.row();
		formBuilder.add(new JFileChooserBinding(getFormModel(), "bio.portraitFile", fileChooser));
        formBuilder.row();
        formBuilder.add("bio.height");
        formBuilder.row();
        formBuilder.add("bio.weight");
        formBuilder.row();
        formBuilder.addInScrollPane(bindingFactory.createBoundTextArea("bio.backstory", 5, 0));
        return formBuilder.getForm();
    }
}
