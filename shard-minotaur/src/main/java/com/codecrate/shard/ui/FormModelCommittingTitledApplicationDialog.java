package com.codecrate.shard.ui;

import org.springframework.richclient.dialog.TitledApplicationDialog;

public abstract class FormModelCommittingTitledApplicationDialog extends TitledApplicationDialog {

	private final FormModel formModel;

	public FormModelCommittingTitledApplicationDialog(FormBackedDialogPage page, JComponent frame, FormModel formModel) {
		super(page, frame);
		this.formModel = formModel;
	}
    protected void onAboutToShow() {
        setEnabled(getPage().isPageComplete());
    }

    protected final boolean onFinish() {
        getFormModel().commit();
        return doOnFinish();
    }

	public FormModel getFormModel() {
		return formModel;
	}

    protected abstract boolean doOnFinish();

}
