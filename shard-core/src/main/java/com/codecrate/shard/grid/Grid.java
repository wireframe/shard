package com.codecrate.shard.grid;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

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

	public Grid(int width, int height) {
		this.grid = new GridSquare[width][height];

		for (int m = 0; m < width; m++) {
			for (int n = 0; n < height; n++) {
				this.grid[m][n] = new GridSquare(m, n);
			}
		}
	}

	public GridSquare getSquare(int x, int y) {
		return grid[x][y];
	}

	/**
	 * @see http://www.codeproject.com/cs/algorithms/mazesolver.asp
	 */
	public Collection pathBetween(GridSquare start, GridSquare end) {
		Queue queue = new LinkedList();

		return null;
	}

	private int getSquareNumber(int x, int y) {
		return (x * y) + y;
	}

	private GridSquare shiftRight(GridSquare start) {
		if (start.x == grid[0].length) {
			return null;
		}
		return getSquare(start.x + 1, start.y);
	}
}
