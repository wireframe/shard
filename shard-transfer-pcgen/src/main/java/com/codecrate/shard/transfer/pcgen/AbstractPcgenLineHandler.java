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

import java.util.StringTokenizer;

import com.codecrate.shard.source.Source;
import com.codecrate.shard.transfer.pcgen.tag.NoOpTagValueAggregator;
import com.codecrate.shard.transfer.pcgen.tag.PcgenTags;
import com.codecrate.shard.transfer.pcgen.tag.TagValueAggregator;

/**
 * helper class for parsing PCGen LST files.
 */
public abstract class AbstractPcgenLineHandler implements PcgenObjectImporter.PcgenLineHandler {
	private final TagValueAggregator tagValueAggregator;

	public AbstractPcgenLineHandler() {
		this(new NoOpTagValueAggregator());
	}

	public AbstractPcgenLineHandler(TagValueAggregator tagValueAggregator) {
		this.tagValueAggregator = tagValueAggregator;
	}

	public final Object handleLine(String line, Source source) {
        String name = getNameToken(line);

        PcgenTags tags = new PcgenTags(line, tagValueAggregator);

        return handleParsedLine(name, tags, source);
    }

	protected abstract Object handleParsedLine(String name, PcgenTags tags, Source source);

	/**
	 * extension point for subclasses to override where to get the name from.
	 * @param line
	 * @return
	 */
	protected String getNameToken(String line) {
        StringTokenizer tokens = new StringTokenizer(line, "\t");
        return tokens.nextToken().trim();
	}
}
