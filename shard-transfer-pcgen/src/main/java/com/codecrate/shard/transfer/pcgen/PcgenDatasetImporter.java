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
import java.util.TreeSet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.codecrate.shard.transfer.ObjectImporter;
import com.codecrate.shard.transfer.progress.ProgressMonitor;

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

    public Collection importObjects(File dataset, ProgressMonitor progress) {
        if (!isDataset(dataset)) {
            LOG.warn(dataset + " is not a dataset.");
            return Collections.EMPTY_LIST;
        }
        File[] files = dataset.listFiles();

        progress.startTask("Importing dataset " + dataset, countTotalNumberOfFilesToImport(files));
        for (Iterator supportedFileExpressions = importers.keySet().iterator(); supportedFileExpressions.hasNext();) {
            String supportedFileExpression = (String) supportedFileExpressions.next();
            ObjectImporter importer = (ObjectImporter) importers.get(supportedFileExpression);

            for (int x = 0; x < files.length; x++) {
                File file = files[x];
                if (doesFileMatchExpression(supportedFileExpression, file) && !progress.isCancelled()) {
                    importer.importObjects(file, progress);

                    progress.completeUnitOfWork();
                }
            }
        }

        progress.finish();
        return Collections.EMPTY_LIST;
    }

	private int countTotalNumberOfFilesToImport(File[] files) {
        int numberOfFiles = 0;
		for (Iterator supportedFileExpressions = importers.keySet().iterator(); supportedFileExpressions.hasNext();) {
            String supportedFileExpression = (String) supportedFileExpressions.next();

            for (int x = 0; x < files.length; x++) {
                File file = files[x];
                if (doesFileMatchExpression(supportedFileExpression, file)) {
                	numberOfFiles ++;
                }
            }
        }
        return numberOfFiles;
	}

	private boolean doesFileMatchExpression(String supportedFileExpression, File file) {
		if (!isPcgenLstFile(file)) {
			return false;
		}
		return -1 != file.getName().indexOf(supportedFileExpression);
	}

    public boolean isDataset(File dataset) {
    	if (null == dataset) {
    		return false;
    	}
        File[] files = dataset.listFiles();
        if (null == files) {
            return false;
        }
        for (int x = 0; x < files.length; x++) {
            File file = files[x];
            if (isPcgenLstFile(file)) {
                return true;
            }
        }
        return false;
    }

	private boolean isPcgenLstFile(File file) {
		return -1 != file.getName().indexOf(".lst");
	}

    /**
     * get all availble datasets from a given directory
     */
    public Collection getAvailableDatasets(File rootDirectory) {
        return findDatasets(rootDirectory);
    }

    private Collection findDatasets(File root) {
    	if (isDataset(root)) {
    		return Collections.singleton(root);
    	}

    	Collection results = new TreeSet();
        File[] files = root.listFiles();
        if (null != files) {
            for (int i = 0; i < files.length; i++) {
                File child = files[i];
                if (child.isDirectory()) {
                	results.addAll(findDatasets(child));
                }
            }
        }
        return results;
    }
}
