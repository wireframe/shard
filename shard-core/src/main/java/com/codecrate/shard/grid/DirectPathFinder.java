package com.codecrate.shard.grid;

import java.util.ArrayList;
import java.util.Collection;

/**
 * find the path between two squares using a direct reach algorithm.
 */
public class DirectPathFinder implements PathFinder {

	@Override
	public Collection<GridSquare> findPathBetween(Grid grid, GridSquare start, GridSquare end) {
		Collection<GridSquare> path = new ArrayList<GridSquare>();

		GridSquare current = start;
		while (!current.equals(end)) {
			current = current.towards(end);
			path.add(current);
		}
		return path;
	}
}
