package com.codecrate.shard.character;

import junit.framework.TestCase;

public class AlignmentTest extends TestCase {

	public void testAlignmentGrabsFirstCharacterFromEachWord() {
		assertEquals("LG", Alignment.LAWFUL_GOOD.getAbbreviation());
	}
	
	public void testTrueNeutralReturnsNForAbbreviation() {
		assertEquals("N", Alignment.UNALIGNED.getAbbreviation());
	}
}
