/*
 * Copyright 2004 codecrate consulting
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.codecrate.shard.ui.command;

import java.io.File;
import java.util.Collection;

import com.codecrate.shard.transfer.ObjectImporter;
import com.codecrate.shard.transfer.pcgen.PcgenDatasetImporter;

public abstract class AbstractObjectManagerCommandAdapter implements ObjectManagerCommandAdapter {

    private final ObjectImporter importer;
    private final PcgenDatasetImporter datasetImporter;

    public AbstractObjectManagerCommandAdapter(ObjectImporter importer, PcgenDatasetImporter datasetImporter) {
        this.importer = importer;
        this.datasetImporter = datasetImporter;
    }

    public Collection importObjects(File file) {
        return importer.importObjects(file);
    }
    
    public void importDataset(File selectedFile) {
        datasetImporter.importDataset(selectedFile);
    }

    public Collection getSupportedImportFileExtensions() {
        return importer.getSupportedFileExtensions();
    }
}
