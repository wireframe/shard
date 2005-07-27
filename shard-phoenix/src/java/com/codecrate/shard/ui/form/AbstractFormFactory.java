package com.codecrate.shard.ui.form;

import org.springframework.richclient.form.AbstractForm;
import org.springframework.richclient.form.FormModelHelper;

public abstract class AbstractFormFactory implements FormFactory {
	public AbstractForm createForm(Object model) {
		return createForm(FormModelHelper.createCompoundFormModel(model));
	}
}
