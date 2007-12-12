package com.codecrate.shard.grid;

import junit.framework.TestCase;

public class TokenTest extends TestCase {

	public void testMovingTokenToBlockedSquareThrowsException() {
		Grid grid = new Grid(2, 2);
		grid.getSquare(new Location(1, 0)).toggle();
		GridSquare start = grid.getSquare(Location.ORIGIN);

		Token token = new Token();
		token.place(start);
		
		Path path = new Path(start);
		path.addStep(Direction.RIGHT);
		try {
			token.move(path);
			fail("should throw exception");
		} catch (IllegalArgumentException expected) {}
	}
	
	public void testCannotMoveAlongPathBeforeTokenHasBeenPlacedOnGrid() {
		Grid grid = new Grid(2, 2);
		GridSquare start = grid.getSquare(Location.ORIGIN);

		Token token = new Token();
		assertFalse(token.canMove(new Path(start)));
	}
}
