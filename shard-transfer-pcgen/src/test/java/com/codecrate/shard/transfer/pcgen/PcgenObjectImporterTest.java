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
import java.util.Collections;

import junit.framework.TestCase;

import org.easymock.MockControl;

import com.codecrate.shard.source.Source;
import com.codecrate.shard.source.SourceDao;
import com.codecrate.shard.source.SourceFactory;
import com.codecrate.shard.transfer.FileUtils;
import com.codecrate.shard.transfer.progress.NullProgressMonitor;

public class PcgenObjectImporterTest extends TestCase {

    public void testBasicImport() throws Exception {
        Source source = new Source("name", "nme", "http://blah.com");

        MockControl mockSourceDao = MockControl.createControl(SourceDao.class);
        SourceDao sourceDao = (SourceDao) mockSourceDao.getMock();
        sourceDao.getSource("System Reference Document");
        mockSourceDao.setReturnValue(null);
        sourceDao.saveSource(source);
        mockSourceDao.setReturnValue(source);
        mockSourceDao.replay();

        MockControl mockSourceFactory = MockControl.createControl(SourceFactory.class);
        SourceFactory sourceFactory = (SourceFactory) mockSourceFactory.getMock();
        sourceFactory.createSource("System Reference Document", "SRD", "http://groups.yahoo.com/group/pcgen/files/3.0%20SRD/");
        mockSourceFactory.setReturnValue(source);
        mockSourceFactory.replay();

        MockControl mockLineHanlder = MockControl.createControl(PcgenObjectImporter.PcgenLineHandler.class);
        PcgenObjectImporter.PcgenLineHandler lineHandler = (PcgenObjectImporter.PcgenLineHandler) mockLineHanlder.getMock();
        lineHandler.handleLine("Climb	KEYSTAT:STR", source);
        mockLineHanlder.setReturnValue(new Object());
        lineHandler.handleLine("Jump	KEYSTAT:STR", source);
        mockLineHanlder.setReturnValue(new Object());
        mockLineHanlder.replay();

        PcgenSourceLineHandler sourceLineHandler = new PcgenSourceLineHandler(sourceDao, sourceFactory);

        File file = FileUtils.getFile("pcgen/pcgen.lst");
        PcgenObjectImporter importer = new PcgenObjectImporter(lineHandler, sourceLineHandler);
		Collection results = importer.importObjects(file, new NullProgressMonitor());

		assertFalse(results.isEmpty());
        assertEquals(2, results.size());
    }

    public void testExceptionHandlingRowDoesKeepsGoing() throws Exception {
        Source source = new Source("name", "nme", "http://blah.com");

        MockControl mockSourceDao = MockControl.createControl(SourceDao.class);
        SourceDao sourceDao = (SourceDao) mockSourceDao.getMock();
        sourceDao.getSource("System Reference Document");
        mockSourceDao.setReturnValue(null);
        sourceDao.saveSource(source);
        mockSourceDao.setReturnValue(source);
        mockSourceDao.replay();

        MockControl mockSourceFactory = MockControl.createControl(SourceFactory.class);
        SourceFactory sourceFactory = (SourceFactory) mockSourceFactory.getMock();
        sourceFactory.createSource("System Reference Document", "SRD", "http://groups.yahoo.com/group/pcgen/files/3.0%20SRD/");
        mockSourceFactory.setReturnValue(source);
        mockSourceFactory.replay();

        MockControl mockLineHanlder = MockControl.createControl(PcgenObjectImporter.PcgenLineHandler.class);
        PcgenObjectImporter.PcgenLineHandler lineHandler = (PcgenObjectImporter.PcgenLineHandler) mockLineHanlder.getMock();
        lineHandler.handleLine("Climb	KEYSTAT:STR", source);
        mockLineHanlder.setThrowable(new IllegalArgumentException("Test error while parsing"));
        lineHandler.handleLine("Jump	KEYSTAT:STR", source);
        mockLineHanlder.setReturnValue(new Object());
        mockLineHanlder.replay();

        PcgenSourceLineHandler sourceLineHandler = new PcgenSourceLineHandler(sourceDao, sourceFactory);

        File file = FileUtils.getFile("pcgen/pcgen.lst");
        PcgenObjectImporter importer = new PcgenObjectImporter(lineHandler, sourceLineHandler);
        Collection results = importer.importObjects(file, new NullProgressMonitor());

        assertFalse(results.isEmpty());
        assertEquals(1, results.size());
    }

    public void testAllAvailableDatasetsReturnedFromTheClassPath() {
        PcgenDatasetImporter importer = new PcgenDatasetImporter(Collections.EMPTY_MAP);

        assertFalse(importer.getAvailableDatasets().isEmpty());
    }
}
