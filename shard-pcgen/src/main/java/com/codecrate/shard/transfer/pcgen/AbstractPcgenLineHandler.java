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

import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.codecrate.shard.source.Source;
import com.codecrate.shard.transfer.pcgen.tag.PcgenTagParser;
import com.codecrate.shard.transfer.pcgen.tag.PcgenTokenTagParser;

/**
 * helper class for parsing PCGen LST files.
 */
public abstract class AbstractPcgenLineHandler implements PcgenObjectImporter.PcgenLineHandler {
    private static final Log LOG = LogFactory.getLog(AbstractPcgenLineHandler.class);

    private static final String TRUE_TAG_VALUE = "YES";

	private final PcgenTagParser tagParser;

	public AbstractPcgenLineHandler() {
		this(new PcgenTokenTagParser("\t"));
	}

    public AbstractPcgenLineHandler(PcgenTagParser tagParser) {
		this.tagParser = tagParser;
    }

	public Object handleLine(String line, Source source) {
        String name = getNameToken(line);
        String tagsLine = getTagsFromLine(line);

        Map tags = tagParser.parseTags(tagsLine);

        return handleParsedLine(name, tags, source);
    }

	protected abstract Object handleParsedLine(String name, Map tags, Source source);

	private String getNameToken(String line) {
        StringTokenizer tokens = new StringTokenizer(line, "\t");
        return tokens.nextToken().trim();
	}

	private String getTagsFromLine(String line) {
		String name = getNameToken(line);
		return line.substring(name.length() + 1);
	}

    protected String getStringTagValue(String tagName, Map tags) {
        String value = (String) tags.get(tagName);
        if (null == value) {
            LOG.debug("No value found for tag " + tagName);
        }
        return value;
    }

    protected int getIntTagValue(String tagName, Map tags) {
        return getIntTagValue(tagName, tags, 0);
    }

    protected int getIntTagValue(String tagName, Map tags, int defaultValue) {
        String value = (String) tags.get(tagName);
        if (null == value) {
            LOG.debug("No value found for tag " + tagName + " defaulting to " + defaultValue);
            return defaultValue;
        }
        return Integer.parseInt(value);
    }


    protected PcgenTagParser getTagParser() {
    	return tagParser;
    }

    protected boolean getBooleanTagValue(String tagName, Map tags, boolean defaultValue) {
        String value = (String) tags.get(tagName);
        if (null == value) {
            LOG.debug("No value found for tag " + tagName + " defaulting to " + defaultValue);
            return defaultValue;
        }
        return (TRUE_TAG_VALUE.equals(value));
    }
}
