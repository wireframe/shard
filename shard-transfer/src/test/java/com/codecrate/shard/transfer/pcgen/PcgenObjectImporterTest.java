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
package com.codecrate.shard.transfer.pcgen;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import org.easymock.MockControl;

public class PcgenObjectImporterTest extends TestCase {

    public void testBasicImport() throws Exception {
        Map tags = new HashMap();
        tags.put("KEYSTAT", "STR");

        MockControl mockLineHanlder = MockControl.createControl(PcgenObjectImporter.PcgenLineHandler.class);
        PcgenObjectImporter.PcgenLineHandler lineHandler = (PcgenObjectImporter.PcgenLineHandler) mockLineHanlder.getMock();
        lineHandler.handleParsedLine("Climb", tags);
        mockLineHanlder.setReturnValue(new Object());
        lineHandler.handleParsedLine("Jump", tags);
        mockLineHanlder.setReturnValue(new Object());
        mockLineHanlder.replay();

        File file = new File(Thread.currentThread().getContextClassLoader().getResource("pcgen.lst").getFile());
        PcgenObjectImporter importer = new PcgenObjectImporter(lineHandler);
		Collection results = importer.importObjects(file);

		assertFalse(results.isEmpty());
        assertEquals(2, results.size());
    }

    public void testExceptionHandlingRowDoesKeepsGoing() throws Exception {
        Map tags = new HashMap();
        tags.put("KEYSTAT", "STR");

        MockControl mockLineHanlder = MockControl.createControl(PcgenObjectImporter.PcgenLineHandler.class);
        PcgenObjectImporter.PcgenLineHandler lineHandler = (PcgenObjectImporter.PcgenLineHandler) mockLineHanlder.getMock();
        lineHandler.handleParsedLine("Climb", tags);
        mockLineHanlder.setThrowable(new IllegalArgumentException("Test error while parsing"));
        lineHandler.handleParsedLine("Jump", tags);
        mockLineHanlder.setReturnValue(new Object());
        mockLineHanlder.replay();

        File file = new File(Thread.currentThread().getContextClassLoader().getResource("pcgen.lst").getFile());
        PcgenObjectImporter importer = new PcgenObjectImporter(lineHandler);
        Collection results = importer.importObjects(file);

        assertFalse(results.isEmpty());
        assertEquals(1, results.size());
    }
}
