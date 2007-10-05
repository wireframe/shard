package com.codecrate.shard.grid;

import java.util.Collection;

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
		if (x >= width || y >= height) {
			throw new IllegalArgumentException("Must retrieve grid square from within bounds of " + getFirstSquare() + " and " + getLastSquare());
		}
		return grid[x][y];
	}

	private GridSquare getFirstSquare() {
		return getSquare(0, 0);
	}

	private GridSquare getLastSquare() {
		return getSquare(width - 1, height -1);
	}

	/**
	 * @see http://www.codeproject.com/cs/algorithms/mazesolver.asp
	 * @see http://forum.java.sun.com/thread.jspa?threadID=740955&start=0
	 */
	public Collection pathBetween(GridSquare start, GridSquare end) {
		int maxSize = getLastSquare().uniqueId();
		
		int[] path = new int[maxSize];
		int[] path2 = new int[maxSize];

		int startInt = start.uniqueId();
		int endInt = end.uniqueId();

		int current = endInt;
		int previous = endInt;
		
		while(current != startInt){
			visit(current, 1, path, path2, previous, maxSize);
			visit(current,-1, path, path2, previous, maxSize);
			visit(current, height, path, path2, previous, maxSize);
			visit(current,-height, path, path2, previous, maxSize);
			
			System.out.println(stringify(path));
			current = path[current];
		}
		
		while(current != endInt){
			System.out.print(path2[current]); 
			current += path2[current];
		}
		
		return null;
	}

	private String stringify(int[] path) {
		StringBuffer buffer = new StringBuffer();
		for (int x = 0; x < path.length; x++) {
			int i = path[x];
			buffer.append(Integer.toString(i)).append(", ");
		}
		return buffer.toString();
	}

	private void visit(int source, int increment, int[] path, int[] path2, int previous, int maxSize) {
		int destination = source + increment;
		if (destination >= maxSize) {
			return;
		}
		if (path[destination] == -1) {
			path[previous] = destination; 
			
			previous = destination; 
			path2[destination] = -increment;
		}
	}

	/**
	 * @see http://forum.java.sun.com/thread.jspa?threadID=740955&start=0
	 * @author rsonnek
	 */
	public static class PathFinder {
		int a,b,rows,lastInt,startInt, endInt;
		int[] s, g;

		void solve(String[] maze){ // maze MUST have solid border of *
			rows = maze[0].length();
			lastInt = maze.length*rows;
			s = new int[lastInt];
			g = new int[lastInt];
			
			for(int i = 0; i<lastInt; i++){
				s[i]=-1; g[i]=-1; // path
				char ch = maze[i/rows].charAt(i%rows);
				if(ch=='*')s[i]=-2; // wall
				if(ch=='S')startInt=i;
				if(ch=='E')endInt=i;
			}

			a = endInt; 
			b=endInt;
			while(a!=startInt){
				add(a,1);
				add(a,-1);
				add(a,rows);
				add(a,-rows);
				a=s[a];
			}
			while(a!=endInt){
				System.out.print(sv(g[a])); 
				a+=g[a];
			}
			System.out.println();
		}

		void add(int p, int o){
			if(s[p+o] == -1) {
				s[b]=p+o; 
				b=p+o; 
				g[p+o]=-o;
			}
		}
		String sv(int o){
			if(o>0) { 
				return (o==1)?"E":"S";
			}
			return (o==-1)?"W":"N";
		}
	}
}
