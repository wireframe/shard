package com.codecrate.shard.grid;

import junit.framework.TestCase;

public class MazePathFinderTest extends TestCase {

	public void testFindSimplePath() {
		Grid grid = new Grid(2, 2);
		
		GridSquare start = grid.getSquare(new Location(0, 0));
		GridSquare end = grid.getSquare(new Location(1, 0));
		
		MazePathFinder finder = new MazePathFinder(grid);
		Path path = finder.findPathBetween(start, end);
		assertEquals(end, path.getGridSquares().get(0));
	}
	
	public void testFindMazePath() {
		Grid grid = new Grid(3, 3);

		GridSquare start = grid.getSquare(new Location(0, 0));
		GridSquare end = grid.getSquare(new Location(2, 0));

		grid.getSquare(new Location(1, 0)).toggle();
		grid.getSquare(new Location(1, 1)).toggle();
		
		MazePathFinder finder = new MazePathFinder(grid);
		Path path = finder.findPathBetween(start, end);
		assertEquals(grid.getSquare(new Location(0, 1)), path.getGridSquares().get(0));
		assertEquals(grid.getSquare(new Location(0, 2)), path.getGridSquares().get(1));
		assertEquals(grid.getSquare(new Location(1, 2)), path.getGridSquares().get(2));
		assertEquals(grid.getSquare(new Location(2, 2)), path.getGridSquares().get(3));
		assertEquals(grid.getSquare(new Location(2, 1)), path.getGridSquares().get(4));
		assertEquals(grid.getSquare(new Location(2, 0)), path.getGridSquares().get(5));
	}
}
