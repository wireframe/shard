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
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class FileExtensionObjectImporterResolver implements ObjectImporter {
	private static final Log LOG = LogFactory.getLog(FileExtensionObjectImporterResolver.class);

	private final Map importers;

	public FileExtensionObjectImporterResolver(Map importers) {
		this.importers = importers;
	}

	public Collection importObjects(File file) {
		String filename = file.getName();
		int index = filename.lastIndexOf(".");
		String extension = filename.substring(index + 1);
		
		ObjectImporter importer = (ObjectImporter) importers.get(extension);
		if (null == importer) {
			LOG.info("No importer found to support file with extension " + extension);
			return Collections.EMPTY_LIST;
		}

		return importer.importObjects(file);
	}
	
	public Collection getSupportedExtensions() {
		return importers.keySet();
	}
}
