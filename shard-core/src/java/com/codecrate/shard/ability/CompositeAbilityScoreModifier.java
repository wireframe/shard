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
