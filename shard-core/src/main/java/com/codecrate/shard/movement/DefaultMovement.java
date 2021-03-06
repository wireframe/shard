/*
 * Copyright 2004 codecrate consulting
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.codecrate.shard.movement;

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
	
	public String toString() {
	    return Integer.toString(baseMovementRate);
	}
	
	public int getBaseMovementRate() {
		return baseMovementRate;
	}
}
