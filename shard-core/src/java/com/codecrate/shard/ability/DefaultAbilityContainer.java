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
package com.codecrate.shard.ability;

import java.util.Map;

/**
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class DefaultAbilityContainer implements AbilityContainer {
	public static final String STRENGTH = "strength";
	public static final String DEXTERITY = "dexterity";
	public static final String CONSTITUTION = "constitution";
	public static final String WISDOM = "wisdom";
	public static final String INTELLIGENCE = "intelligence";
	public static final String CHARISMA = "charisma";
	
	private Map scores;

	public DefaultAbilityContainer(Map scores) {
	    this.scores = scores;
	}
	
	public Ability getAbility(String name) {
		return (Ability) scores.get(name);
	}
}
