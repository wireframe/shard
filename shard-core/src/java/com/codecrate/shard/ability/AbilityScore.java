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

import com.codecrate.shard.Modifiable;
import com.codecrate.shard.Modifier;

/**
 * Ability score represents a characters individual statistic.
 * ex: Strength=10
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public interface AbilityScore extends Modifiable, Modifier {
	/**
	 * gets the name of the ability. 
	 * @return
	 */
	Ability getAbility();
	
	/**
	 * gets the point cost associated with the score.
	 * @return
	 */
	int getPointCost();
}