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
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import com.codecrate.shard.io.AutoCreateDirectoriesFile;

public class FSDirectoryManager implements DirectoryManager {
	private static final Log LOG = LogFactory.getLog(FSDirectoryManager.class);
    private static final String INDEX_FILE_NAME = "segments";

	private final Directory directory;
    private final Analyzer analyzer;
    private final File indexDirectory;

	public FSDirectoryManager(File applicationWorkingDirectory, Analyzer analyzer) throws IOException {
        this.analyzer = analyzer;
        indexDirectory = new AutoCreateDirectoriesFile(applicationWorkingDirectory, "index");

        if (!isIndexAvailable()) {
            initializeIndex();
        }

		directory = FSDirectory.getDirectory(indexDirectory, false);
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

    private boolean isIndexAvailable() {
        File indexFile = new File(indexDirectory, INDEX_FILE_NAME);
        return indexFile.exists();
    }

	private void initializeIndex() throws IOException {
        Directory directory = FSDirectory.getDirectory(indexDirectory, true);
        LOG.info("Initializing search index: " + directory);
		IndexWriter writer = new IndexWriter(directory, analyzer, true);
		writer.close();
        directory.close();
	}
}
