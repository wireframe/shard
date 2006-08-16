package com.codecrate.shard.transfer.pcgen;

import junit.framework.TestCase;

public class PcgenCharacterClassLineHandlerTest extends TestCase {

	public void testCharacterClassNameExtractedFromClassTag() {
		PcgenCharacterClassLineHandler handler = new PcgenCharacterClassLineHandler(null, null, null);

		handler.handleLine("CLASS: My Kit", null);
	}
}
