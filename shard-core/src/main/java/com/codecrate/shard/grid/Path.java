package com.codecrate.shard.grid;

import java.util.ArrayList;
import java.util.Collection;

public class Path {
	private Collection<GridSquare> path = new ArrayList<GridSquare>();
	private GridSquare current;
	private int diagonals;
	
	public Path(GridSquare start) {
		this.current = start;
	}

	public int getLength() {
		return 5 * path.size() + 5 * (diagonals / 2);
	}
	
	public void addStep(GridSquare next) {
		path.add(next);

		if (current.directionTo(next).isDiagonal()) {
			diagonals++;
		}
		
		this.current = next;
	}

	boolean contains(GridSquare square) {
		return path.contains(square);
	}
}
