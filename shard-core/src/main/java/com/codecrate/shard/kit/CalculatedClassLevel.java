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
package com.codecrate.shard.kit;

import com.codecrate.shard.level.LevelCalculator;

/**
 * Definition of a class level.
 * ex: Ranger level 1 grants +1 to hit ....
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class CalculatedClassLevel implements ClassLevel {

    private final int level;
	private final CharacterClass characterClass;
	private final LevelCalculator baseAttackCalculator;
	private final LevelCalculator fortitudeSaveCalculator;
	private final LevelCalculator reflexSaveCalculator;
	private final LevelCalculator willpowerSaveCalculator;
    
	public CalculatedClassLevel(int level, CharacterClass characterClass, 
            LevelCalculator baseAttackCalculator, LevelCalculator fortitudeSaveCalculator, 
            LevelCalculator reflexSaveCalculator, LevelCalculator willpowerSaveCalculator) {
				this.level = level;
				this.characterClass = characterClass;
				this.baseAttackCalculator = baseAttackCalculator;
				this.fortitudeSaveCalculator = fortitudeSaveCalculator;
				this.reflexSaveCalculator = reflexSaveCalculator;
				this.willpowerSaveCalculator = willpowerSaveCalculator;
    }
    
    public CharacterClass getCharacterClass() {
    	return characterClass;
    }
    
    public int getLevel() {
    	return level;
    }
    
    public int getBaseAttackBonus() {
        return baseAttackCalculator.calculateValue(level);
    }
    public int getFortituteSaveBonus() {
        return fortitudeSaveCalculator.calculateValue(level);
    }
    public int getReflexSaveBonus() {
        return reflexSaveCalculator.calculateValue(level);
    }
    public int getWillpowerSaveBonus() {
        return willpowerSaveCalculator.calculateValue(level);
    }
}
