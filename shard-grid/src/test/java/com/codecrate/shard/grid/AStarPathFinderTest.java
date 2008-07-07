package com.codecrate.shard.grid;

import junit.framework.TestCase;

public class AStarPathFinderTest extends TestCase {

	public void testFindShortestPath() {
		Grid grid = new Grid(3, 3);

		GridSquare start = grid.getSquare(new Location(0, 0));
		GridSquare end = grid.getSquare(new Location(2, 1));

		grid.getSquare(new Location(1, 1)).toggle();
		
		AStarPathFinder finder = new AStarPathFinder(grid, 5);
		Path path = finder.findPathBetween(start, end);
		assertEquals(grid.getSquare(new Location(1, 0)), path.getGridSquares().get(0));
		assertEquals(grid.getSquare(new Location(2, 1)), path.getGridSquares().get(1));
	}

	public void testAvoidsDifficultTerrainIfCausesLongerPath() {
		Grid grid = new Grid(3, 3);

		GridSquare start = grid.getSquare(new Location(0, 0));
		GridSquare end = grid.getSquare(new Location(2, 2));

		grid.getSquare(new Location(1, 1)).toggle();
		grid.getSquare(new Location(1, 0)).setDifficultTerrain(true);
		grid.getSquare(new Location(2, 0)).setDifficultTerrain(true);
		
		AStarPathFinder finder = new AStarPathFinder(grid, 5);
		Path path = finder.findPathBetween(start, end);
		assertEquals(grid.getSquare(new Location(0, 1)), path.getGridSquares().get(0));
		assertEquals(grid.getSquare(new Location(1, 2)), path.getGridSquares().get(1));
		assertEquals(grid.getSquare(new Location(2, 2)), path.getGridSquares().get(2));
	}
	
	public void testThrowsExceptionIfUnableToFindPath() {
		Grid grid = new Grid(3, 3);

		GridSquare start = grid.getSquare(new Location(0, 0));
		GridSquare end = grid.getSquare(new Location(2, 1));

    grid.getSquare(new Location(1, 0)).toggle();
		grid.getSquare(new Location(1, 1)).toggle();
    grid.getSquare(new Location(1, 2)).toggle();
		
		
		AStarPathFinder finder = new AStarPathFinder(grid, 5);
		try {
  		Path path = finder.findPathBetween(start, end);
  		fail();
		} catch (PathNotFoundException expected) {}
	}
}
