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
