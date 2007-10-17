package com.codecrate.shard.grid;


/**
 * find the path between two squares using a direct reach algorithm.
 */
public class DirectPathFinder implements PathFinder {

	@Override
	public Path findPathBetween(Grid grid, GridSquare start, GridSquare end) {
		Path path = new Path(start);

		GridSquare current = start;
		while (!current.equals(end)) {
			current = current.towards(end);
			path.addStep(current);
		}
		return path;
	}
}
