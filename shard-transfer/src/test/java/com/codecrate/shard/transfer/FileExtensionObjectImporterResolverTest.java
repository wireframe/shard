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
import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

public class FileExtensionObjectImporterResolverTest extends TestCase {

	public void testNoImporterFoundReturnsEmptyList() {
        File file = new File(Thread.currentThread().getContextClassLoader().getResource("feats.xls").getFile());
		FileExtensionObjectImporterResolver importer = new FileExtensionObjectImporterResolver(Collections.EMPTY_MAP);
		Collection results = importer.importObjects(file);

		assertTrue(results.isEmpty());
	}
	
	public void testSupportedExtensionsReturnsConfiguredImporters() {
		Map importers = new HashMap();
		importers.put("xls", null);
		importers.put("xml", null);
		FileExtensionObjectImporterResolver importer = new FileExtensionObjectImporterResolver(importers);

		Collection extensions = importer.getSupportedExtensions();
		assertTrue(extensions.contains("xls"));
		assertTrue(extensions.contains("xml"));
	}
}
