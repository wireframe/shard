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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.codecrate.shard.Modifier;
import com.codecrate.shard.ModifierType;
import com.codecrate.shard.TypeGroupedModifier;

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
			ModifierType type = (ModifierType) it.next();
			TypeGroupedModifier modifier = (TypeGroupedModifier) modifiers.get(type);
            value += modifier.getModifier();
		}
		return value;
	}
	
	public void addModifier(Modifier modifier) {
		ModifierType type = modifier.getModifierType();
		TypeGroupedModifier modifiers = getModifier(type);
		modifiers.addModifier(modifier);
		updateModifier(type, modifiers);
	}
	
	public void removeModifier(Modifier modifier) {
		ModifierType type = modifier.getModifierType();
		TypeGroupedModifier modifiers = getModifier(type);
		modifiers.removeModifier(modifier);
		updateModifier(type, modifiers);
	}
	
	private TypeGroupedModifier getModifier(ModifierType type) {
	    TypeGroupedModifier modifier = (TypeGroupedModifier) modifiers.get(type);
		if (null == modifier) {
			LOG.debug("No modifiers found for type: " + type);
			modifier = new TypeGroupedModifier(type);
		}
		return modifier;
	}
	
	private void updateModifier(ModifierType type, TypeGroupedModifier modifier) {
		modifiers.put(type, modifier);
	}

	public Collection getModifiers() {
		return modifiers.values();
	}
}
