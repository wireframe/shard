package com.codecrate.shard.grid;

import junit.framework.TestCase;

public class MazePathFinderTest extends TestCase {

	public void testFindPath() {
		Grid grid = new Grid(2, 2);
		
		GridSquare start = grid.getSquare(new Location(0, 0));
		GridSquare end = grid.getSquare(new Location(1, 1));
		
		MazePathFinder finder = new MazePathFinder();
		finder.findPathBetween(grid, start, end);
	}
}
