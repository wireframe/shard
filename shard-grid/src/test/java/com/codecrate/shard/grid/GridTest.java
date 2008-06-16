package com.codecrate.shard.grid;

import java.util.Collection;

import junit.framework.TestCase;

public class GridTest extends TestCase {

	public void testErrorOccursWhenAccessingSquareNotOnGrid() {
		Grid grid = new Grid(2, 2);
		try {
			grid.getSquare(new Location(5, 5));
			fail("expected failure");
		} catch(IllegalArgumentException expected) {}
	}
	
	public void testRowOneContainsSquaresWithYEqualToOne() {
		Grid grid = new Grid(2, 2);
		Collection<GridSquare> row = grid.row(1);
		assertEquals(grid.getSquare(new Location(0, 1)), row.iterator().next());
	}
}
