package com.codecrate.shard.grid;

import java.util.ArrayList;
import java.util.Collection;

/** 
 * Represents a <b>continuous</b> path from a start {@link GridSquare square} to an end position.
 */
public class Path {
	private Collection<GridSquare> steps = new ArrayList<GridSquare>();
	private GridSquare current;
	private int diagonals;
	
	public Path(GridSquare start) {
		this.current = start;
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

		GridSquare next = current.move(step);
		steps.add(next);
		this.current = next;
	}

	boolean contains(GridSquare square) {
		return steps.contains(square);
	}
}
