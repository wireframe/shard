package com.codecrate.shard.grid;

import junit.framework.TestCase;

public class DirectionTest extends TestCase {

	public void testOppositeDirectionOfLeftIsRight() {
		assertEquals(Direction.RIGHT, Direction.LEFT.opposite());
	}
	
	public void testOppositeDirectionOfUpLeftIsDownRight() {
		assertEquals(Direction.DOWN_RIGHT, Direction.UP_LEFT.opposite());
	}
}
