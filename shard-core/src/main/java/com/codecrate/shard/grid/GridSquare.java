package com.codecrate.shard.grid;

import java.util.ArrayList;
import java.util.Collection;

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
	 * @see http://www.codeproject.com/cs/algorithms/mazesolver.asp
	 */
	public int getSequentialId() {
		return (location.getY() * grid.getWidth()) + location.getX();
	}
	
	public static GridSquare parseSequenceId(Grid grid, int id) {
		int row = id / grid.getWidth();
		int column = id % grid.getWidth();
		
		return grid.getSquare(new Location(row, column));
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

	public Direction directionTo(GridSquare next) {
		return this.location.directionTo(next.location);
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
	 * restrict the range of the value between -1 and 1.
	 */
	private int restrictRange(int value) {
		if (value < 0) {
			value = Math.max(-1, value);
		}
		if (value > 0) {
			value = Math.min(1, value);
		}
		return value;
	}
}
