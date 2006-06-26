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
