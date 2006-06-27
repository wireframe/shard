package com.codecrate.shard.ui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.Map;

import javax.swing.JComponent;

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
import org.springframework.richclient.form.binding.Binding;
import org.springframework.richclient.form.binding.support.AbstractBinder;
import org.springframework.richclient.form.binding.support.CustomBinding;
import org.springframework.richclient.form.binding.swing.SwingBindingFactory;
import org.springframework.richclient.form.builder.TableFormBuilder;
import org.springframework.richclient.progress.BusyIndicator;
import org.springframework.richclient.progress.ProgressMonitor;
import org.springframework.richclient.progress.StatusBar;

import com.codecrate.shard.race.RaceDao;
import com.codecrate.shard.transfer.pcgen.PcgenDatasetImporter;
import com.l2fprod.common.swing.JDirectoryChooser;

import foxtrot.Job;
import foxtrot.Worker;

public class ImportCommand extends ApplicationWindowAwareCommand implements ActionCommandExecutor {
    private final RaceDao raceDao;
    private final PcgenDatasetImporter importer;

    public ImportCommand(RaceDao raceDao, PcgenDatasetImporter importer) {
        this.raceDao = raceDao;
        this.importer = importer;
    }

    protected void doExecuteCommand() {
        final DirectorySelection directorySelection = new DirectorySelection();
        ValidatingFormModel model = FormModelHelper.createFormModel(directorySelection);
        //model.setValidator(new ValidPcgenDatasetValidator());
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
                importer.importObjects(selectedFile, new ImportProgressAdapter(getApplicationWindow().getStatusBar().getProgressMonitor()));
                return null;
            }
        };
        executeAsyncBlockingJobInBackground("Importing...", importTask);
    }

    private void executeAsyncBlockingJobInBackground(String description, Job job) {
        ProgressMonitor progressMonitor = getApplicationWindow().getStatusBar().getProgressMonitor();
        BusyIndicator.showAt(getApplicationWindow().getControl());
        progressMonitor.taskStarted(description, StatusBar.UNKNOWN);

        //use foxtrot 3 async worker
        Worker.post(job);

        BusyIndicator.clearAt(getApplicationWindow().getControl());
        progressMonitor.done();
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

    public class ValidPcgenDatasetValidator implements Validator {

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

    public class JDirectoryChooserBinding extends CustomBinding {

        private final JDirectoryChooser component;

        protected JDirectoryChooserBinding(FormModel model, String property, JDirectoryChooser component) {
            super(model, property, File.class);
            this.component = component;
        }

        protected JComponent doBindControl() {
            component.setSelectedFile((File)getValue());
            component.addPropertyChangeListener(new PropertyChangeListener() {
                public void propertyChange(PropertyChangeEvent evt) {
                    String prop = evt.getPropertyName();
                    if(JDirectoryChooser.SELECTED_FILE_CHANGED_PROPERTY.equals(prop)) {
                        File file = (File) evt.getNewValue();
                        controlValueChanged(file);
                    }
                }
            });
            return component;
        }

        protected void readOnlyChanged() {
            component.setEnabled(isEnabled() && !isReadOnly());
        }

        protected void enabledChanged() {
            component.setEnabled(isEnabled() && !isReadOnly());
        }

        protected void valueModelChanged(Object newValue) {
            component.setSelectedFile((File)newValue);
        }
    }

    public class JDirectoryChooserBinder extends AbstractBinder {

        protected JDirectoryChooserBinder() {
            super(File.class);
        }

        protected JComponent createControl(Map context) {
            return new JDirectoryChooser();
        }

        protected Binding doBind(JComponent control, FormModel formModel, String formPropertyPath, Map context) {
            final JDirectoryChooser directoryChooser = (JDirectoryChooser) control;
            return new JDirectoryChooserBinding(formModel, formPropertyPath, directoryChooser);
        }
    }
}
