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

public class DefaultArmorClassModifierType implements ArmorClassModifierType {
	public static final ArmorClassModifierType DEXTERITY = new DefaultArmorClassModifierType("dexterity", false);
	public static final ArmorClassModifierType ARMOR = new DefaultArmorClassModifierType("armor", false);
	public static final ArmorClassModifierType SHIELD = new DefaultArmorClassModifierType("shield", false);
	public static final ArmorClassModifierType NATURAL = new DefaultArmorClassModifierType("natural", false);
	public static final ArmorClassModifierType SIZE= new DefaultArmorClassModifierType("size", false);
	public static final ArmorClassModifierType ENHANCEMENT = new DefaultArmorClassModifierType("enhancement", false);
	public static final ArmorClassModifierType DEFLECTION = new DefaultArmorClassModifierType("deflection", false);
	public static final ArmorClassModifierType DODGE = new DefaultArmorClassModifierType("dodge", true);
	
	
	private final String name;
	private final boolean isStackable;

	public DefaultArmorClassModifierType(String name, boolean isStackable) {
		this.name = name;
		this.isStackable = isStackable;
	}
	
	public String toString() {
		return name;
	}
	
	public boolean isStackable() {
		return isStackable;
	}
	
	public String getName() {
		return name;
	}
}
