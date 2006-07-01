package com.codecrate.shard.ui.command;

import java.io.File;

import org.springframework.context.ApplicationEvent;

import com.codecrate.shard.transfer.progress.ProgressMonitor;

public class ImportDatasetEvent extends ApplicationEvent {
    private File selectedDirectory;
    private final ProgressMonitor monitor;

    public ImportDatasetEvent(Object source, ProgressMonitor monitor) {
        super(source);
        this.monitor = monitor;
    }

    public File getSelectedDirectory() {
        return selectedDirectory;
    }

    public void setSelectedDirectory(File selectedDirectory) {
        this.selectedDirectory = selectedDirectory;
    }

    public String toString() {
        return ImportDatasetEvent.class.getName() + " selectedDirectory=" + selectedDirectory;
    }

    public ProgressMonitor getMonitor() {
        return monitor;
    }
}
