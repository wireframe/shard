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
package com.codecrate.shard.character;

import com.codecrate.shard.character.ability.AbilityContainer;

public interface PlayerCharacter extends AbilityContainer {
	int getChallengeRating();
	
	/**
	 * gets the base attack bonus for this character.
	 * value should be the sum of the bonuses for all character classes.
	 * @return
	 */
	int getBaseAttackBonus();
	
	/**
	 * gets the gender for the character;
	 * @return
	 */
	Gender getGender();
}