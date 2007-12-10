package com.codecrate.shard.grid;

import junit.framework.TestCase;

public class DirectPathFinderTest extends TestCase {

	public void testDiagonalPathIsUsed() {
		Grid grid = new Grid(2, 2);
		GridSquare start = grid.getSquare(new Location(0, 0));
		GridSquare end = grid.getSquare(new Location(1, 1));
		
		DirectPathFinder finder = new DirectPathFinder();
		Path path = finder.findPathBetween(grid, start, end);
		assertTrue(path.getGridSquares().contains(grid.getSquare(new Location(1, 1))));
	}
	
	public void testDiagonalPathIsUsedFirstAndThenAlongTheCorrectAxis() {
		Grid grid = new Grid(3, 3);
		GridSquare start = grid.getSquare(new Location(0, 0));
		GridSquare end = grid.getSquare(new Location(2, 1));
		
		DirectPathFinder finder = new DirectPathFinder();
		Path path = finder.findPathBetween(grid, start, end);
		assertTrue(path.getGridSquares().contains(grid.getSquare(new Location(1, 1))));
		assertTrue(path.getGridSquares().contains(grid.getSquare(new Location(2, 1))));
	}
}
