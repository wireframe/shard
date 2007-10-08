package com.codecrate.shard.grid;

import java.awt.Dimension;

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
	
	public GridSquare left() {
		return grid.getSquare(x - 1, y);
	}

	public GridSquare right() {
		return grid.getSquare(x + 1, y);
	}

	public GridSquare up() {
		return grid.getSquare(x, y - 1);
	}
	
	public GridSquare down() {
		return grid.getSquare(x, y + 1);
	}

	public boolean canMoveUp() {
		return y != 0;
	}
	public boolean canMoveDown() {
		return y != grid.getHeight() - 1;
	}
    public boolean canMoveLeft() {
    	return x != 0;
    }
    public boolean canMoveRight() {
    	return x != grid.getWidth() - 1;
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
	
	public enum Direction {
		UP(0, -1),
		DOWN(0, 1),
		LEFT(-1, 0),
		RIGHT(1, 0);

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
	}
}
