package com.codecrate.shard.grid;

import junit.framework.TestCase;

import java.util.Arrays;

public class CheckpointPathFinderTest extends TestCase {

	public void testFindSimplePathWithCheckpoints() {
		Grid grid = new Grid(3, 3);
		
		GridSquare start = grid.getSquare(new Location(0, 0));
		GridSquare end = grid.getSquare(new Location(2, 2));
		
		CheckpointPathFinder finder = new CheckpointPathFinder(Arrays.asList(new GridSquare[] {grid.getSquare(new Location(0, 2))}));
		Path path = finder.findPathBetween(grid, start, end);
		assertEquals(grid.getSquare(new Location(0, 1)), path.getGridSquares().get(0));
		assertEquals(grid.getSquare(new Location(0, 2)), path.getGridSquares().get(1));
		assertEquals(grid.getSquare(new Location(1, 2)), path.getGridSquares().get(2));
		assertEquals(grid.getSquare(new Location(2, 2)), path.getGridSquares().get(3));
	}
}
