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
	public static final Vision NORMAL = new DefaultVision("Normal", 0, 1);
	public static final Vision LOW_LIGHT_VISION = new DefaultVision("Low-Light", 0, 2);
	public static final Vision DARKVISION = new DefaultVision("Darkvision", 60, 1);

	private final String name;
	private final int distance;
	private final int lightMultiplier;

	public DefaultVision(String name, int darkDistance, int lightMultiplier) {
		this.name = name;
		this.distance = darkDistance;
		this.lightMultiplier = lightMultiplier;
	}

    public String toString() {
        return name + " " + distance;
    }
	public String getName() {
		return name;
	}

	public int getDarkDistance() {
		return distance;
	}

	public int getLightMultiplier() {
		return lightMultiplier;
	}
}
