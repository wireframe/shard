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
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Modifier for a specific modifier type.
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class TypeGroupedModifier implements Modifier, ModifierContainer {
    private static final Log LOG = LogFactory.getLog(TypeGroupedModifier.class);
    
    private ModifierType type;
    private Collection modifiers = new ArrayList();

    public TypeGroupedModifier(ModifierType type) {
        this.type = type;
    }
    
    public int getModifier() {
        if (!type.isStackable()) {
            return calculateNonStackableModifiers();
        }
        return calculateStackableModifiers();
    }

    private int calculateNonStackableModifiers() {
        if (0 == modifiers.size()) {
            return 0;
        }
        
		int value = Integer.MIN_VALUE;
		Iterator mods = modifiers.iterator();
		while (mods.hasNext()) {
			Modifier modifier = (Modifier) mods.next();
			int modifierValue = modifier.getModifier();
			if (value < modifierValue) {
				value = modifierValue;
			}
		}
		return value;
    }

    private int calculateStackableModifiers() {
		int value = 0;
		Iterator mods = modifiers.iterator();
		while (mods.hasNext()) {
			Modifier modifier = (Modifier) mods.next();
			int modifierValue = modifier.getModifier();
			value += modifierValue;
		}
		return value;
    }
    
    public void addModifier(Modifier modifier) {
        if (!type.equals(modifier.getModifierType())) {
            LOG.info("Modifier type does not match required type: " + type);
            return;
        }
        modifiers.add(modifier);
    }
    
    public void removeModifier(Modifier modifier) {
        modifiers.remove(modifier);
    }

    public ModifierType getModifierType() {
        return type;
    }

    public Collection getModifiers() {
        return modifiers;
    }
    
    public boolean isBonus() {
        if (0 <= getModifier()) {
            return true;
        }
        return false;
    }
    
    public boolean isPenalty() {
        return !isBonus();
    }
}
