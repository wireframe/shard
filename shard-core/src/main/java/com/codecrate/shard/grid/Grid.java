package com.codecrate.shard.grid;

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
				this.grid[m][n] = new GridSquare(this, new Location(m, n));
			}
		}
	}

	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}

	public GridSquare getSquare(Location location) {
		if (!doesSquareExist(location)) {
			throw new IllegalArgumentException("Cannot location square at " + location + ".  Must retrieve grid square from within bounds of " + getFirstLocation() + " and " + getLastLocation());
		}
		return grid[location.getX()][location.getY()];
	}

	public boolean doesSquareExist(Location location) {
		return location.getX() >=0 && location.getY() >=0 && location.getX() < width && location.getY() < height;
	}

	private Location getFirstLocation() {
		return new Location(0, 0);
	}

	private Location getLastLocation() {
		return new Location(width - 1, height - 1);
	}

	/**
	 * return all {@link GridSquare squares} in a row
	 */
	public GridSquare[] row(int row) {
		return grid[row];
	}
}
