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
package com.codecrate.shard.ui.command;

import java.io.File;

import javax.swing.JComponent;
import javax.swing.SwingUtilities;

import org.springframework.binding.form.FormModel;
import org.springframework.binding.form.ValidatingFormModel;
import org.springframework.binding.validation.DefaultValidationResults;
import org.springframework.binding.validation.Severity;
import org.springframework.binding.validation.ValidationResults;
import org.springframework.binding.validation.Validator;
import org.springframework.richclient.command.ActionCommandExecutor;
import org.springframework.richclient.command.support.ApplicationWindowAwareCommand;
import org.springframework.richclient.dialog.FormBackedDialogPage;
import org.springframework.richclient.form.AbstractForm;
import org.springframework.richclient.form.FormModelHelper;
import org.springframework.richclient.form.binding.swing.SwingBindingFactory;
import org.springframework.richclient.form.builder.TableFormBuilder;
import org.springframework.richclient.progress.BusyIndicator;
import org.springframework.richclient.progress.ProgressMonitor;
import org.springframework.richclient.progress.StatusBar;

import com.codecrate.shard.race.RaceDao;
import com.codecrate.shard.transfer.pcgen.PcgenDatasetImporter;
import com.codecrate.shard.ui.binding.JDirectoryChooserBinding;
import com.codecrate.shard.ui.form.FormModelCommittingTitledPageApplicationDialog;
import com.codecrate.shard.ui.transfer.ImportProgressAdapter;
import com.l2fprod.common.swing.JDirectoryChooser;

import foxtrot.Job;
import foxtrot.Worker;

public class ImportDatasetCommand extends ApplicationWindowAwareCommand implements ActionCommandExecutor {
    private final RaceDao raceDao;
    private final PcgenDatasetImporter importer;

    public ImportDatasetCommand(RaceDao raceDao, PcgenDatasetImporter importer) {
        this.raceDao = raceDao;
        this.importer = importer;
    }

    protected void doExecuteCommand() {
        final DirectorySelection directorySelection = new DirectorySelection();
        ValidatingFormModel model = FormModelHelper.createFormModel(directorySelection);
        //model.setValidator(new ValidDatasetValidator());
        final DirectorySelectionForm form = new DirectorySelectionForm(model);
        final FormBackedDialogPage page = new FormBackedDialogPage(form);

        FormModelCommittingTitledPageApplicationDialog dialog = new FormModelCommittingTitledPageApplicationDialog(page, getApplicationWindow().getControl(), model) {
            protected boolean doOnFinish() {
            	importFile(directorySelection.getSelectedDirectory());
                return true;
            }
        };
        dialog.showDialog();
    }

    public boolean isImportNeeded() {
        return raceDao.getRaces().isEmpty();
    }

    private void importFile(final File selectedFile) {
        Job importTask = new Job() {
            public Object run() {
                importer.importObjects(selectedFile, new ImportProgressAdapter(getProgressMonitor()));
                return null;
            }
        };
        executeAsyncBlockingJobInBackground("Importing...", importTask);
    }

    private ProgressMonitor getProgressMonitor() {
    	return getApplicationWindow().getStatusBar().getProgressMonitor();
    }
    private void executeAsyncBlockingJobInBackground(final String description, final Job job) {
    	SwingUtilities.invokeLater(new Runnable() {
			public void run() {
		        BusyIndicator.showAt(getApplicationWindow().getControl());
		        getProgressMonitor().taskStarted(description, StatusBar.UNKNOWN);

		        Worker.post(job);

		        BusyIndicator.clearAt(getApplicationWindow().getControl());
		        getProgressMonitor().done();
			}
    	});
    }

    public class DirectorySelectionForm extends AbstractForm {
        private static final String PAGE_NAME = "importPage";

        public DirectorySelectionForm(FormModel formModel) {
            super(formModel, PAGE_NAME);
        }

        protected JComponent createFormControl() {
            SwingBindingFactory bindingFactory = (SwingBindingFactory) getBindingFactory();

            JDirectoryChooser directoryChooser = new JDirectoryChooser();
            directoryChooser.setControlButtonsAreShown(false);

            TableFormBuilder formBuilder = new TableFormBuilder(bindingFactory);
            formBuilder.add(new JDirectoryChooserBinding(getFormModel(), "selectedDirectory", directoryChooser));

            return formBuilder.getForm();
        }
    }

    public class DirectorySelection {
		private File selectedDirectory;

        public File getSelectedDirectory() {
            return selectedDirectory;
        }

        public void setSelectedDirectory(File selectedDirectory) {
            this.selectedDirectory = selectedDirectory;
        }
    }

    public class ValidDatasetValidator implements Validator {

		public ValidationResults validate(Object model) {
			DefaultValidationResults results = new DefaultValidationResults();

			DirectorySelection directorySelection = (DirectorySelection) model;
			File directory = directorySelection.getSelectedDirectory();
			if (!importer.isDataset(directory)) {
				results.addMessage("not.valid.dataset", Severity.ERROR, "Not a valid dataset");
			}
			return results;
		}
	}
}
