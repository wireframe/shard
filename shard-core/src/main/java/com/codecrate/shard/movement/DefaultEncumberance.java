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

public class DefaultEncumberance implements Encumberance {
	public static final Encumberance LIGHT = new DefaultEncumberance("Light", 100, 0);
	public static final Encumberance MEDIUM = new DefaultEncumberance("Medium", 3, -3);
	public static final Encumberance HEAVY = new DefaultEncumberance("Heavy", 1, -6);

	
	private final int maxDexterityModifier;
	private final int armorCheckModifier;
	private final String name;

	public DefaultEncumberance(String name, int maxDexterityModifier, int armorCheckModifier) {
		this.name = name;
		this.maxDexterityModifier = maxDexterityModifier;
		this.armorCheckModifier = armorCheckModifier;
	}
	
	public String getName() {
		return name;
	}
	
	public String toString() {
	    return name;
	}
	
	public int getMaxDexterityModifier() {
		return maxDexterityModifier;
	}

	public int getArmorCheckModifier() {
		return armorCheckModifier;
	}

}
