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

/**
 * Helper class to easily work with ability modifiers.
 * provides basic defaults for how ability modifiers should work.
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class DefaultAbilityModifier implements AbilityModifier {

	private final String abilityName;
	private final int modifier;

	public DefaultAbilityModifier(String abilityName, int modifier) {
		this.abilityName = abilityName;
		this.modifier = modifier;
	}

	public String getAbilityName() {
		return abilityName;
	}

	public int getModifier() {
		return modifier;
	}

}
