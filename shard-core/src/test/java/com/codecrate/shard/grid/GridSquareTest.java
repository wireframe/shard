package com.codecrate.shard.grid;

import junit.framework.TestCase;

public class GridSquareTest extends TestCase {

	public void testSequentialIdStartsWithZero() {
		Grid grid = new Grid(2, 2);
		GridSquare start = grid.getSquare(0, 0);
		
		assertEquals(0, start.getSequentialId());
	}
	
	public void testSequentialIdIncrementsLinearlyAcrossRows() {
		Grid grid = new Grid(2, 2);
		assertEquals(0, grid.getSquare(0, 0).getSequentialId());
		assertEquals(1, grid.getSquare(1, 0).getSequentialId());
		assertEquals(2, grid.getSquare(0, 1).getSequentialId());
		assertEquals(3, grid.getSquare(1, 1).getSequentialId());
	}
}
