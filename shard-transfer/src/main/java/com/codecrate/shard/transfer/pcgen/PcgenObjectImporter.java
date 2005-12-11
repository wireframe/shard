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
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.codecrate.shard.transfer.ObjectImporter;

public class PcgenObjectImporter implements ObjectImporter {
	private static final String PCGEN_LST_FILE_EXTENSION = "lst";
    private static final String SOURCE_NAME_TAG_NAME = "SOURCELONG";
    private static final String SOURCE_ABBREVIATION_TAG_NAME = "SOURCESHORT";
    private static final String SOURCE_URL_TAG_NAME = "SOURCEWEB";

    private static final Log LOG = LogFactory.getLog(PcgenObjectImporter.class);

    private final PcgenLineHandler lineHandler;

    public PcgenObjectImporter(PcgenLineHandler lineHandler) {
        this.lineHandler = lineHandler;
    }


    public Collection getSupportedFileExtensions() {
        return Collections.singletonList(PCGEN_LST_FILE_EXTENSION);
    }

    public Collection importObjects(File file) {
        Collection results = new ArrayList();
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

            while (reader.ready()) {
			    String line = reader.readLine();
                if (isSourceLine(line)) {
                    handleSourceLine(line);
                } else if (!isEmptyLine(line)) {
			    	try {
			            results.add(handleLine(line));
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

    private void handleSourceLine(String line) {
        Map tags = new HashMap();
        StringTokenizer tokens = new StringTokenizer(line, "|");
        while (tokens.hasMoreTokens()) {
            String token = tokens.nextToken();
            int colonIndex = token.indexOf(":");
            String tagName = token.substring(0, colonIndex);
            String tagValue = token.substring(colonIndex + 1, token.length());

            tags.put(tagName, tagValue);
        }

        String name = (String) tags.get(SOURCE_NAME_TAG_NAME);
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

	private Object handleLine(String line) {
        StringTokenizer tokens = new StringTokenizer(line, "\t");

        String name = tokens.nextToken().trim();
        Map tags = new HashMap();

        while (tokens.hasMoreTokens()) {
            String token = tokens.nextToken().trim();

            String[] splitTags = token.split(":");
            String tagName = splitTags[0].trim();
            String tagValue = splitTags[1].trim();

            tags.put(tagName, tagValue);
        }

        return lineHandler.handleParsedLine(name, tags);
    }

    private boolean isSourceLine(String value) {
        return value.startsWith("SOURCE");
    }

    private boolean isEmptyLine(String value) {
        return (null == value || value.trim().length() < 1  || value.startsWith("#"));
    }


    public interface PcgenLineHandler {

        Object handleParsedLine(String name, Map tags);
    }
}
