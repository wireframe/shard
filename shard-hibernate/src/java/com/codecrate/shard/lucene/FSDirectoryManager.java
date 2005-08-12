package com.codecrate.shard.lucene;

import java.io.File;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
        FSDirectory.getDirectory(path, true);
	}
}
