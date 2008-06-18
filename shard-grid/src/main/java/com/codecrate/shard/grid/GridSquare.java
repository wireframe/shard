package com.codecrate.shard.grid;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

/**
 * A GridSquare is a composition of a {@link Location location} along with metadata about the location.
 * 
 */
public class GridSquare {
	private final Grid grid;
	private final Location location;
	private boolean blocked = false;

	public GridSquare(Grid grid, Location location) {
		this.grid = grid;
		this.location = location;
	}

	public String toString() {
		return location.toString();
	}

	/**
	 * return a unique id for each square on the grid.
	 * values range from: 0 to total_number_of_squares_in_grid - 1
	 * @see http://www.codeproject.com/cs/algorithms/mazesolver.asp
	 */
	public int getSequentialId() {
		return (location.getY() * grid.getWidth()) + location.getX();
	}

	/**
	 * locate a particular grid square based on it's {@link #getSequentialId() unique id}
	 */
	public static GridSquare parseSequenceId(Grid grid, int id) {
		int row = id / grid.getWidth();
		int column = id % grid.getWidth();
		
		return grid.getSquare(new Location(column, row));
	}

	/**
	 * toggle between blocked and not blocked.
	 */
	public void toggle() {
		this.blocked = !blocked;
	}

	public  boolean isBlocked() {
		return blocked ;
	}

    public boolean doesSquareExist(Direction direction) {
    	Location location = this.location.nextLocation(direction);
    	return grid.isWithinBounds(location);
    }

    public GridSquare nextSquare(Direction direction) {
    	Location location = this.location.nextLocation(direction);
    	return grid.getSquare(location);
    }

	/** 
	 * return the most direct direction towards the end position.
	 */
	public Direction towards(GridSquare end) {
		int directionX = restrictRange(end.location.getX() - location.getX());
		int directionY = restrictRange(end.location.getY() - location.getY());
		
		return Direction.findByDirection(directionX, directionY);
	}

	/**
	 * return all adjacent {@link GridSquare squares} to the current object.
	 * @return
	 */
	public Collection<GridSquare> neighbors() {
		Collection<GridSquare> results = new ArrayList<GridSquare>();
		for (Direction direction : Direction.values()) {
			Location newLocation = location.nextLocation(direction);
			if (grid.isWithinBounds(newLocation)) {
				results.add(grid.getSquare(newLocation));
			}
		}
		return results;
	}
	
 /** 
  * get all gridsquares that are affected by a blast centered on the current square as the 'origin'
  */	
	public Collection<GridSquare> blast(int radius) {
		Collection<GridSquare> results = new HashSet<GridSquare>();
		
		for (int x = location.getX() - radius; x <= location.getX() + radius; x++) {
			for (int y = location.getY() - radius; y <= location.getY() + radius; y++) {
        Location newLocation = new Location(x, y);
				if (grid.isWithinBounds(newLocation)) {
					results.add(grid.getSquare(newLocation));
				}
			}
		}
		results.remove(this);
		return results;
	}

	/**
	 * restrict the range of the value between -1 and 1.
	 */
	private int restrictRange(int value) {
		value = Math.max(-1, value);
		value = Math.min(1, value);
		return value;
	}
}
