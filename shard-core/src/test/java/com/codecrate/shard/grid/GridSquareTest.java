package com.codecrate.shard.grid;

import junit.framework.TestCase;

public class GridSquareTest extends TestCase {

	public void testSequentialIdStartsWithZero() {
		Grid grid = new Grid(2, 2);
		GridSquare start = grid.getSquare(new Location(0, 0));
		
		assertEquals(0, start.getSequentialId());
	}
	
	public void testSequentialIdIncrementsLinearlyAcrossRows() {
		Grid grid = new Grid(2, 2);
		assertEquals(0, grid.getSquare(new Location(0, 0)).getSequentialId());
		assertEquals(1, grid.getSquare(new Location(1, 0)).getSequentialId());
		assertEquals(2, grid.getSquare(new Location(0, 1)).getSequentialId());
		assertEquals(3, grid.getSquare(new Location(1, 1)).getSequentialId());
	}
	
	public void testSequentialIdParsesAcrossRows() {
		Grid grid = new Grid(2, 2);
		assertEquals(grid.getSquare(new Location(0, 0)), GridSquare.parseSequenceId(grid, 0));
		assertEquals(grid.getSquare(new Location(1, 0)), GridSquare.parseSequenceId(grid, 1));
	}
}
