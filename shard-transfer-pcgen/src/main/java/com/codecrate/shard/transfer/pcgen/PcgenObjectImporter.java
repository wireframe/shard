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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.codecrate.shard.source.Source;
import com.codecrate.shard.transfer.ObjectImporter;
import com.codecrate.shard.transfer.progress.ProgressMonitor;

public class PcgenObjectImporter implements ObjectImporter {
	private static final String PCGEN_LST_FILE_EXTENSION = "lst";

    private static final Log LOG = LogFactory.getLog(PcgenObjectImporter.class);

    private final PcgenLineHandler lineHandler;
    private final PcgenSourceLineHandler sourceLineHandler;

    public PcgenObjectImporter(PcgenLineHandler lineHandler, PcgenSourceLineHandler sourceLineHandler) {
        this.lineHandler = lineHandler;
        this.sourceLineHandler = sourceLineHandler;
    }

    public Collection getSupportedFileExtensions() {
        return Collections.singletonList(PCGEN_LST_FILE_EXTENSION);
    }

    public boolean isDirectoryImportSupported() {
        return false;
    }

    public Collection importObjects(File file, ProgressMonitor progress) {
        Collection results = new ArrayList();
        BufferedReader reader = null;
        Source source = null;

        progress.startTask("Importing pcgen objects from " + file.getName(), getNumberOfFileLines(file));
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

            while (reader.ready() && !progress.isCancelled()) {
			    String line = reader.readLine().trim();
                if (sourceLineHandler.isSourceLine(line)) {
                    source = (Source) sourceLineHandler.handleLine(line, source);
                } else if (!isEmptyLine(line)) {
			    	try {
			            results.add(lineHandler.handleLine(line, source));
			    	} catch (Exception e) {
			    		LOG.error("Error processing line: " + line, e);
			    	}
			    }

                progress.completeUnitOfWork();
			}
		} catch (IOException e) {
			LOG.error("Error importing file: " + file, e);
		} finally {
			closeReader(reader);
		}

		progress.finish();

        return results;
    }

    private int getNumberOfFileLines(File file) {
    	int lines = 0;
    	try {
    		long lastByte = file.length();
    		LineNumberReader lineRead = new LineNumberReader(new FileReader(file));
    		lineRead.skip(lastByte);
    		lines = lineRead.getLineNumber() - 1;
    		lineRead.close();
    	} catch(IOException e) {
    		LOG.warn("Error counting the number of lines in file: " + file, e);
    	}
    	return lines;
    }

    private void closeReader(BufferedReader reader) {
    	if (null != reader) {
    		try {
    			reader.close();
    		} catch (IOException e) {
    			LOG.warn("Error closing input stream: " + reader);
    		}
    	}
    }

    private boolean isEmptyLine(String value) {
        return (null == value || value.length() < 1  || value.startsWith("#") || -1 != value.indexOf(".MOD"));
    }


    public interface PcgenLineHandler {

    	Object handleLine(String line, Source source);
    }
}
