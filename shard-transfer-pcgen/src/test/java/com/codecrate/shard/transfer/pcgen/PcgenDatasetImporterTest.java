package com.codecrate.shard.transfer.pcgen;

import java.util.HashMap;

import junit.framework.TestCase;

public class PcgenDatasetImporterTest extends TestCase {

	public void testAbleToLoadDatasetsFromClassPath() {
		PcgenDatasetImporter importer = new PcgenDatasetImporter(new HashMap());
		assertFalse(importer.getAvailableDatasets().isEmpty());
	}
}
