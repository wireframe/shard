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

public class PcgenTokenTagParser implements PcgenTagParser {
	private final String tokenDelimiter;
	private final String tagSeperator;

	public PcgenTokenTagParser(String tokenDelimiter) {
		this(tokenDelimiter, ":");
	}

	public PcgenTokenTagParser(String tokenDelimiter, String tagSeperator) {
		this.tokenDelimiter = tokenDelimiter;
		this.tagSeperator = tagSeperator;
	}

	public Map parseTags(String line) {
		Map tags = new HashMap();
		StringTokenizer tokens = new StringTokenizer(line, tokenDelimiter);
		while (tokens.hasMoreTokens()) {
			String token = tokens.nextToken();
			int seperatorIndex = token.indexOf(tagSeperator);
			String tagName = token.substring(0, seperatorIndex);
			String tagValue = token.substring(seperatorIndex + 1, token.length());
			
			tags.put(tagName, tagValue);
		}
		return tags;
	}
	
}
