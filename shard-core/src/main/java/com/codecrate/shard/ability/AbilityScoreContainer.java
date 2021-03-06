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

import java.util.Collection;

import com.codecrate.shard.modifier.KeyedModifierContainer;

/**
 * Defines an interface for objects that contain Abilities.
 * the caller must be aware that not all ability containers 
 * will have all abilities.  ex: some creatures do not have a
 * strength ability, like wraithes.
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public interface AbilityScoreContainer extends KeyedModifierContainer {
	AbilityScore getStrength();
	
	AbilityScore getDexterity();
	
	AbilityScore getWisdom();
	
	AbilityScore getIntelligence();
	
	AbilityScore getConstitution();

	AbilityScore getCharisma();

	/**
	 * gets an ability with the specified name.
	 * @param ability
	 * @return the ability or null if not found.
	 */
	AbilityScore getAbilityScore(Ability ability);
	
	/**
	 * gets all ability scores the character has.
	 * @return
	 */
	Collection getAbilityScores();
	
	/**
	 * allows checking if container has an ability.
	 * @param ability
	 * @return true if ability exists, else false.
	 */
	boolean hasAbilityScore(Ability ability);
	
	/**
	 * gets the total point score for the abilities.
	 * @return
	 */
	int getTotalPointScore();
}