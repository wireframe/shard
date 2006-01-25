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
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.codecrate.shard.transfer.ObjectImporter;

/**
 * import a complete pcgen dataset.
 *
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class PcgenDatasetImporter implements ObjectImporter {
    private static final Log LOG = LogFactory.getLog(PcgenDatasetImporter.class);

    private final Map importers;

    public PcgenDatasetImporter(Map importers) {
        this.importers = importers;
    }

    public Collection getSupportedFileExtensions() {
        return Collections.EMPTY_LIST;
    }
    
    public boolean isDirectoryImportSupported() {
        return true;
    }

    public Collection importObjects(File dataset) {
        if (!isDataset(dataset)) {
            LOG.warn(dataset + " is not a dataset.");
        }
        File[] files = dataset.listFiles();

        for (Iterator supportedFileExpressions = importers.keySet().iterator(); supportedFileExpressions.hasNext();) {
            String supportedFileExpression = (String) supportedFileExpressions.next();
            ObjectImporter importer = (ObjectImporter) importers.get(supportedFileExpression);

            for (int x = 0; x < files.length; x++) {
                File file = files[x];
                if (-1 != file.getName().indexOf(supportedFileExpression)) {
                    importer.importObjects(file);
                }
            }
        }
        
        return Collections.EMPTY_LIST;
    }

    private boolean isDataset(File dataset) {
        File[] files = dataset.listFiles();
        for (int x = 0; x < files.length; x++) {
            File file = files[x];
            if (-1 != file.getName().indexOf(".lst")) {
                return true;
            }
        }
        return false;
    }
}
