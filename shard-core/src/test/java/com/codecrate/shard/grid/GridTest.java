package com.codecrate.shard.grid;

import junit.framework.TestCase;

public class GridTest extends TestCase {

	public void testErrorOccursWhenAccessingSquareNotOnGrid() {
		Grid grid = new Grid(2, 2);
		try {
			grid.getSquare(5, 5);
			fail("expected failure");
		} catch(IllegalArgumentException expected) {}
	}
}
