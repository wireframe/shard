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

import com.codecrate.shard.modifier.DefaultModifierType;
import com.codecrate.shard.modifier.ModifiableObject;
import com.codecrate.shard.modifier.ModifierType;

/**
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class DefaultArmorClass extends ModifiableObject implements ArmorClass {
	public static final ModifierType ARMOR = new DefaultModifierType("armor", false);
	public static final ModifierType SHIELD = new DefaultModifierType("shield", false);
	public static final ModifierType NATURAL = new DefaultModifierType("natural", false);
	public static final ModifierType SIZE= new DefaultModifierType("size", false);
	public static final ModifierType ENHANCEMENT = new DefaultModifierType("enhancement", false);
	public static final ModifierType DEFLECTION = new DefaultModifierType("deflection", false);
	public static final ModifierType DODGE = new DefaultModifierType("dodge", true);

	public static final int BASE_ARMOR_CLASS = 10;
	
	public DefaultArmorClass() {
	    super(BASE_ARMOR_CLASS);
	}
}
