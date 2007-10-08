package com.codecrate.shard.grid;

import java.util.Collection;

/**
 * API for finding a path between two <code>GridSquare</code>s.
 * 
 * @author rsonnek
 */
public interface PathFinder {

	/**
	 * find a path of <code>GridSquare</code>s from start to finish.
	 * @param start
	 * @param end
	 * @return
	 */
	public Collection<GridSquare> findPathBetween(Grid grid, GridSquare start, GridSquare end);
}
