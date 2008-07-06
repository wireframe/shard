package com.codecrate.shard.grid;

import junit.framework.TestCase;

public class DirectPathFinderTest extends TestCase {

	public void testDiagonalPathIsUsed() {
		Grid grid = new Grid(2, 2);
		GridSquare start = grid.getSquare(new Location(0, 0));
		GridSquare end = grid.getSquare(new Location(1, 1));
		
		DirectPathFinder finder = new DirectPathFinder();
		Path path = finder.findPathBetween(start, end);
		assertTrue(path.getGridSquares().contains(grid.getSquare(new Location(1, 1))));
	}
	
	public void testDiagonalPathIsUsedFirstAndThenAlongTheCorrectAxis() {
		Grid grid = new Grid(3, 3);
		GridSquare start = grid.getSquare(new Location(0, 0));
		GridSquare end = grid.getSquare(new Location(2, 1));
		
		DirectPathFinder finder = new DirectPathFinder();
		Path path = finder.findPathBetween(start, end);
		assertTrue(path.getGridSquares().contains(grid.getSquare(new Location(1, 1))));
		assertTrue(path.getGridSquares().contains(grid.getSquare(new Location(2, 1))));
	}
	
	public void testExceptionThrownIfBlockedSquareIsInThePath() {
		Grid grid = new Grid(3, 3);
		GridSquare start = grid.getSquare(new Location(0, 0));
		GridSquare end = grid.getSquare(new Location(2, 0));

   grid.getSquare(new Location(1, 0)).toggle();
  		
		DirectPathFinder finder = new DirectPathFinder();
		try {
  		Path path = finder.findPathBetween(start, end);
		  fail();
		} catch (PathNotFoundException expected) {}
	}
}
