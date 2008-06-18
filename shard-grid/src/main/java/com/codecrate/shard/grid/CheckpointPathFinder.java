package com.codecrate.shard.grid;

import java.util.Collection;

/**
 * find a path from start to end, that follows a given set of checkpoints.
 */
public class CheckpointPathFinder implements PathFinder {
	private final Collection<GridSquare> checkpoints;

  public CheckpointPathFinder(Collection<GridSquare> checkpoints) {
	  this.checkpoints = checkpoints;
  }

	public Path findPathBetween(Grid grid, GridSquare start, GridSquare end) {
		Path path = new Path(start);
		GridSquare current = start;
		for (GridSquare next : checkpoints) {
			findSubpath(path, current, next);
			current = next;
		}
		findSubpath(path, current, end);

		return path;
	}

  /**
   * find a direct path from start to end.
   * @see DirectPathFinder
   */	
	private void findSubpath(Path path, GridSquare start, GridSquare end) {
		GridSquare current = start;
		while (!current.equals(end)) {
			Direction step = current.towards(end);
			path.addStep(step);
			
			current = current.nextSquare(step);
		}
	}
	
}