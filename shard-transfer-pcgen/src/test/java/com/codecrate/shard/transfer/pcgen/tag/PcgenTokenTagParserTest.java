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

import java.util.Map;

import junit.framework.TestCase;

public class PcgenTokenTagParserTest extends TestCase {

	public void testTagsSeperatedByTokenAreMappedCorrectly() {
		Map tags = new PcgenTokenTagParser().parseTags("NAME:Thor \t EYES:blue");

		assertEquals(2, tags.size());
	}

	public void testTagValueIsValueAfterDelimiter() {
		Map tags = new PcgenTokenTagParser().parseTags("NAME:Thor");

		assertEquals("Thor", tags.get("NAME"));
	}

	public void testUrlWithColonCanBeUsedAsTagValue() {
		Map tags = new PcgenTokenTagParser().parseTags("URL:http://blah.com");

		assertEquals("http://blah.com", tags.get("URL"));
	}
}
