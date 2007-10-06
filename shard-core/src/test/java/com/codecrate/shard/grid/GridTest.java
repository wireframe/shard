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

	public void testFindPath() {
		Grid grid = new Grid(2, 2);
		
		GridSquare start = grid.getSquare(0, 0);
		GridSquare end = grid.getSquare(1, 1);
		grid.pathBetween(start, end);
	}
	
	public void testFindBigPath() {
		Grid grid = new Grid(5, 5);
		
		GridSquare start = grid.getSquare(1, 1);
		GridSquare end = grid.getSquare(4, 2);
		grid.pathBetween(start, end);
	}
}
