package com.codecrate.shard.modifier;

import java.util.ArrayList;
import java.util.Collection;

public class DefaultModifierContainer implements ModifierContainer {

	private Collection<Modifier> modifiers = new ArrayList<Modifier>();
	
	
	public void addModifier(Modifier modifier) {
		modifiers.add(modifier);
	}

	
	public Collection<Modifier> getModifiers() {
		return modifiers;
	}

	
	public void removeModifier(Modifier modifier) {
		modifiers.remove(modifier);
	}

}
