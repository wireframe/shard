package com.codecrate.shard.modifier;

import java.util.ArrayList;
import java.util.Collection;

public class DefaultModifierContainer implements ModifierContainer {

	private Collection<Modifier> modifiers = new ArrayList<Modifier>();
	
	@Override
	public void addModifier(Modifier modifier) {
		modifiers.add(modifier);
	}

	@Override
	public Collection<Modifier> getModifiers() {
		return modifiers;
	}

	@Override
	public void removeModifier(Modifier modifier) {
		modifiers.remove(modifier);
	}

}
