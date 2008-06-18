package com.codecrate.shard.grid;


/**
 * find the path between two squares using a fast "maze" algorithm.
 * 
 * @see http://www.codeproject.com/cs/algorithms/mazesolver.asp
 * @see http://forum.java.sun.com/thread.jspa?threadID=740955&start=0
 */
public class MazePathFinder implements PathFinder {
	private static final int BLOCKED = -2;
	private static final int OPEN = -1;

  private final Grid grid;
  private final int[] origin;
  private final int[] queue;
  
  public MazePathFinder(Grid grid) {
	  this.grid = grid;
	  this.queue = initializeGridArray(grid);
	  this.origin = initializeGridArray(grid);
  }

	public Path findPathBetween(GridSquare start, GridSquare end) {
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

	/**
	 * create a one dimensional array that represents the grid in use.
	 * each location within the array maps to a square within the grid. 
	 * the value of each array location tells if the square is available for path movement.
	 */
	private int[] initializeGridArray(Grid grid) {
		int maxSize = grid.getWidth() * grid.getHeight();
		int[] array = new int[maxSize];
		
		for (int x = 0; x < grid.getHeight(); x++) {
			for (GridSquare square : grid.row(x)) {
				array[square.getSequentialId()] = (square.isBlocked() ? BLOCKED : OPEN);
			}
		}
		return array;
	}

	private GridSquare visit(GridSquare previous, GridSquare current, GridSquare next, int[] queue, int[] origin) {
		if (queue[next.getSequentialId()] == OPEN) {
			queue[previous.getSequentialId()] = next.getSequentialId(); 
			origin[next.getSequentialId()] = -(next.getSequentialId() - current.getSequentialId());
			return next;
		}
		return previous;
	}
}
