package com.codecrate.shard.ui.command;

import java.io.File;

import org.springframework.context.ApplicationEvent;

public class ImportDatasetEvent extends ApplicationEvent {
    private File selectedDirectory;

    public ImportDatasetEvent(Object source) {
        super(source);
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
}
