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
package com.codecrate.shard.race;



/**
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class DefaultVision implements Vision {
	public static final Vision NORMAL = new DefaultVision("Normal", 30);
	public static final Vision LOW_LIGHT_VISION = new DefaultVision("Low-Light", 60);
	public static final Vision DARKVISION = new DefaultVision("Darkvision", 90);
	
	private final String name;
	private final int distance;
	
	public DefaultVision(String name, int distance) {
		this.name = name;
		this.distance = distance;
	}
	
	public String getName() {
		return name;
	}
	
	public int getDistance() {
		return distance;
	}
}
