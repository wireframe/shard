package com.codecrate.shard.ui.form;

import org.springframework.binding.form.NestingFormModel;
import org.springframework.richclient.form.AbstractForm;

public interface FormFactory {
	AbstractForm createForm(Object model);
	
	AbstractForm createForm(NestingFormModel model);
}
