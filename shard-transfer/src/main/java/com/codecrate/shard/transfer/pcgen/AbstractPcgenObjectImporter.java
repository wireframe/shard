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
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.codecrate.shard.transfer.ObjectImporter;

public abstract class AbstractPcgenObjectImporter implements ObjectImporter {
	private static final Log LOG = LogFactory.getLog(AbstractPcgenObjectImporter.class);
	
	private static final String TRUE_TAG_VALUE = "YES";
	
    public Collection importObjects(File file) {
        Collection results = new ArrayList();
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

            while (reader.ready()) {
			    String line = reader.readLine();
			    if (isUsableLine(line)) {
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

        return handleParsedLine(name, tags);
    }

    protected abstract Object handleParsedLine(String name, Map tags);

    protected String getStringTagValue(String tagName, Map tags) {
    	String value = (String) tags.get(tagName);
    	if (null == value) {
    		LOG.info("No value found for tag " + tagName);
    	}
    	return value;
	}

    protected boolean getBooleanTagValue(String tagName, Map tags, boolean defaultValue) {
    	String value = (String) tags.get(tagName);
    	if (null == value) {
    		LOG.info("No value found for tag " + tagName + " defaulting to " + defaultValue);
    		return defaultValue;
    	}
    	return (TRUE_TAG_VALUE.equals(value));
	}

	private boolean isUsableLine(String value) {
        return (!isEmpty(value) && !value.startsWith("SOURCE"));
    }

    private boolean isEmpty(String value) {
        return (null == value || value.trim().length() < 1  || value.startsWith("#"));
    }
}
