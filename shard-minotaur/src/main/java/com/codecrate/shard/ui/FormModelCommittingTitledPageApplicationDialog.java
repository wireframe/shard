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

import java.awt.Window;

import org.springframework.binding.form.FormModel;
import org.springframework.richclient.dialog.DialogPage;
import org.springframework.richclient.dialog.TitledPageApplicationDialog;

public abstract class FormModelCommittingTitledPageApplicationDialog extends TitledPageApplicationDialog {

	private final FormModel model;
	private final DialogPage page;

	public FormModelCommittingTitledPageApplicationDialog(DialogPage page, Window window, FormModel formModel) {
		super(page, window);
		this.model = formModel;
		this.page = page;
	}
    protected void onAboutToShow() {
        setEnabled(page.isPageComplete());
    }

    protected final boolean onFinish() {
        model.commit();
        return doOnFinish();
    }

    protected abstract boolean doOnFinish();

}
