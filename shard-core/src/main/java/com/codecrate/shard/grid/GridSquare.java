package com.codecrate.shard.grid;

import java.awt.Dimension;
import java.util.Iterator;

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

    public boolean canMove(Direction direction) {
    	Dimension location = direction.nextLocation(this);
    	return grid.doesSquareExist(location.width, location.height);
    }

    public GridSquare move(Direction direction) {
    	Dimension location = direction.nextLocation(this);
    	return grid.getSquare(location.width, location.height);
    }

	/**
	 * return a unique id for each square on the grid.
	 * @see http://www.codeproject.com/cs/algorithms/mazesolver.asp
	 */
	public int getSequentialId() {
		return (y * grid.getWidth()) + x;
	}
	
	public String toString() {
		return "(" + x + "," + y + ")";
	}

	public static GridSquare parseSequenceId(Grid grid, int id) {
		int row = id / grid.getWidth();
		int column = id % grid.getWidth();
		
		return grid.getSquare(row, column);
	}

	public GridSquare towards(GridSquare end) {
		int directionX = restrictRange(end.x - x);
		int directionY = restrictRange(end.y - y);
		
		return grid.getSquare(x + directionX, y + directionY);
	}

	public Direction directionTo(GridSquare next) {
		int directionX = next.x - x;
		int directionY = next.y - y;
		
		for (Direction direction : Direction.values()) {
			if (direction.xModifier == directionX && direction.yModifier == directionY) {
				return direction;
			}
		}

		throw new IllegalArgumentException("Unable to determine direction from " + this + " to " + next);
	}

	/**
	 * restrict the range of the value between -1 and 1.
	 */
	private int restrictRange(int value) {
		if (value < 0) {
			value = Math.max(-1, value);
		}
		if (value > 0) {
			value = Math.min(1, value);
		}
		return value;
	}

	public enum Direction {
		UP(0, -1),
		DOWN(0, 1),
		LEFT(-1, 0),
		RIGHT(1, 0),
		UP_LEFT(-1, -1),
		UP_RIGHT(1, -1),
		DOWN_LEFT(-1, 1),
		DOWN_RIGHT(1, 1);

		private final int xModifier;
		private int yModifier;

		Direction(int xModifier, int yModifier) {
			this.xModifier = xModifier;
			this.yModifier = yModifier;
		}

		Dimension nextLocation(GridSquare square) {
			int x = square.x + xModifier;
			int y = square.y + yModifier;
			return new Dimension(x, y);
		}
		
		public boolean isDiagonal() {
			return xModifier != 0 && yModifier != 0;
		}
	}
}
