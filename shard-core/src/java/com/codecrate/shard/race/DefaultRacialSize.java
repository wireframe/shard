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

import com.codecrate.shard.armorclass.ArmorClassModifier;
import com.codecrate.shard.armorclass.DefaultArmorClassModifier;
import com.codecrate.shard.armorclass.DefaultArmorClassModifierType;

/**
 * Note: Large or larger creatures using reach weapons can strike up to double their natural reach 
 * but can’t strike at their natural reach or less.
 *  
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class DefaultRacialSize implements RacialSize {
    public static final RacialSize FINE = new DefaultRacialSize("Fine", 0, new DefaultArmorClassModifier(DefaultArmorClassModifierType.SIZE, 8), .5f, 0);
    public static final RacialSize DIMINUTIVE = new DefaultRacialSize("Diminutive", 0, new DefaultArmorClassModifier(DefaultArmorClassModifierType.SIZE, 4), 1, 0);
    public static final RacialSize TINY = new DefaultRacialSize("Tiny", 0, new DefaultArmorClassModifier(DefaultArmorClassModifierType.SIZE, 2), 2.5f, 0);
    public static final RacialSize SMALL = new DefaultRacialSize("Small", 1, new DefaultArmorClassModifier(DefaultArmorClassModifierType.SIZE, 1), 5, 5);
    public static final RacialSize MEDIUM = new DefaultRacialSize("Medium", 0, new DefaultArmorClassModifier(DefaultArmorClassModifierType.SIZE, 0), 5, 5);
    public static final RacialSize LARGE_TALL = new DefaultRacialSize("Large (Tall)", 0, new DefaultArmorClassModifier(DefaultArmorClassModifierType.SIZE, -1), 10, 10);
    public static final RacialSize LARGE_LONG = new DefaultRacialSize("Large (Long)", 0, new DefaultArmorClassModifier(DefaultArmorClassModifierType.SIZE, -1), 10, 5);
    public static final RacialSize HUGE_TALL = new DefaultRacialSize("Huge (Tall)", 0, new DefaultArmorClassModifier(DefaultArmorClassModifierType.SIZE, -2), 15, 15);
    public static final RacialSize HUGE_LONG = new DefaultRacialSize("Huge (Long)", 0, new DefaultArmorClassModifier(DefaultArmorClassModifierType.SIZE, -2), 15, 10);
    public static final RacialSize GARGANTUAN_TALL = new DefaultRacialSize("Gargantuan (Tall)", 0, new DefaultArmorClassModifier(DefaultArmorClassModifierType.SIZE, -4), 20, 20);
    public static final RacialSize GARGANTUAN_LONG = new DefaultRacialSize("Gargantuan (Long)", 0, new DefaultArmorClassModifier(DefaultArmorClassModifierType.SIZE, -4), 20, 15);
    public static final RacialSize COLOSSAL_TALL = new DefaultRacialSize("Colossal (Tall)", 0, new DefaultArmorClassModifier(DefaultArmorClassModifierType.SIZE, -8), 30, 30);
    public static final RacialSize COLOSSAL_LONG = new DefaultRacialSize("Colossal (Long)", 0, new DefaultArmorClassModifier(DefaultArmorClassModifierType.SIZE, -8), 30, 20);

    
    private String name;
    private ArmorClassModifier armorClassModifier;
    private int baseAttackBonusModifier;
    private float space;
    private int reach;

    public DefaultRacialSize(String name, int baseAttackBonusModifier, ArmorClassModifier armorClassModifier, float space, int reach) {
    	this.name = name;
        this.baseAttackBonusModifier = baseAttackBonusModifier;
        this.armorClassModifier = armorClassModifier;
        this.space = space;
        this.reach = reach;
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

	public ArmorClassModifier getArmorClassModifier() {
		return armorClassModifier;
	}
}
