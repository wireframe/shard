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

import com.codecrate.shard.armorclass.ArmorClassModifier;

/**
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public interface RacialSize  {
    int getBaseAttackBonusModifier();
    
    ArmorClassModifier getArmorClassModifier();

	/**
	 * gets the name of the racial size.
	 * @return
	 */
	String getName();
    
    //small characters 3/4 max weight of medium.
    //float getMaxWeightModifier();
    
    //small characters 2/3 max weight of medium.
    //int getMovementRateModifier();
}
