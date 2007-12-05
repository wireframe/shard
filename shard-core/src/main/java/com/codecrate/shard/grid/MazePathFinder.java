package com.codecrate.shard.grid;


/**
 * find the path between two squares using a fast "maze" algorithm.
 * 
 * @see http://www.codeproject.com/cs/algorithms/mazesolver.asp
 * @see http://forum.java.sun.com/thread.jspa?threadID=740955&start=0
 */
public class MazePathFinder implements PathFinder {

	@Override
	public Path findPathBetween(Grid grid, GridSquare start, GridSquare end) {
		int maxSize = grid.getWidth() * grid.getHeight();

		int[] queue = new int[maxSize];
		int[] origin = new int[maxSize];
		for(int i = 0; i < maxSize; i++){
			queue[i]=-1; 
			origin[i]=-1;
		}

		GridSquare current = end;
		GridSquare previous = end;

		while (!current.equals(start)) {
			for (Direction direction : Direction.values()) {
				if (current.canMove(direction)) {
					previous = visit(current.move(direction), queue, origin, previous, current);
				}
			}

			int nextSquareId = queue[current.getSequentialId()];
			current = GridSquare.parseSequenceId(grid, nextSquareId);
		}

		Path path = new Path(start);
		while (!current.equals(end)) {
			int direction = origin[current.getSequentialId()];
			current = GridSquare.parseSequenceId(grid, direction + current.getSequentialId());
			path.addStep(current);
		}

		return path;
	}

	private GridSquare visit(GridSquare next, int[] queue, int[] origin, GridSquare previous, GridSquare current) {
		if (queue[next.getSequentialId()] == -1) {
			queue[previous.getSequentialId()] = next.getSequentialId(); 
			origin[next.getSequentialId()] = -(next.getSequentialId() - current.getSequentialId());
			return next;
		}
		return previous;
	}
}
