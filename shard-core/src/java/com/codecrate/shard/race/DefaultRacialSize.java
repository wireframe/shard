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
import com.codecrate.shard.armorclass.DefaultArmorClass;

/**
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class DefaultRacialSize implements RacialSize {
    public static final RacialSize FINE = new DefaultRacialSize(0, 8);
    public static final RacialSize DIMINUTIVE = new DefaultRacialSize(0, 4);
    public static final RacialSize TINY = new DefaultRacialSize(0, 2);
    public static final RacialSize SMALL = new DefaultRacialSize(1, 1);
    public static final RacialSize MEDIUM = new DefaultRacialSize(0, 0);
    public static final RacialSize LARGE = new DefaultRacialSize(0, -1);
    public static final RacialSize HUGE = new DefaultRacialSize(0, -2);
    public static final RacialSize GARGANTUAN = new DefaultRacialSize(0, -4);
    public static final RacialSize COLOSSAL = new DefaultRacialSize(0, -8);

    
    private ArmorClassModifier armorClassModifier;
    private int baseAttackBonusModifier;

    public DefaultRacialSize(int armorClassModifier, int baseAttackBonusModifier) {
        this.armorClassModifier = new RacialSizeArmorClassModifier(armorClassModifier);
        this.baseAttackBonusModifier = baseAttackBonusModifier;
    }
    
    public int getBaseAttackBonusModifier() {
        return baseAttackBonusModifier;
    }
    
	public String getModifierType() {
		return armorClassModifier.getModifierType();
	}

	public int getModifier() {
		return armorClassModifier.getModifier();
	}

	
    private static class RacialSizeArmorClassModifier implements ArmorClassModifier {

    	private int modifier;
    	
    	public RacialSizeArmorClassModifier(int modifier) {
    		this.modifier = modifier;
    	}
    	
		public String getModifierType() {
			return DefaultArmorClass.TYPE_SIZE;
		}

		public int getModifier() {
			return modifier;
		}
    	
    }
}
