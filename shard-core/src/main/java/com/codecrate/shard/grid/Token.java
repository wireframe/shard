package com.codecrate.shard.grid;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * A token represents an object that can be moved around the grid.
 * 
 */
public class Token {
	private GridSquare square;

	public void place(GridSquare square) {
		this.square = square;
	}
	public boolean canMove(Direction direction) {
		return square.doesSquareExist(direction);
	}
	public void move(Direction direction) {
		this.square = square.nextSquare(direction);
	}
	
	public Icon getIcon() {
		return new ImageIcon(getClass().getClassLoader().getResource("images/token.png"));
	}
	public GridSquare getGridSquare() {
		return square;
	}
}
