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

import java.util.Collection;

import com.codecrate.shard.ModifiableObject;
import com.codecrate.shard.Modifier;


/**
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class DefaultAbilityScore extends ModifiableObject implements AbilityScore {
	private final Ability ability;
    private CompositeAbilityScoreListener listeners = new CompositeAbilityScoreListener();
    private AbilityScoreDao dao;
    
    public DefaultAbilityScore(Ability ability, int baseScore, AbilityScoreDao dao) {
        super(baseScore);
    	this.ability = ability;
    	this.dao = dao;
    }
    
    public String toString() {
        return ability + ": " + getModifiedValue() + " (" + getBonus() +")";
    }
    
    public Ability getAbility() {
    	return ability;
    }
    
    public int getBonus() {
        return (int) Math.floor((getModifiedValue() - 10) / 2);
    }
    
    public int getPointCost() {
    	return dao.getPointCost(getModifiedValue());
    }

	public void addListener(AbilityScoreListener listener) {
		listeners.addListener(listener);
	}

	public void removeListener(AbilityScoreListener listener) {
		listeners.removeListener(listener);
	}
	
	public Collection getListeners() {
	    return listeners.getListeners();
	}
	
	public void addModifier(Modifier modifier) {
	    super.addModifier(modifier);
	    listeners.onModify();
	}
	
	public void removeModifier(Modifier modifier) {
	    super.removeModifier(modifier);
	    listeners.onModify();
	}
}
