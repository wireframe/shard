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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.codecrate.shard.ModifiableObject;
import com.codecrate.shard.Modifier;


/**
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class DefaultAbilityScore extends ModifiableObject implements AbilityScore {
	private static final Log LOGGER = LogFactory.getLog(DefaultAbilityScore.class);
	
	private Ability ability;
    private CompositeAbilityScoreListener listeners = new CompositeAbilityScoreListener();
    
    public DefaultAbilityScore(Ability ability, int baseScore) {
        super(baseScore);
    	this.ability = ability;
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
    	int points = 0;
    	int score = getModifiedValue();
    	
    	switch (score) {
    	case 0:
    		points = -8;
    		break;
    	case 1:
    		points = -7;
    		break;
    	case 2:
    		points = -6;
    		break;
    	case 3:
    		points = -5;
    		break;
    	case 4:
    		points = -4;
    		break;
    	case 5:
    		points = -3;
    		break;
    	case 6:
    		points = -2;
    		break;
    	case 7:
    		points = -1;
    		break;
    	case 8:
    		points = 0;
    		break;
    	case 9:
    		points = 1;
    		break;
    	case 10:
    		points = 2;
    		break;
    	case 11:
    		points = 3;
    		break;
    	case 12:
    		points = 4;
    		break;
    	case 13:
    		points = 5;
    		break;
    	case 14:
    		points = 6;
    		break;
    	case 15:
    		points = 8;
    		break;
    	case 16:
    		points = 10;
    		break;
    	case 17:
    		points = 13;
    		break;
    	case 18:
    		points = 16;
    		break;
    	case 19:
    		points = 19;
    		break;
    	case 20:
    		points = 22;
    		break;
    	case 21:
    		points = 25;
    		break;
    	case 22:
    		points = 28;
    		break;
    	default:
    		LOGGER.info("Unknown point cost for score: " + score);
    	}
    	
    	return points;
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
