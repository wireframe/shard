package com.codecrate.shard.grid;

import junit.framework.TestCase;

public class PathTest extends TestCase {

	public void testLengthOfPathStartsAtZero() {
		Grid grid = new Grid(2, 2);
		GridSquare start = grid.getSquare(new Location(0, 0));
		Path path = new Path(start);
		
		assertEquals(0, path.getLength());
	}

	public void testLengthOfPathIncreasesAlongIncreasingXAxis() {
		Grid grid = new Grid(2, 2);
		GridSquare start = grid.getSquare(new Location(0, 0));
		Path path = new Path(start);
		path.addStep(grid.getSquare(new Location(1, 0)));
		
		assertEquals(5, path.getLength());
	}

	public void testLengthOfPathIncreasesAlongDecreasingXAxis() {
		Grid grid = new Grid(2, 2);
		GridSquare start = grid.getSquare(new Location(1, 0));
		Path path = new Path(start);
		path.addStep(grid.getSquare(new Location(0, 0)));
		
		assertEquals(5, path.getLength());
	}

	public void testLengthOfPathIncreasesAlongDiagonalAxis() {
		Grid grid = new Grid(2, 2);
		GridSquare start = grid.getSquare(new Location(1, 0));
		Path path = new Path(start);
		path.addStep(grid.getSquare(new Location(1, 1)));
		
		assertEquals(5, path.getLength());
	}

	public void testLengthOfPathIncreasesAlongDiagonalAndXAxis() {
		Grid grid = new Grid(3, 3);
		GridSquare start = grid.getSquare(new Location(1, 0));

		Path path = new Path(start);
		path.addStep(grid.getSquare(new Location(1, 1)));
		path.addStep(grid.getSquare(new Location(2, 1)));
		
		assertEquals(10, path.getLength());
	}

	public void testExtraFiveFeetAddedEveryOtherDiagonalMove() {
		Grid grid = new Grid(3, 3);
		GridSquare start = grid.getSquare(new Location(0, 0));
		Path path = new Path(start);
		path.addStep(grid.getSquare(new Location(1, 1)));
		path.addStep(grid.getSquare(new Location(2, 2)));
		
		assertEquals(15, path.getLength());
	}

}
