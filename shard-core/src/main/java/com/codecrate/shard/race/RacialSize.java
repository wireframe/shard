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
package com.codecrate.shard.race;

import java.math.BigDecimal;
import java.util.Collection;

import com.codecrate.shard.modifier.Modifier;

/**
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public interface RacialSize  {
	/**
	 * gets the base attack bonus for the creature size.
	 * @return
	 */
    int getBaseAttackBonusModifier();
    
    Modifier getArmorClassModifier();

	/**
	 * gets the name of the racial size.
	 * @return
	 */
	String getName();

	/**
	 * gets the amount of space the creature occupies.
	 * @return
	 */
	BigDecimal getSpace();

	/**
	 * gets the reach of the creature.
	 * @return
	 */
	int getReach();

	/**
	 * gets the skill modifiers imposed on this size.
	 * ex: small characters get +4 bonus to hide.
	 * @return
	 */
	Collection getSkillModifiers();

    /**
     * gets the encumberance mulitplier for creatues of this size.
     * ex: large creatures can carry 2 times as much as medium creatures.
     * @return
     */
    BigDecimal getEncumberanceMultiplier();
}
