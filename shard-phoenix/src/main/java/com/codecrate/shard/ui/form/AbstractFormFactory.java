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
package com.codecrate.shard.ui.form;

import org.springframework.binding.form.FormModel;
import org.springframework.richclient.dialog.AbstractDialogPage;
import org.springframework.richclient.dialog.FormBackedDialogPage;
import org.springframework.richclient.form.AbstractForm;
import org.springframework.richclient.form.FormModelHelper;

public abstract class AbstractFormFactory implements FormFactory {
	public AbstractForm createInitialForm(Object model) {
		return createInitialFormWithModel(FormModelHelper.createFormModel(model));
	}
    
    public AbstractDialogPage createPage(AbstractForm form) {
        return new FormBackedDialogPage(form);
    }

	public abstract AbstractForm createInitialFormWithModel(FormModel formModel);
}
