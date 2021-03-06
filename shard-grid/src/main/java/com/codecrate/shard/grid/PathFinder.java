package com.codecrate.shard.grid;


/**
 * API for finding a path between two <code>GridSquare</code>s.
 * 
 * @author rsonnek
 */
public interface PathFinder {

	/**
	 * find a <code>Path</code> of <code>GridSquare</code>s from start to end.
	 * @throws PathNotFoundException if unable to find path from start to end.
	 */
	public Path findPathBetween(GridSquare start, GridSquare end);
}
