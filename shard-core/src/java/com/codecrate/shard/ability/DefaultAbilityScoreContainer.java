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
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.codecrate.shard.KeyedModifier;

/**
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class DefaultAbilityScoreContainer implements AbilityScoreContainer {
	private static final Log LOG = LogFactory.getLog(DefaultAbilityScoreContainer.class);
	
	private final Map scores;

	public DefaultAbilityScoreContainer(Map scores) {
	    this.scores = scores;
	}
	
	public AbilityScore getAbilityScore(Ability ability) {
	    AbilityScore score = (AbilityScore) scores.get(ability);
	    if (null == score) {
	        LOG.info("No ability score found for ability: " + ability);
	    }
		return score;
	}
	
	public boolean hasAbilityScore(Ability ability) {
	    if (null == scores.get(ability)) {
	        return false;
	    }
	    return true;
	}
	
	public String toString() {
		return getAbilityScores().toString();
	}

	public Collection getAbilityScores() {
		return scores.values();
	}
	
	public int getTotalPointScore() {
		int points = 0;
		Iterator iterator = scores.values().iterator();
		while (iterator.hasNext()) {
			AbilityScore score = (AbilityScore) iterator.next();
			points += score.getPointCost();
		}
		return points;
	}
	
    public AbilityScore getStrength() {
        return getAbilityScore(DefaultAbility.STRENGTH);
    }

    public AbilityScore getDexterity() {
        return getAbilityScore(DefaultAbility.DEXTERITY);
    }

    public AbilityScore getWisdom() {
        return getAbilityScore(DefaultAbility.WISDOM);
    }

    public AbilityScore getIntelligence() {
        return getAbilityScore(DefaultAbility.INTELLIGENCE);
    }

    public AbilityScore getConstitution() {
        return getAbilityScore(DefaultAbility.CONSTITUTION);
    }

    public AbilityScore getCharisma() {
        return getAbilityScore(DefaultAbility.CHARISMA);
    }

    public void addModifier(KeyedModifier modifier) {
        Ability ability = (Ability) modifier.getKey();
        if (!hasAbilityScore(ability)) {
            LOG.info("Ability " + ability + " does not exist to attach modifier to.");
            return;
        } 
        getAbilityScore(ability).addModifier(modifier);
    }

    public void removeModifier(KeyedModifier modifier) {
        Ability ability = (Ability) modifier.getKey();
        if (!hasAbilityScore(ability)) {
            LOG.info("Ability " + ability + " does not exist to remove modifier from.");
            return;
        } 
        getAbilityScore(ability).removeModifier(modifier);
    }
}
