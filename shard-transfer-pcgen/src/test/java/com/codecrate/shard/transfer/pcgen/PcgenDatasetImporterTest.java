package com.codecrate.shard.transfer.pcgen;

import java.io.File;
import java.util.Collections;

import junit.framework.TestCase;

import com.codecrate.shard.transfer.FileUtils;

public class PcgenDatasetImporterTest extends TestCase {

	public void testAbleToLocateDatasetsInChildDirectories() {
        File file = FileUtils.getFile("pcgen-5.10.1");

		PcgenDatasetImporter importer = new PcgenDatasetImporter(Collections.EMPTY_MAP);
		assertFalse(importer.getAvailableDatasets(file).isEmpty());
	}
}
