package com.codecrate.shard.grid;

import junit.framework.TestCase;

public class GridSquareTest extends TestCase {

	public void testDistanceToNextSquareOnXAxisIsFiveFeet() {
		GridSquare start = new GridSquare(0, 0);
		GridSquare end = new GridSquare(1, 0);
		
		assertEquals(5, start.distanceTo(end));
	}

	public void testDistanceToPreviousSquareOnXAxisIsFiveFeet() {
		GridSquare start = new GridSquare(1, 0);
		GridSquare end = new GridSquare(0, 0);
		
		assertEquals(5, start.distanceTo(end));
	}

	public void testDistanceToNextSquareOnYAxisIsFiveFeet() {
		GridSquare start = new GridSquare(0, 0);
		GridSquare end = new GridSquare(0, 1);
		
		assertEquals(5, start.distanceTo(end));
	}

	public void testDistanceToPreviousSquareOnYAxisIsFiveFeet() {
		GridSquare start = new GridSquare(0, 1);
		GridSquare end = new GridSquare(0, 0);
		
		assertEquals(5, start.distanceTo(end));
	}

	public void testDiagonalDistanceStartsWithFiveFeet() {
		GridSquare start = new GridSquare(0, 0);
		GridSquare end = new GridSquare(1, 1);
		
		assertEquals(5, start.distanceTo(end));
	}
	
	public void testDiagonalDistanceIncreasesToTenFeet() {
		GridSquare start = new GridSquare(0, 0);
		GridSquare end = new GridSquare(2, 2);
		
		assertEquals(15, start.distanceTo(end));
	}
	
	public void testDiagonalDistanceAlternatesBetweenFiveAndTenFeet() {
		GridSquare start = new GridSquare(0, 0);
		GridSquare end = new GridSquare(4, 4);
		
		assertEquals(30, start.distanceTo(end));
	}
	
	public void testDiagonalDistanceWithXAxis() {
		GridSquare start = new GridSquare(0, 0);
		GridSquare end = new GridSquare(4, 2);
		
		assertEquals(25, start.distanceTo(end));
	}
	
	public void testDiagonalDistanceWithYAxis() {
		GridSquare start = new GridSquare(0, 0);
		GridSquare end = new GridSquare(2, 4);
		
		assertEquals(25, start.distanceTo(end));
	}
}
