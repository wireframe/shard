package com.codecrate.shard.grid;

import junit.framework.TestCase;

public class GridSquareTest extends TestCase {

	public void testDistanceToNextSquareOnXAxisIsFiveFeet() {
		Grid grid = new Grid(2, 2);
		GridSquare start = grid.getSquare(0, 0);
		GridSquare end = grid.getSquare(1, 0);
		
		assertEquals(5, start.distanceTo(end));
	}

	public void testDistanceToPreviousSquareOnXAxisIsFiveFeet() {
		Grid grid = new Grid(2, 2);
		GridSquare start = grid.getSquare(1, 0);
		GridSquare end = grid.getSquare(0, 0);
		
		assertEquals(5, start.distanceTo(end));
	}

	public void testDistanceToNextSquareOnYAxisIsFiveFeet() {
		Grid grid = new Grid(2, 2);
		GridSquare start = grid.getSquare(0, 0);
		GridSquare end = grid.getSquare(0, 1);
		
		assertEquals(5, start.distanceTo(end));
	}

	public void testDistanceToPreviousSquareOnYAxisIsFiveFeet() {
		Grid grid = new Grid(2, 2);
		GridSquare start = grid.getSquare(0, 1);
		GridSquare end = grid.getSquare(0, 0);
		
		assertEquals(5, start.distanceTo(end));
	}

	public void testDiagonalDistanceStartsWithFiveFeet() {
		Grid grid = new Grid(2, 2);
		GridSquare start = grid.getSquare(0, 0);
		GridSquare end = grid.getSquare(1, 1);
		
		assertEquals(5, start.distanceTo(end));
	}
	
	public void testDiagonalDistanceIncreasesToTenFeet() {
		Grid grid = new Grid(3, 3);
		GridSquare start = grid.getSquare(0, 0);
		GridSquare end = grid.getSquare(2, 2);
		
		assertEquals(15, start.distanceTo(end));
	}
	
	public void testDiagonalDistanceAlternatesBetweenFiveAndTenFeet() {
		Grid grid = new Grid(5, 5);
		GridSquare start = grid.getSquare(0, 0);
		GridSquare end = grid.getSquare(4, 4);
		
		assertEquals(30, start.distanceTo(end));
	}
	
	public void testDiagonalDistanceWithXAxis() {
		Grid grid = new Grid(5, 5);
		GridSquare start = grid.getSquare(0, 0);
		GridSquare end = grid.getSquare(4, 2);
		
		assertEquals(25, start.distanceTo(end));
	}
	
	public void testDiagonalDistanceWithYAxis() {
		Grid grid = new Grid(5, 5);
		GridSquare start = grid.getSquare(0, 0);
		GridSquare end = grid.getSquare(2, 4);
		
		assertEquals(25, start.distanceTo(end));
	}
	
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
