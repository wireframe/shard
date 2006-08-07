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

import org.springframework.richclient.progress.ProgressMonitor;

import com.codecrate.shard.transfer.pcgen.PcgenDatasetImporter;
import com.codecrate.shard.ui.transfer.EventDispatcherThreadProgressMonitor;
import com.codecrate.shard.ui.transfer.ImportProgressAdapter;

public class ImportDatasetService {
    private final PcgenDatasetImporter importer;

    public ImportDatasetService(PcgenDatasetImporter importer) {
        this.importer = importer;
    }

    public void importDataset(ImportDatasetEvent event, ProgressMonitor progressMonitor) {
        importer.importObjects(event.getSelectedDirectory(), new EventDispatcherThreadProgressMonitor(new ImportProgressAdapter(progressMonitor)));
    }
}
