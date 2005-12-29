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
package com.codecrate.shard.race;

import java.math.BigDecimal;
import java.util.Collection;

import com.codecrate.shard.modifier.Modifier;

/**
 * Note: Large or larger creatures using reach weapons can strike up to double their natural reach
 * but can�t strike at their natural reach or less.
 *
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class DefaultRacialSize implements RacialSize {
    private final String name;
    private final Modifier armorClassModifier;
    private final int baseAttackBonusModifier;
    private final BigDecimal space;
    private final int reach;
	private final Collection skillModifiers;
    private final BigDecimal encumberanceMultiplier;

    public DefaultRacialSize(String name, int baseAttackBonusModifier,
    		Modifier armorClassModifier, BigDecimal space, int reach,
			Collection skillModifiers, BigDecimal encumberanceMultiplier) {
    	this.name = name;
        this.baseAttackBonusModifier = baseAttackBonusModifier;
        this.armorClassModifier = armorClassModifier;
        this.space = space;
        this.reach = reach;
		this.skillModifiers = skillModifiers;
        this.encumberanceMultiplier = encumberanceMultiplier;
    }

    public String toString() {
    	return name;
    }

    public String getName() {
    	return name;
    }

    public int getBaseAttackBonusModifier() {
        return baseAttackBonusModifier;
    }

    public Modifier getArmorClassModifier() {
		return armorClassModifier;
	}

	public BigDecimal getSpace() {
		return space;
	}

	public int getReach() {
		return reach;
	}

	public Collection getSkillModifiers() {
		return skillModifiers;
	}

	public BigDecimal getEncumberanceMultiplier() {
	    return encumberanceMultiplier;
	}
}
