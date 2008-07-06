package com.codecrate.shard.grid;

import java.util.Collection;

/**
 * find a path from start to end, that follows a given set of checkpoints.
 */
public class CheckpointPathFinder implements PathFinder {
	private final Collection<GridSquare> checkpoints;
  private final PathFinder finder;
  
  /**
   * @param finder implementation used to find path between checkpoints
   */
  public CheckpointPathFinder(Collection<GridSquare> checkpoints, PathFinder finder) {
	  this.checkpoints = checkpoints;
	  this.finder = finder;
  }

	public Path findPathBetween(GridSquare start, GridSquare end) {
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
   * find a subpath from start to end and add to the aggregate path.
   */	
	private void findSubpath(Path path, GridSquare start, GridSquare end) {
	  Path subpath = finder.findPathBetween(start, end);
	  for (Direction direction : subpath.getDirections()) {
	    path.addStep(direction);
	  }
	}
	
}