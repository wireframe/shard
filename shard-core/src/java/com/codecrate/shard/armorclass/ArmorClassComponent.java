package com.codecrate.shard.armorclass;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class ArmorClassComponent {
    
    private ArmorClassModifierType type;
    private Collection modifiers = new ArrayList();

    public ArmorClassComponent(ArmorClassModifierType type) {
        this.type = type;
    }
    
    public int getValue() {
		int value = 0;
		Iterator mods = modifiers.iterator();
		while (mods.hasNext()) {
			ArmorClassModifier modifier = (ArmorClassModifier) mods.next();
			int modifierValue = modifier.getModifier();
			if (type.isStackable()) {
				value += modifierValue;
			} else if (value < modifierValue) {
				value = modifierValue;
			}
		}
		return value;
    }
    
    public void addModifier(ArmorClassModifier modifier) {
        modifiers.add(modifier);
    }
    
    public void removeModifier(ArmorClassModifier modifier) {
        modifiers.remove(modifier);
    }
}
