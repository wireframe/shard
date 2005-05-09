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
package com.codecrate.shard.equipment;

import com.codecrate.shard.dice.Dice;
import com.codecrate.shard.race.RacialSize;

/**
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public interface Weapon extends Item {

	/**
	 * gets the category of this weapon (simple, martial, exotic).
	 * @return
	 */
	String getCategory();
	
	/**
	 * gets the type of this weapon (slashing, piercing, bludgening).
	 * @return
	 */
	String getType();
	
	/**
	 * gets the damage that the weapon does.
	 * @return
	 */
    Dice getDamage();
    
    /**
     * gets the size that this weapon is balanced for.
     * this is important because a short sword to a large character isn't 
     * balanced the same as a long sword to a small character.
     * @return
     */
    RacialSize getIntendedSize();
    
    /**
     * gets the multiplier used for a critical hit.
     * @return
     */
    int getCriticalMultiplier();
    
    /**
     * gets the number of values that count as a threat.
     * ex: weapons with a threat range of zero only "threat" 
     * on a roll of twenty.
     * @return
     */
    int getThreatRange();
}
