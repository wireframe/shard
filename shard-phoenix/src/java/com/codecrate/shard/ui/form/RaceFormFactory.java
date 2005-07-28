package com.codecrate.shard.ui.form;

import org.springframework.binding.form.NestingFormModel;
import org.springframework.richclient.form.AbstractForm;

public class RaceFormFactory extends AbstractFormFactory implements FormFactory {

	public AbstractForm createForm(NestingFormModel formModel) {
		return new RaceForm(formModel);
	}
}
