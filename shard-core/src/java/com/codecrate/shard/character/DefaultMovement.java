
package com.codecrate.shard.character;

/**
 * Default implementation of movement handles base movement rate.
 * can be used as base movement rate for races.  ex: human=30
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class DefaultMovement implements Movement {

	private int baseMovementRate;
	
	public DefaultMovement(int baseMovementRate) {
		if (0 > baseMovementRate) {
			throw new IllegalArgumentException("Base movement rate can not be lower than zero.");
		}
		this.baseMovementRate = baseMovementRate;
	}
	
	public int getBaseMovementRate() {
		return baseMovementRate;
	}
}
