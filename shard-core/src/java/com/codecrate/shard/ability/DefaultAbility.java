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

/**
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class DefaultAbility implements Ability {
	private static final Log LOG = LogFactory.getLog(DefaultAbility.class);
	
	private String name;
    private int baseScore;
    private CompositeAbilityModifier modifiers;
    
    public DefaultAbility(String name, int baseScore) {
    	this.name = name;
    	this.baseScore = baseScore;
    	modifiers = new CompositeAbilityModifier(name);
    }
    
    public String getName() {
    	return name;
    }
    
    public int getScore() {
    	return baseScore + modifiers.getModifier();
    }
    
    public int getModifier() {
        return (int) Math.floor((getScore() - 10) / 2);
    }

	public void addAbilityModifier(AbilityModifier modifier) {
		modifiers.addAbilityModifier(modifier);
	}
	
	public void removeAbilityModifier(AbilityModifier modifier) {
		modifiers.removeAbilityModifier(modifier);
	}
	
	
	/**
	 * Composite object to handle appending/removing multiple modifiers.
	 * 
	 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
	 */
	private static class CompositeAbilityModifier implements AbilityModifier {
		private List modifiers = new ArrayList();
		private String name;
		
		public CompositeAbilityModifier(String abilityName) {
			this.name = abilityName;
		}
		
		public String getAbilityName() {
			return name;
		}
		
		public int getModifier() {
	    	int value = 0;
	    	Iterator it = modifiers.iterator();
	    	while (it.hasNext()) {
	    		AbilityModifier modifier = (AbilityModifier) it.next();
	    		value += modifier.getModifier();
	    	}
	    	return value;
		}

		void addAbilityModifier(AbilityModifier modifier) {
			if (!name.equals(modifier.getAbilityName())) {
				LOG.error("Modifier does not apply to ability: " + name);
			} else {
				modifiers.add(modifier);
			}
		}
		
		void removeAbilityModifier(AbilityModifier modifier) {
			modifiers.remove(modifier);
		}
	}
}
