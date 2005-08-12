package com.codecrate.shard.lucene;

import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;

public class RAMDirectoryManager implements DirectoryManager {

	private Directory directory;

	public RAMDirectoryManager() {
		this.directory = new RAMDirectory();
	}

	public Directory getDirectory() {
		return directory;
	}

	public void close() {
	}

}
