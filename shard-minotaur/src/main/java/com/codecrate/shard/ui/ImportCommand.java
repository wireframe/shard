package com.codecrate.shard.ui;

import java.io.File;

import javax.swing.JFileChooser;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.richclient.command.ActionCommandExecutor;
import org.springframework.richclient.command.support.ApplicationWindowAwareCommand;
import org.springframework.richclient.filechooser.DefaultFileFilter;
import org.springframework.richclient.progress.BusyIndicator;
import org.springframework.richclient.progress.StatusBar;

import com.codecrate.shard.race.RaceDao;
import com.codecrate.shard.transfer.ObjectImporter;
import com.codecrate.shard.transfer.progress.ProgressMonitor;

import foxtrot.Job;
import foxtrot.Worker;

public class ImportCommand extends ApplicationWindowAwareCommand implements ActionCommandExecutor {
    private static final Log LOG = LogFactory.getLog(ImportCommand.class);
    private final RaceDao raceDao;
    private final ObjectImporter importer;
    
    public ImportCommand(RaceDao raceDao, ObjectImporter importer) {
        this.raceDao = raceDao;
        this.importer = importer;
    }
    protected void doExecuteCommand() {
        if (!isImportNeeded()) {
            LOG.info("Import not needed at this time.");
            return;
        }
        System.out.println(getApplicationWindow());
        JFileChooser fileChooser = new JFileChooser();
        DefaultFileFilter filter = new DefaultFileFilter();
        fileChooser.setFileFilter(filter);
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        
        if (JFileChooser.APPROVE_OPTION == fileChooser.showOpenDialog(getApplicationWindow().getControl())) {
            final File selectedFile = fileChooser.getSelectedFile();
            importFile(selectedFile);
        }
    }

    private boolean isImportNeeded() {
        return raceDao.getRaces().isEmpty();
    }
    private void importFile(final File selectedFile) {
        Job importTask = new Job() {
            public Object run() {
                importer.importObjects(selectedFile, new ImportProgressAdapter(getApplicationWindow().getStatusBar().getProgressMonitor()));
                return null;
            }
        };
        importTask.run();
        executeBlockingJobInBackground("Importing...", importTask);
    }
    private Object executeBlockingJobInBackground(String description, Job job) {
        org.springframework.richclient.progress.ProgressMonitor progressMonitor = getApplicationWindow().getStatusBar().getProgressMonitor();
        BusyIndicator.showAt(getApplicationWindow().getControl());
        progressMonitor.taskStarted(description, StatusBar.UNKNOWN);

        Object result = Worker.post(job);

        BusyIndicator.clearAt(getApplicationWindow().getControl());
        progressMonitor.done();

        return result;
    }
    
    public class ImportProgressAdapter implements ProgressMonitor {

        private final org.springframework.richclient.progress.ProgressMonitor delegate;
        private int unitsWorked = 0;

        public ImportProgressAdapter(org.springframework.richclient.progress.ProgressMonitor delegate) {
            this.delegate = delegate;
        }

        public void startTask(String description, int totalUnitsOfWork) {
            unitsWorked = 0;
            delegate.taskStarted(description, totalUnitsOfWork);
        }

        public void completeUnitOfWork() {
            unitsWorked++;
            delegate.worked(unitsWorked);
        }

        public void finish() {
            delegate.done();
        }
    }
}
