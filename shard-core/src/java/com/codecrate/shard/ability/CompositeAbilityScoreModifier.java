package com.codecrate.shard.ability;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CompositeAbilityScoreModifier implements AbilityScoreModifier {
    private static final Log LOG = LogFactory.getLog(CompositeAbilityScoreModifier.class);
    
	private List modifiers = new ArrayList();
	private Ability ability;
	
	public CompositeAbilityScoreModifier(Ability ability) {
		this.ability = ability;
	}
	
	public Ability getAbility() {
		return ability;
	}
	
	public int getModifier() {
    	int value = 0;
    	Iterator it = modifiers.iterator();
    	while (it.hasNext()) {
    		AbilityScoreModifier modifier = (AbilityScoreModifier) it.next();
    		value += modifier.getModifier();
    	}
    	return value;
	}

	void addAbilityModifier(AbilityScoreModifier modifier) {
		if (!ability.equals(modifier.getAbility())) {
			LOG.error("Modifier does not apply to ability: " + ability);
		} else {
			modifiers.add(modifier);
		}
	}
	
	void removeAbilityModifier(AbilityScoreModifier modifier) {
		modifiers.remove(modifier);
	}
}
