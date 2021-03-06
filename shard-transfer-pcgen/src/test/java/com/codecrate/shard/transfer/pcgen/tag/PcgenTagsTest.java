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

import junit.framework.TestCase;

public class PcgenTagsTest extends TestCase {

	public void testTagsSeperatedByTokenAreConsideredSeperateTags() {
		PcgenTags tags = new PcgenTags("NAME:Thor \t EYES:blue");

		assertTrue(tags.hasTag("NAME"));
		assertTrue(tags.hasTag("EYES"));
	}

	public void testTagValueIsValueAfterDelimiter() {
		PcgenTags tags = new PcgenTags("NAME:Thor");

		assertEquals("Thor", tags.getStringTagValue("NAME"));
	}

	public void testUrlWithColonCanBeUsedAsTagValue() {
		PcgenTags tags = new PcgenTags("URL:http://blah.com");

		assertEquals("http://blah.com", tags.getStringTagValue("URL"));
	}

	public void testCreatingTagsWithLeadingNameGrabsOtherTags() {
		PcgenTags tags = new PcgenTags("Thor The Alighty \t URL:http://blah.com");

		assertEquals("http://blah.com", tags.getStringTagValue("URL"));
	}

	public void testUndefinedTagValueIsSaved() {
		PcgenTags tags = new PcgenTags("Thor The Alighty \t URL:http://blah.com");

		assertEquals("Thor The Alighty", tags.getUndefinedTagValue());
	}

	public void testTagValueCanBeFoundFromAggregatedContent() {
		PcgenTags tags = new PcgenTags("Thor The Alighty \t BONUS:CHECKS|BASE.Reflex,BASE.Will|CL/3	BONUS:COMBAT|BAB|CL|TYPE=Base.REPLACE");

		assertEquals("CL", tags.getTagValueAfterElement("BONUS", "BAB"));
	}

}
