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
package com.codecrate.shard.armorclass;

import java.util.Collection;

/**
 * Defines an armor class.
 * armor class value is changed using <code>ArmorClassModifier</code>s.  
 * this allows for decoupling between the different factors that change 
 * the armor class value (ex: magic, equipment, dexterity, etc).
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public interface ArmorClass {

	int getValue();
	
	void addArmorClassModifier(ArmorClassModifier modifier);
	
	void removeArmorClassModifier(ArmorClassModifier modifier);

	/**
	 * gets the modifiers for the armor class.
	 * @return
	 */
	Collection getModifiers();
}
