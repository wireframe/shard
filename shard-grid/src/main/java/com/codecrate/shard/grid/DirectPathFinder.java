package com.codecrate.shard.grid;


/**
 * find the path between two squares using a direct reach algorithm.
 */
public class DirectPathFinder implements PathFinder {

	public Path findPathBetween(GridSquare start, GridSquare end) {
		Path path = new Path(start);

		GridSquare current = start;
		while (!current.equals(end)) {
			Direction step = current.towards(end);
			path.addStep(step);
			
			current = current.nextSquare(step);
		}
		return path;
	}
}
