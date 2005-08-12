package com.codecrate.shard.lucene;

import org.apache.lucene.store.Directory;

public interface DirectoryManager {

	Directory getDirectory();

	void close();

}
