package com.codecrate.shard.grid;

import java.util.ArrayList;
import java.util.Collection;

/** 
 * Represents a <b>continuous</b> path from a start {@link GridSquare square} to an end position.
 */
public class Path {
	private Collection<GridSquare> steps = new ArrayList<GridSquare>();
	private GridSquare currentSquare;
	private Direction currentDirection;
	private int diagonals;
	private boolean straight = true;
	
	public Path(GridSquare start) {
		this.currentSquare = start;
	}

	public int getLength() {
		return 5 * steps.size() + 5 * (diagonals / 2);
	}
	
	/**
	 * add the next step to the path.
	 */
	public void addStep(Direction step) {
		if (step.isDiagonal()) {
			diagonals++;
		}
		if (currentDirection != null && !currentDirection.equals(step)) {
			straight = false;
		}
		GridSquare next = currentSquare.nextSquare(step);
		steps.add(next);
		this.currentSquare = next;
		this.currentDirection = step;
	}

	/**
	 * check if the path does not change directions and is considered "straight".
	 */
	public boolean isStraight() {
		return straight;
	}

	public Collection<GridSquare> getGridSquares() {
		return steps;
	}
}
