package com.codecrate.shard.grid;

/**
 * A Location is a set of X and Y coordinates.
 * 
 */
public class Location {
	private final int x;
	private final int y;

	public Location(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public String toString() {
		return "(" + x + "," + y + ")";
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}

	public Direction directionTo(Location location) {
		int directionX = location.x - x;
		int directionY = location.y - y;

		return Direction.findByDirection(directionX, directionY);
	}

	public Location nextLocation(Direction direction) {
		int newX = x + direction.getXModifier();
		int newY = y + direction.getYModifier();
		return new Location(newX, newY);
	}
}
