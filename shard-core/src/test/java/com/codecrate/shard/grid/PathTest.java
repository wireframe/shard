package com.codecrate.shard.grid;

import junit.framework.TestCase;

public class PathTest extends TestCase {

	public void testLengthOfPathStartsAtZero() {
		Grid grid = new Grid(2, 2);
		GridSquare start = grid.getSquare(new Location(0, 0));
		Path path = new Path(start);
		
		assertEquals(0, path.getLength());
	}

	public void testLengthOfPathIncreasesWhenMovingRight() {
		Grid grid = new Grid(2, 2);
		GridSquare start = grid.getSquare(new Location(0, 0));
		Path path = new Path(start);
		path.addStep(Direction.RIGHT);
		
		assertEquals(5, path.getLength());
	}

	public void testLengthOfPathIncreasesWhenMovingLeft() {
		Grid grid = new Grid(2, 2);
		GridSquare start = grid.getSquare(new Location(1, 0));
		Path path = new Path(start);
		path.addStep(Direction.LEFT);
		
		assertEquals(5, path.getLength());
	}

	public void testLengthOfPathIncreasesWhenMovingDiagonal() {
		Grid grid = new Grid(2, 2);
		GridSquare start = grid.getSquare(new Location(1, 0));
		Path path = new Path(start);
		path.addStep(Direction.DOWN_LEFT);
		
		assertEquals(5, path.getLength());
	}

	public void testLengthOfPathIncreasesWhenMovingDiagonalAndRight() {
		Grid grid = new Grid(3, 3);
		GridSquare start = grid.getSquare(new Location(1, 0));

		Path path = new Path(start);
		path.addStep(Direction.DOWN_LEFT);
		path.addStep(Direction.RIGHT);
		
		assertEquals(10, path.getLength());
	}

	public void testExtraFiveFeetAddedEveryOtherDiagonalMove() {
		Grid grid = new Grid(3, 3);
		GridSquare start = grid.getSquare(new Location(0, 0));
		Path path = new Path(start);
		path.addStep(Direction.DOWN_RIGHT);
		path.addStep(Direction.DOWN_RIGHT);
		
		assertEquals(15, path.getLength());
	}
}
