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
 * Encumberance is what load the character is currently under.
 * inventory weight and armor worn both affect what encumberance the 
 * character has.  Carrying a light load, but using heavy armor puts the
 * character under higher encumberance and vice versa.
 * Note that the Max Dexterity Modifier for encumberance ONLY applies 
 * to the max dexterity modifier for armor class.  The character's dex 
 * modifier can still be used in other ways without this restriction.
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public interface Encumberance {

	String getName();
	
	/**
	 * gets the max dexterity modifier for character.
	 * heavy armor restricts movement.
	 * Note that this only applies to bonus for armor class.
	 * @return
	 */
	int getMaxDexterityModifier();
	
	/**
	 * gets the armor check penalty.
	 * ex: Heavy encumberance causes penelty for swim checks.
	 * @return
	 */
	int getArmorCheckModifier();
}
