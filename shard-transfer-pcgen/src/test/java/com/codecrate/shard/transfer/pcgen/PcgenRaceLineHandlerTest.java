package com.codecrate.shard.transfer.pcgen;

import junit.framework.TestCase;

public class PcgenRaceLineHandlerTest extends TestCase {

	public void testNameWithColonIsFilteredOut() {
		PcgenRaceLineHandler handler = new PcgenRaceLineHandler(null, null, null);

		assertNull(handler.handleLine("NAME:FOO \t VALUE:BAR", null));
	}
}
