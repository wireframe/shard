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
package com.codecrate.shard;

/**
 * Generic modifier for shard objects.
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public interface Modifier {

    /**
     * gets the type of modifier.
     * the modifier type determines whether or not multiple modifiers can 
     * stack.
     * @return
     */
	ModifierType getModifierType();
	
	/**
	 * gets the value of the modifier.
	 * ex: +4 or -2
	 * @return
	 */
	int getModifier();
	
	/**
	 * flag for if the modifier is a bonus.
	 * @return
	 */
	boolean isBonus();
	
	/**
	 * flag for if the modifier is a penalty.
	 * @return
	 */
	boolean isPenalty();
}
