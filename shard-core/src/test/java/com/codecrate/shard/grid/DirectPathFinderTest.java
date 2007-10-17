package com.codecrate.shard.grid;

import java.util.Collection;

import junit.framework.TestCase;

public class DirectPathFinderTest extends TestCase {

	public void testDiagonalPathIsUsed() {
		Grid grid = new Grid(2, 2);
		GridSquare start = grid.getSquare(0, 0);
		GridSquare end = grid.getSquare(1, 1);
		
		DirectPathFinder finder = new DirectPathFinder();
		Collection<GridSquare> path = finder.findPathBetween(grid, start, end);
		assertTrue(path.contains(grid.getSquare(1, 1)));
	}
	
	public void testDiagonalPathIsUsedFirstAndThenAlongTheCorrectAxis() {
		Grid grid = new Grid(3, 3);
		GridSquare start = grid.getSquare(0, 0);
		GridSquare end = grid.getSquare(2, 1);
		
		DirectPathFinder finder = new DirectPathFinder();
		Collection<GridSquare> path = finder.findPathBetween(grid, start, end);
		assertTrue(path.contains(grid.getSquare(1, 1)));
		assertTrue(path.contains(grid.getSquare(2, 1)));
	}
}
