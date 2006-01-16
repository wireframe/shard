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
package com.codecrate.shard.transfer;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class FileExtensionObjectImporterResolver implements ObjectImporter {
	private static final Log LOG = LogFactory.getLog(FileExtensionObjectImporterResolver.class);

	private final Collection importers;

	public FileExtensionObjectImporterResolver(Collection importers) {
		this.importers = importers;
	}

	public Collection importObjects(File file) {
		ObjectImporter importer = (ObjectImporter) getImporterForFile(file);
		if (null == importer) {
			LOG.info("No importer found to support file " + file);
			return Collections.EMPTY_LIST;
		}

		return importer.importObjects(file);
	}

    private ObjectImporter getImporterForFile(File file) {
        if (file.isDirectory()) {
            return getImporterForDirectory();
        }
        String filename = file.getName();
        int index = filename.lastIndexOf(".");
        String extension = filename.substring(index + 1);
        return getImporterForExtension(extension);
    }

    private ObjectImporter getImporterForDirectory() {
        for (Iterator it = importers.iterator(); it.hasNext();) {
            ObjectImporter importer = (ObjectImporter) it.next();
            if (importer.isDirectoryImportSupported()) {
                return importer;
            }
        }
        return null;
    }

    private ObjectImporter getImporterForExtension(String extension) {
        Iterator it = importers.iterator();
        while (it.hasNext()) {
            ObjectImporter importer = (ObjectImporter) it.next();
            if (importer.getSupportedFileExtensions().contains(extension)) {
                return importer;
            }
        }
        return null;
    }

	public Collection getSupportedFileExtensions() {
	    Collection extensions = new ArrayList();
        Iterator it = importers.iterator();
        while (it.hasNext()) {
            ObjectImporter importer = (ObjectImporter) it.next();
            extensions.addAll(importer.getSupportedFileExtensions());
        }
        return extensions;
    }

    public boolean isDirectoryImportSupported() {
        for (Iterator it = importers.iterator(); it.hasNext();) {
            ObjectImporter importer = (ObjectImporter) it.next();
            if (importer.isDirectoryImportSupported()) {
                return true;
            }
        }
        return false;
    }

}
