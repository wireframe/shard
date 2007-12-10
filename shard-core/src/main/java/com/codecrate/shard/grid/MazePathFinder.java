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

		int[] queue = initializeGridArray(grid, maxSize);
		int[] origin = initializeGridArray(grid, maxSize);

		GridSquare current = end;
		GridSquare previous = end;

		while (!current.equals(start)) {
			for (Direction direction : Direction.values()) {
				if (current.doesSquareExist(direction)) {
					previous = visit(previous, current, current.nextSquare(direction), queue, origin);
				}
			}

			int nextSquareId = queue[current.getSequentialId()];
			current = GridSquare.parseSequenceId(grid, nextSquareId);
		}

		Path path = new Path(start);
		while (!current.equals(end)) {
			int direction = origin[current.getSequentialId()];
			GridSquare next = GridSquare.parseSequenceId(grid, direction + current.getSequentialId());
			
			path.addStep(current.towards(next));
			current = next;
		}

		return path;
	}

	private int[] initializeGridArray(Grid grid, int maxSize) {
		int[] array = new int[maxSize];
		for(int i = 0; i < maxSize; i++){
			int value = -1;
			if (GridSquare.parseSequenceId(grid, i).isBlocked()) {
				value = -2;
			}
			array[i] = value;
		}
		return array;
	}

	private GridSquare visit(GridSquare previous, GridSquare current, GridSquare next, int[] queue, int[] origin) {
		if (queue[next.getSequentialId()] == -1) {
			queue[previous.getSequentialId()] = next.getSequentialId(); 
			origin[next.getSequentialId()] = -(next.getSequentialId() - current.getSequentialId());
			return next;
		}
		return previous;
	}
}
