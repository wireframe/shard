package com.codecrate.shard.grid;

import junit.framework.TestCase;

public class TokenTest extends TestCase {

	public void testMovingTokenToBlockedSquareThrowsException() {
		Token token = new Token();
		
		Grid grid = new Grid(2, 2);
		GridSquare start = grid.getSquare(Location.ORIGIN);
		token.place(start);
		
		grid.getSquare(new Location(1, 0)).toggle();
		Path path = new Path(start);
		path.addStep(Direction.RIGHT);
		try {
			token.move(path);
			fail("should throw exception");
		} catch (IllegalArgumentException expected) {}
	}
}
