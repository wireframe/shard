package com.codecrate.shard.character;


/**
 */
public class DefaultVision implements Vision {
	private int distance;
	
	public DefaultVision(int distance) {
		this.distance = distance;
	}
	
	public int getDistance() {
		return distance;
	}
}
