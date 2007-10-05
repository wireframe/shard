package com.codecrate.shard.grid;

public class GridSquare {
	private final Grid grid;
	private final int x;
	private final int y;

	public GridSquare(Grid grid, int x, int y) {
		this.grid = grid;
		this.x = x;
		this.y = y;
	}

	/**
	 * calculate the number of feet to another square in a straight line.
	 * Diagonal squares alternate between 5 and 10 foot increments.
	 */
	public int distanceTo(GridSquare end) {
		int distanceX = Math.abs(end.x - x);
		int distanceY = Math.abs(end.y - y);

		int diaganolSquares = Math.min(distanceX, distanceY);
		int discountSquares = (int) Math.ceil((double)diaganolSquares / 2);
		int diaganolDiscount = 5 * discountSquares;

		int fullDIstance = 5 * (distanceX + distanceY);
		return fullDIstance - diaganolDiscount;
	}
	
	public int uniqueId() {
		return (x * grid.getWidth()) + y;
	}
	
	public String toString() {
		return "" + x + "," + y;
	}
}
