package com.codecrate.shard.grid;

import junit.framework.TestCase;

public class TokenTest extends TestCase {

	public void testMovingTokenToNonExistentSquareThrowsException() {
		Token token = new Token();
		
		Grid grid = new Grid(2, 2);
		token.place(grid.getSquare(Location.ORIGIN));
		
		try {
			token.move(Direction.UP);
			fail("should throw exception");
		} catch (IllegalArgumentException expected) {}
	}
	
	public void testMovingTokenToBlockedSquareThrowsException() {
		Token token = new Token();
		
		Grid grid = new Grid(2, 2);
		token.place(grid.getSquare(Location.ORIGIN));
		
		grid.getSquare(new Location(1, 0)).toggle();
		try {
			token.move(Direction.RIGHT);
			fail("should throw exception");
		} catch (IllegalArgumentException expected) {}
	}
}
