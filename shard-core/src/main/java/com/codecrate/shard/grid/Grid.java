package com.codecrate.shard.grid;

public class Grid {

	private GridSquare[][] grid;
	
	public Grid(int x, int y) {
		this.grid = new GridSquare[x][y];
		
		for (int m = 0; m < x; m++) {
			for (int n = 0; n < y; n++) {
				this.grid[m][n] = new GridSquare(m, n);
			}
		}
	}
	
	public GridSquare getSquare(int x, int y) {
		return grid[x][y];
	}
}
