package com.codecrate.shard.grid;

import junit.framework.TestCase;

public class LocationTest extends TestCase {

	public void testDirectionToRightLocationIsRight() {
		assertEquals(Direction.RIGHT, new Location(0, 0).directionTo(new Location(1, 0)));
	}
}
