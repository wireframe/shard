package com.codecrate.shard.lucene;

import java.io.IOException;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;

public class RAMDirectoryManager implements DirectoryManager {

	private final Directory directory;

	public RAMDirectoryManager() throws IOException {
		this.directory = new RAMDirectory();
		IndexWriter writer = new IndexWriter(directory, new StandardAnalyzer(), true);
		writer.close();
	}

	public Directory getDirectory() {
		return directory;
	}

	public void close() {
	}

}
