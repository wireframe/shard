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
 * Define AbilityModifier interface.
 * Ability modifiers can come from a variety of sources, but their purpose 
 * is to just modify an ability score.  Ex: Elf characters have bonus to DEX.
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public interface AbilityScoreModifier {

	/**
	 * gets the identifier of what ability to modify.
	 * @return ability name that modifier applies to.
	 */
	Ability getAbility();
	
	/**
	 * gets the modifier for the ability.
	 * usually ranges from -5 to 5.
	 * @return int modifier value.
	 */
	int getModifier();
}
