package com.codecrate.shard.grid;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * A token represents an object that can be moved around the grid.
 * 
 */
public class Token {
	private GridSquare location;

	/**
	 * place the token on a grid square.
	 */
	public void place(GridSquare square) {
		this.location = square;
	}

	/** 
	 * check if the token can move along a given path.
	 */
	public boolean canMove(Path path) {
		if (location == null) {
			return false;
		}
		for (GridSquare square : path.getGridSquares()) {
			if (square.isBlocked()) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * move the token along the given path.
	 * @return the final destination from moving along the path.
	 */
	public GridSquare move(Path path) {
		if (!canMove(path)) {
			throw new IllegalArgumentException("Unable to move token along path: " + path);
		}
		this.location = path.getDestination();
		return location;
	}
	
	public Icon getIcon() {
		return new ImageIcon(getClass().getClassLoader().getResource("images/token.png"));
	}
	public GridSquare getGridSquare() {
		return location;
	}
}
