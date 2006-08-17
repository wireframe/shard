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
package com.codecrate.shard.transfer.pcgen.tag;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PcgenTags {
    private static final Log LOG = LogFactory.getLog(PcgenTags.class);
	private static final String TAG_VALUE_DELIMITER = "|";
	private static final String TAG_SEPERATOR = "\t";
    private static final String TAG_NAME_VALUE_SEPERATOR = ":";
    private static final String TRUE_TAG_VALUE = "YES";

    private final ConcatTagValueAggregator tagValueAggregator = new ConcatTagValueAggregator(TAG_VALUE_DELIMITER);
    private final Map tags = new HashMap();
	private String undefinedTagValue;

	public PcgenTags(String line) {
		StringTokenizer tokens = new StringTokenizer(line, TAG_SEPERATOR);
		while (tokens.hasMoreTokens()) {
			String token = tokens.nextToken();
			int seperatorIndex = token.indexOf(TAG_NAME_VALUE_SEPERATOR);
			if (-1 == seperatorIndex) {
				this.undefinedTagValue = token.trim();
			} else {
				String tagName = token.substring(0, seperatorIndex).trim();
				String tagValue = token.substring(seperatorIndex + 1, token.length()).trim();

				addTagValue(tagName, tagValue);
			}
		}
	}

	private void addTagValue(String name, String value) {
		String oldValue = (String) tags.get(name);
        String newValue = tagValueAggregator.aggregateValue(oldValue, value);

		tags.put(name, newValue);
	}

	public String getStringTagValue(String tagName) {
        return getStringTagValue(tagName, null);
    }

    public String getStringTagValue(String tagName, String defaultValue) {
        String value = (String) tags.get(tagName);
        if (null == value) {
            LOG.debug("No value found for tag " + tagName);
            return defaultValue;
        }
        return value;
    }

    public int getIntTagValue(String tagName) {
        return getIntTagValue(tagName, 0);
    }

    public int getIntTagValue(String tagName, int defaultValue) {
        String value = (String) tags.get(tagName);
        if (null == value) {
            LOG.debug("No value found for tag " + tagName + " defaulting to " + defaultValue);
            return defaultValue;
        }
        return Integer.parseInt(value);
    }

    public boolean getBooleanTagValue(String tagName, boolean defaultValue) {
        String value = (String) tags.get(tagName);
        if (null == value) {
            LOG.debug("No value found for tag " + tagName + " defaulting to " + defaultValue);
            return defaultValue;
        }
        return (TRUE_TAG_VALUE.equals(value));
    }


	public boolean hasTag(String tagName) {
		return (null != tags.get(tagName));
	}

	public String getUndefinedTagValue() {
		return undefinedTagValue;
	}

    public String getTagValueAfterElement(String tagName, String expression) {
        StringTokenizer tokens = getTagValues(tagName);
        while (tokens.hasMoreTokens()) {
			String token = tokens.nextToken();
			if (token.indexOf(expression) != -1 && tokens.hasMoreTokens()) {
				return tokens.nextToken();
			}
		}
        return null;
    }

	public StringTokenizer getTagValues(String tagName) {
		String value = getStringTagValue(tagName);
		return tagValueAggregator.parseAggregatedValue(value);
	}
}
