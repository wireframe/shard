package com.codecrate.shard.grid;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * A token represents an object that can be moved around the grid.
 * 
 */
public class Token {
	private GridSquare square;

	/**
	 * place the token on a grid square.
	 */
	public void place(GridSquare square) {
		this.square = square;
	}

	/** 
	 * check if the token can move along a given path.
	 */
	public boolean canMove(Direction direction) {
		if (!square.doesSquareExist(direction)) {
			return false;
		}
		if (square.nextSquare(direction).isBlocked()) {
			return false;
		}
		return true;
	}
	
	public void move(Direction direction) {
		if (!canMove(direction)) {
			throw new IllegalArgumentException("Unable to move in direction: " + direction);
		}
		this.square = square.nextSquare(direction);
	}
	
	public Icon getIcon() {
		return new ImageIcon(getClass().getClassLoader().getResource("images/token.png"));
	}
	public GridSquare getGridSquare() {
		return square;
	}
}
