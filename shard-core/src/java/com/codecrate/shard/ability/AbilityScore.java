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

import com.codecrate.shard.ModifierContainer;

/**
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public interface AbilityScore extends ModifierContainer {
	/**
	 * gets the name of the ability. 
	 * @return
	 */
	Ability getAbility();
	
	/**
	 * gets the current value of the ability.
	 * includes modifiers.
	 * @return value.
	 */
	int getScore();

	/**
	 * gets the point cost associated with the score.
	 * @return
	 */
	int getPointCost();
	
	/**
	 * gets the bonus from the ability value.
	 * ex: value of 10 = bonus of 0
	 * @return bonus.
	 */
	int getModifier();

	/**
	 * adds a listener.
	 * @param listener
	 */
	void addListener(AbilityScoreListener listener);
	
	/**
	 * removes a listener.
	 * @param listener
	 */
	void removeListener(AbilityScoreListener listener);

    /**
     * gets the listeners attached to this ability score.
     * @return
     */
    Collection getListeners();
}