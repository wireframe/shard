package com.codecrate.shard.grid;

/**
 * Directions are used for navigating around a {@link Grid grid}
 */
public enum Direction {
	UP(0, -1),
	DOWN(0, 1),
	LEFT(-1, 0),
	RIGHT(1, 0),
	UP_LEFT(-1, -1),
	UP_RIGHT(1, -1),
	DOWN_LEFT(-1, 1),
	DOWN_RIGHT(1, 1);

	private final int xModifier;
	private final int yModifier;

	Direction(int xModifier, int yModifier) {
		this.xModifier = xModifier;
		this.yModifier = yModifier;
	}

	public String toString() {
		return "x:" + xModifier + " y:" + yModifier;
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

	public static Direction findByDirection(int directionX, int directionY) {
		for (Direction direction : values()) {
			if (direction.xModifier == directionX && direction.yModifier == directionY) {
				return direction;
			}
		}

		throw new IllegalArgumentException("Unable to determine direction from modifiers x:" + directionX + " y:" + directionY);
	}
}
