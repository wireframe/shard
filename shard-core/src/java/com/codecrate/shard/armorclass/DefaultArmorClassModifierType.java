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

import com.codecrate.shard.DefaultModifierType;
import com.codecrate.shard.ModifierType;

public class DefaultArmorClassModifierType extends DefaultModifierType implements ModifierType {
	public static final ModifierType DEXTERITY = new DefaultArmorClassModifierType("dexterity", false);
	public static final ModifierType ARMOR = new DefaultArmorClassModifierType("armor", false);
	public static final ModifierType SHIELD = new DefaultArmorClassModifierType("shield", false);
	public static final ModifierType NATURAL = new DefaultArmorClassModifierType("natural", false);
	public static final ModifierType SIZE= new DefaultArmorClassModifierType("size", false);
	public static final ModifierType ENHANCEMENT = new DefaultArmorClassModifierType("enhancement", false);
	public static final ModifierType DEFLECTION = new DefaultArmorClassModifierType("deflection", false);
	public static final ModifierType DODGE = new DefaultArmorClassModifierType("dodge", true);
	
	public DefaultArmorClassModifierType(String name, boolean isStackable) {
	    super(name, isStackable);
	}
}
