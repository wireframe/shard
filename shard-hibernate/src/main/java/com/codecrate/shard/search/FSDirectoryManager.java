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
package com.codecrate.shard.search;

import java.io.File;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class FSDirectoryManager implements DirectoryManager {
	private static final Log LOG = LogFactory.getLog(FSDirectoryManager.class);

	private Directory directory;

	public FSDirectoryManager(String path) throws IOException {
		if (!isIndexAvailable(path)) {
			initializeIndex(path);
		}
		directory = FSDirectory.getDirectory(path, false);
	}

	public Directory getDirectory() {
		return directory;
	}

	public void close() {
		try {
			directory.close();
		} catch (IOException e) {
			LOG.error("Error closing directory: " + directory, e);
		}
	}

	private boolean isIndexAvailable(String path) {
        File rootPath = new File(path);
        return rootPath.exists();
	}

	private void initializeIndex(String path) throws IOException {
        File rootPath = new File(path);
        rootPath.mkdirs();
        directory = FSDirectory.getDirectory(path, true);
		IndexWriter writer = new IndexWriter(directory, new StandardAnalyzer(), true);
		writer.close();
	}
}
