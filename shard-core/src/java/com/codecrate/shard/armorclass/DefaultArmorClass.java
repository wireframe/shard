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

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class DefaultArmorClass implements ArmorClass {
	public static final String TYPE_DEXTERITY = "dexterity";
	public static final String TYPE_ARMOR = "armor";
	public static final String TYPE_SHIELD = "shield";
	public static final String TYPE_NATURAL = "natural";
	public static final String TYPE_SIZE = "size";
	public static final String TYPE_ENHANCEMENT = "enhancement";
	public static final String TYPE_DEFLECTION = "deflection";
	public static final String TYPE_DODGE = "dodge";
	
	private static final Log LOG = LogFactory.getLog(DefaultArmorClass.class);
	
	private int baseValue;
	private Map modifiers = new HashMap();
	
	public DefaultArmorClass() {
		this(10);
	}
	
	public DefaultArmorClass(int baseValue) {
		this.baseValue = baseValue;
	}
	
	public int getValue() {
		int value = baseValue;
		
		Iterator it = modifiers.keySet().iterator();
		while (it.hasNext()) {
			String type = (String) it.next();
			ArmorClassModifier modifier = (ArmorClassModifier) modifiers.get(type);
			value += modifier.getModifier();
		}
		return value;
	}
	
	public void addArmorClassModifier(ArmorClassModifier modifier) {
		modifiers.put(modifier.getModifierType(), modifier);
	}
	
	public void removeArmorClassModifier(ArmorClassModifier modifier) {
		modifiers.remove(modifier.getModifierType());
	}
}
