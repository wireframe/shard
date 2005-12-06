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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import junit.framework.TestCase;

public class FileExtensionObjectImporterResolverTest extends TestCase {

	public void testImportWithUnsupportedFileExtensionReturnsEmptyList() {
        File file = new File(Thread.currentThread().getContextClassLoader().getResource("feats.xls").getFile());
		FileExtensionObjectImporterResolver importer = new FileExtensionObjectImporterResolver(Collections.EMPTY_LIST);
		Collection results = importer.importObjects(file);

		assertTrue(results.isEmpty());
	}

	public void testSupportedExtensionsReturnsConfiguredImporters() {
        Collection importers = new ArrayList();
        importers.add(new DummyObjectImporter("xls"));
        importers.add(new DummyObjectImporter("xml"));
		FileExtensionObjectImporterResolver importer = new FileExtensionObjectImporterResolver(importers);

		Collection extensions = importer.getSupportedFileExtensions();
		assertTrue(extensions.contains("xls"));
		assertTrue(extensions.contains("xml"));
	}

    private class DummyObjectImporter implements ObjectImporter {

        private final String extension;

        public DummyObjectImporter(String extension) {
            this.extension = extension;
        }

        public Collection importObjects(File file) {
            return Collections.EMPTY_LIST;
        }

        public Collection getSupportedFileExtensions() {
            return Collections.singletonList(extension);
        }

    }
}
