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
import java.util.Iterator;

/**
 * ArmorClass Modifier for a specific modifier type.
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class CompositeArmorClassModifier implements ArmorClassModifier {
    
    private ArmorClassModifierType type;
    private Collection modifiers = new ArrayList();

    public CompositeArmorClassModifier(ArmorClassModifierType type) {
        this.type = type;
    }
    
    public int getModifier() {
        if (!type.isStackable()) {
            return calculateNonStackableModifiers();
        }
        return calculateStackableModifiers();
    }

    private int calculateNonStackableModifiers() {
		int value = -100;
		Iterator mods = modifiers.iterator();
		while (mods.hasNext()) {
			ArmorClassModifier modifier = (ArmorClassModifier) mods.next();
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
			ArmorClassModifier modifier = (ArmorClassModifier) mods.next();
			int modifierValue = modifier.getModifier();
			value += modifierValue;
		}
		return value;
    }
    
    public void addArmorClassModifier(ArmorClassModifier modifier) {
        modifiers.add(modifier);
    }
    
    public void removeArmorClassModifier(ArmorClassModifier modifier) {
        modifiers.remove(modifier);
    }

    public ArmorClassModifierType getModifierType() {
        return type;
    }
}
