package com.codecrate.shard.character.armorclass;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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
