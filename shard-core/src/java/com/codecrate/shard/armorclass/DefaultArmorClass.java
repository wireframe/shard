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

import java.util.ArrayList;
import java.util.Collection;
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
			ArmorClassModifierType type = (ArmorClassModifierType) it.next();
			int typeTotal = 0;
			Iterator mods = ((Collection)modifiers.get(type)).iterator();
			while (mods.hasNext()) {
				ArmorClassModifier modifier = (ArmorClassModifier) mods.next();
				int modifierValue = modifier.getModifier();
				if (type.isStackable()) {
					typeTotal += modifierValue;
				} else if (typeTotal < modifierValue) {
					typeTotal = modifierValue;
				}
			}
			value += typeTotal;
		}
		return value;
	}
	
	public void addArmorClassModifier(ArmorClassModifier modifier) {
		ArmorClassModifierType type = modifier.getModifierType();
		Collection mods = getModifiers(type);
		mods.add(modifier);
		updateModifiers(type, mods);
	}
	
	public void removeArmorClassModifier(ArmorClassModifier modifier) {
		ArmorClassModifierType type = modifier.getModifierType();
		Collection mods = getModifiers(type);
		mods.remove(modifier);
		updateModifiers(type, mods);
	}
	
	private Collection getModifiers(ArmorClassModifierType type) {
		Collection mods = (Collection) modifiers.get(type);
		if (null == mods) {
			LOG.debug("No modifiers found for type: " + type);
			mods = new ArrayList();
		}
		return mods;
	}
	
	private void updateModifiers(ArmorClassModifierType type, Collection mods) {
		modifiers.put(type, mods);
	}
}
