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
package com.codecrate.shard.character.ability;

/**
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public interface Ability {
	/**
	 * gets the name of the ability. 
	 * @return
	 */
	String getName();
	
	/**
	 * gets the current value of the ability.
	 * includes modifiers.
	 * @return value.
	 */
	int getScore();

	/**
	 * gets the bonus from the ability value.
	 * ex: value of 10 = bonus of 0
	 * @return bonus.
	 */
	int getModifier();

	/**
	 * adds a modifier to the ability.
	 * @param modifier
	 */
	void addAbilityModifier(AbilityModifier modifier);

	/**
	 * removes a modifier from the ability.
	 * @param modifier
	 */
	void removeAbilityModifier(AbilityModifier modifier);
}