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

public class PcgenTagParser {
	private static final String TAG_SEPERATOR = "\t";
    private static final String TAG_NAME_VALUE_SEPERATOR = ":";
    private static final Log LOG = LogFactory.getLog(PcgenTagParser.class);
    private final TagValueAggregator tagValueAggregator;

	public PcgenTagParser() {
		this(new NoOpTagValueAggregator());
	}

    public PcgenTagParser(TagValueAggregator tagValueAggregator) {
        this.tagValueAggregator = tagValueAggregator;
	}

	public Map parseTags(String line) {
		Map tags = new HashMap();
		StringTokenizer tokens = new StringTokenizer(line, TAG_SEPERATOR);
		while (tokens.hasMoreTokens()) {
			String token = tokens.nextToken();
			int seperatorIndex = token.indexOf(TAG_NAME_VALUE_SEPERATOR);
			String tagName = token.substring(0, seperatorIndex);
			String tagValue = token.substring(seperatorIndex + 1, token.length());

            if (null != tags.get(tagName)) {
                String oldValue = (String) tags.get(tagName);
                tagValue = tagValueAggregator.aggregateValue(oldValue, tagValue);
                LOG.debug("A value is already defined for tag [" + tagName + "].  New tag value is: " + tagValue);
            }
			tags.put(tagName, tagValue);
		}
		return tags;
	}

}
