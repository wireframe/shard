package com.codecrate.shard.grid;

import junit.framework.TestCase;

public class PathTest extends TestCase {

	public void testLengthOfPathStartsAtZero() {
		Grid grid = new Grid(2, 2);
		GridSquare start = grid.getSquare(new Location(0, 0));
		Path path = new Path(start);
		
		assertEquals(0, path.getLength());
	}

	public void testLengthOfPathIncreasesWithEachStep() {
		Grid grid = new Grid(2, 2);
		GridSquare start = grid.getSquare(new Location(0, 0));
		Path path = new Path(start);
		path.addStep(Direction.RIGHT);
		path.addStep(Direction.DOWN);
		
		assertEquals(2, path.getLength());
	}

	public void testPathIsStraightWhenDoesNotChangeDirections() {
		Grid grid = new Grid(3, 3);
		GridSquare start = grid.getSquare(new Location(0, 0));
		Path path = new Path(start);
		path.addStep(Direction.DOWN_RIGHT);
		path.addStep(Direction.DOWN_RIGHT);

		assertTrue(path.isStraight());
	}

	public void testPathIsNotStraightWhenChangesDirections() {
		Grid grid = new Grid(3, 3);
		GridSquare start = grid.getSquare(new Location(0, 0));
		Path path = new Path(start);
		path.addStep(Direction.RIGHT);
		path.addStep(Direction.DOWN);

		assertFalse(path.isStraight());
	}
	
	public void testReversePath() {
		Grid grid = new Grid(3, 3);
		GridSquare start = grid.getSquare(new Location(0, 0));
		GridSquare end = grid.getSquare(new Location(1, 1));
		Path path = new Path(start);
		path.addStep(Direction.RIGHT);
		path.addStep(Direction.DOWN);

    Path reverse = path.reverse();
    assertEquals(2, reverse.getDirections().size());
    assertEquals(end, reverse.getStart());
    assertEquals(start, reverse.getDestination());
    assertEquals(Direction.UP, reverse.getDirections().get(0));
    assertEquals(Direction.LEFT, reverse.getDirections().get(1));
	}
}
