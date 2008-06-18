package com.codecrate.shard.grid;

import junit.framework.TestCase;

import java.util.Collection;

public class GridSquareTest extends TestCase {

	public void testSequentialIdStartsWithZero() {
		Grid grid = new Grid(2, 2);
		GridSquare start = grid.getSquare(new Location(0, 0));
		
		assertEquals(0, start.getSequentialId());
	}

	public void testSequentialIdEndsWithMaxNumberOfSquaresMinusOne() {
		Grid grid = new Grid(3, 3);
		GridSquare start = grid.getSquare(new Location(2, 2));
		
		assertEquals(8, start.getSequentialId());
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
	
	public void testCanDetermineDirectionTowardsNonAdjacentSquare() {
		Grid grid = new Grid(3, 3);
		GridSquare start = grid.getSquare(Location.ORIGIN);
		
		assertEquals(Direction.RIGHT, start.towards(grid.getSquare(new Location(2, 0))));
	}

	public void testDirectionTowardsNonAdjacentSquareIsDiagonalIfNotInSameRowOrColumn() {
		Grid grid = new Grid(3, 3);
		GridSquare start = grid.getSquare(Location.ORIGIN);
		
		assertEquals(Direction.DOWN_RIGHT, start.towards(grid.getSquare(new Location(2, 1))));
		assertEquals(Direction.DOWN_RIGHT, start.towards(grid.getSquare(new Location(2, 2))));
		assertEquals(Direction.DOWN_RIGHT, start.towards(grid.getSquare(new Location(1, 2))));
	}
	
	public void testBlastSquaresReturnsAllAffectedAdjacentSquares() {
		Grid grid = new Grid(5, 5);
		GridSquare origin = grid.getSquare(new Location(2, 2));
		
		Collection squares = origin.blast(2);
		assertTrue(squares.contains(grid.getSquare(new Location(0, 0))));
		System.out.println(squares);
		System.out.println(squares.size());
		assertEquals(24, squares.size());
	}
}
