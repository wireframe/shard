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
package com.codecrate.shard.transfer.pcgen;

import java.io.File;
import java.util.Iterator;
import java.util.Map;

import com.codecrate.shard.transfer.ObjectImporter;

/**
 * import a complete pcgen dataset.
 *
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class PcgenDatasetImporter {
    private final Map importers;

    public PcgenDatasetImporter(Map importers) {
        this.importers = importers;
    }

    public void importDataset(File dataset) {
        File[] files = dataset.listFiles();

        for (Iterator supportedFileExpressions = importers.keySet().iterator(); supportedFileExpressions.hasNext();) {
            String supportedFileExpression = (String) supportedFileExpressions.next();
            ObjectImporter importer = (ObjectImporter) importers.get(supportedFileExpression);

            for (int x = 0; x < files.length; x++) {
                File file = files[x];
                if (file.getName().contains(supportedFileExpression)){
                    importer.importObjects(file);
                }
            }
        }
    }
}
