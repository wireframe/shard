package com.codecrate.shard.grid;

import java.util.ArrayList;
import java.util.Collection;

/** 
 * Represents a <b>continuous</b> path from a start {@link GridSquare square} to an end position.
 */
public class Path {
	private Collection<Direction> steps = new ArrayList<Direction>();
	private GridSquare start;
	
	public Path(GridSquare start) {
		this.start = start;
	}

	/** 
	 * get the length of the path.
	 */
	public int getLength() {
		int diagonals = 0;
		for (Direction step : steps) {
			if (step.isDiagonal()) {
				diagonals++;
			}
		}
		return 5 * steps.size() + 5 * (diagonals / 2);
	}
	
	/**
	 * add the next step to the path.
	 */
	public void addStep(Direction step) {
		steps.add(step);
	}

	/**
	 * check if the path does not change directions and is considered "straight".
	 */
	public boolean isStraight() {
		Direction currentDirection = null;
		for (Direction step : steps) {
			if (currentDirection != null && !currentDirection.equals(step)) {
				return false;
			}
			currentDirection = step;
		}
		return true;
	}

	public Collection<Direction> getDirections() {
		return steps;
	}
	public Collection<GridSquare> getGridSquares() {
		Collection<GridSquare> results = new ArrayList<GridSquare>();
		GridSquare currentSquare = start;
		for (Direction step : steps) {
			currentSquare = currentSquare.nextSquare(step);
			results.add(currentSquare);
		}
		return results;
	}
}
