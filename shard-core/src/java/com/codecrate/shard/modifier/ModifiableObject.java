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
package com.codecrate.shard.modifier;

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
public class ModifiableObject implements Modifiable, ModifierListenerContainer {
	private static final Log LOG = LogFactory.getLog(ModifiableObject.class);
	
	private int baseValue;
	private Map modifiers = new HashMap();
	private CompositeModifierListener listeners = new CompositeModifierListener();
	
	public ModifiableObject() {
		this(0);
	}
	
	public ModifiableObject(int baseValue) {
		this.baseValue = baseValue;
	}
	
	public int getValue() {
	    return baseValue;
	}
	
	public int getModifiedValue() {
		int value = baseValue;
		
		Iterator it = modifiers.keySet().iterator();
		while (it.hasNext()) {
			ModifierType type = (ModifierType) it.next();
			Collection typeModifiers = (Collection) modifiers.get(type);
			value += type.calculateModifier(typeModifiers);
		}
		return value;
	}
	
	public void addModifier(Modifier modifier) {
		ModifierType type = modifier.getModifierType();
		Collection typeModifiers = getModifiers(type);
		typeModifiers.add(modifier);
		updateModifier(type, typeModifiers);
	}
	
	public void removeModifier(Modifier modifier) {
		ModifierType type = modifier.getModifierType();
		Collection typeModifiers = getModifiers(type);
		typeModifiers.remove(modifier);
		updateModifier(type, typeModifiers);
	}
	
	protected Collection getModifiers(ModifierType type) {
		Collection typeModifiers = (Collection) modifiers.get(type);
		if (null == typeModifiers) {
			LOG.debug("No modifiers found for type: " + type);
			typeModifiers = new ArrayList();
		}
		return typeModifiers;
	}
	
	private void updateModifier(ModifierType type, Collection typeModifiers) {
		modifiers.put(type, typeModifiers);
		listeners.onModify();
	}

	public Collection getModifiers() {
		return modifiers.values();
	}
    /**
     * @param listener
     */
    public void addListener(ModifierListener listener) {
        listeners.addListener(listener);
    }
    /**
     * @return
     */
    public Collection getListeners() {
        return listeners.getListeners();
    }
    /**
     * @param listener
     */
    public void removeListener(ModifierListener listener) {
        listeners.removeListener(listener);
    }

    /**
     * @param i
     */
    public void setValue(int newValue) {
        this.baseValue = newValue;
    }
}
