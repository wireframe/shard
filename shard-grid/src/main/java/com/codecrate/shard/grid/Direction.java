package com.codecrate.shard.grid;

/**
 * Directions are used for navigating around a {@link Grid grid}
 */
public enum Direction {
	UP(0, -1, "Up"),
	DOWN(0, 1, "Down"),
	LEFT(-1, 0, "Left"),
	RIGHT(1, 0, "Right"),
	UP_LEFT(-1, -1, "Up Left"),
	UP_RIGHT(1, -1, "Up Right"),
	DOWN_LEFT(-1, 1, "Down Left"),
	DOWN_RIGHT(1, 1, "Down Right");

	private final int xModifier;
	private final int yModifier;
	private final String label;
	
	Direction(int xModifier, int yModifier, String label) {
		this.xModifier = xModifier;
		this.yModifier = yModifier;
		this.label = label;
	}

	public String toString() {
		return label;
	}

	public int getXModifier() {
		return xModifier;
	}

	public int getYModifier() {
		return yModifier;
	}

	public boolean isDiagonal() {
		return xModifier != 0 && yModifier != 0;
	}
	
	public Direction opposite() {
		return findByDirection(xModifier * -1, yModifier * -1);
	}

	public static Direction findByDirection(int directionX, int directionY) {
		for (Direction direction : values()) {
			if (direction.xModifier == directionX && direction.yModifier == directionY) {
				return direction;
			}
		}

		throw new IllegalArgumentException("Unable to determine direction from modifiers x:" + directionX + " y:" + directionY);
	}
}
