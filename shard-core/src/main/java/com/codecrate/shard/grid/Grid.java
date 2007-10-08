package com.codecrate.shard.grid;

import java.util.ArrayList;
import java.util.Collection;

import com.codecrate.shard.grid.GridSquare.Direction;

/**
 * The grid is an n x m sized Grid of <code>GridSquare</code>s.
 * <p>
 * The grid is <b>zero</b> indexed which means the <i>first</i> position is actually the
 * accessed as with the <i>zero</i> index.
 * </p>
 * <p>
 * The layout of the grid is an <b>inverted y axis</b> which means that the x axis increases
 * from left to right, and the y axis increases top to bottom.
 * </p>
 * <p>
 * example grid with (x,y) locations.
 * </p>
 * <table border="1">
 *   <tr>
 *     <td>0,0</td>
 *     <td>0,1</td>
 *   </tr>
 *   <tr>
 *     <td>1,0</td>
 *     <td>1,1</td>
 *   </tr>
 * </table>
 *
 * @author rsonnek
 */
public class Grid {
	private GridSquare[][] grid;
	private final int width;
	private final int height;

	public Grid(int width, int height) {
		this.width = width;
		this.height = height;
		this.grid = new GridSquare[width][height];

		for (int m = 0; m < width; m++) {
			for (int n = 0; n < height; n++) {
				this.grid[m][n] = new GridSquare(this, m, n);
			}
		}
	}

	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}

	public GridSquare getSquare(int x, int y) {
		if (!doesSquareExist(x, y)) {
			throw new IllegalArgumentException("Cannot location square at (" + x + "," + y + ").  Must retrieve grid square from within bounds of " + getFirstSquare() + " and " + getLastSquare());
		}
		return grid[x][y];
	}
	
	public boolean doesSquareExist(int x, int y) {
		return x >=0 && y >=0 && x < width && y < height;
	}

	private GridSquare getFirstSquare() {
		return getSquare(0, 0);
	}

	private GridSquare getLastSquare() {
		return getSquare(width - 1, height -1);
	}

	/**
	 * find the path between two squares.
	 * @see http://www.codeproject.com/cs/algorithms/mazesolver.asp
	 * @see http://forum.java.sun.com/thread.jspa?threadID=740955&start=0
	 * @see http://renaud.waldura.com/doc/java/dijkstra/
	 */
	public Collection<GridSquare> pathBetween(GridSquare start, GridSquare end) {
		int maxSize = width * height;

		int[] queue = new int[maxSize];
		int[] origin = new int[maxSize];
		for(int i = 0; i < maxSize; i++){
			queue[i]=-1; 
			origin[i]=-1;
		}

		GridSquare current = end;
		GridSquare previous = end;

		while (!current.equals(start)) {
			for (Direction direction : GridSquare.Direction.values()) {
				if (current.canMove(direction)) {
					previous = visit(current.move(direction), queue, origin, previous, current);
				}
			}
//			if (current.canMoveRight()) {
//				previous = visit(current.right(), queue, origin, previous, current);
//			}
//			if (current.canMoveLeft()) {
//				previous = visit(current.left(), queue, origin, previous, current);
//			}
//			if (current.canMoveUp()) {
//				previous = visit(current.up(), queue, origin, previous, current);
//			}
//			if (current.canMoveDown()) {
//				previous = visit(current.down(), queue, origin, previous, current);
//			}

			int nextSquareId = queue[current.getSequentialId()];
			current = GridSquare.parseSequenceId(this, nextSquareId);
		}

		Collection<GridSquare> shortestPath = new ArrayList<GridSquare>();
		while (!current.equals(end)) {
			int direction = origin[current.getSequentialId()];
			current = GridSquare.parseSequenceId(this, direction + current.getSequentialId());
			shortestPath.add(current);
		}

		return shortestPath;
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
