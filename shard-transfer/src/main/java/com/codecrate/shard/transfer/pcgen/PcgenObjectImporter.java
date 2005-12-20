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
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.codecrate.shard.source.Source;
import com.codecrate.shard.transfer.ObjectImporter;

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

    public Collection importObjects(File file) {
        Collection results = new ArrayList();
        BufferedReader reader = null;
        Source source = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

            while (reader.ready()) {
			    String line = reader.readLine();
                if (sourceLineHandler.isSourceLine(line)) {
                    source = (Source) sourceLineHandler.handleLine(line, source);
                } else if (!isEmptyLine(line)) {
			    	try {
			            results.add(lineHandler.handleLine(line, source));
			    	} catch (Exception e) {
			    		LOG.error("Error processing line: " + line, e);
			    	}
			    }
			}
		} catch (IOException e) {
			LOG.error("Error importing file: " + file, e);
		} finally {
			closeReader(reader);
		}

        return results;
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
        return (null == value || value.trim().length() < 1  || value.startsWith("#"));
    }


    public interface PcgenLineHandler {

    	Object handleLine(String line, Source source);
    }
}
