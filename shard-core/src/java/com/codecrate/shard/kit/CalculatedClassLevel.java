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
    private ClassLevel delegate;

    public CalculatedClassLevel(int level, CharacterClass characterClass, 
            LevelCalculator baseAttackCalculator, LevelCalculator fortitudeSaveCalculator, 
            LevelCalculator reflexSaveCalculator, LevelCalculator willpowerSaveCalculator) {
        delegate = new DefaultClassLevel(level, characterClass, 
                baseAttackCalculator.calculateValue(level),
                fortitudeSaveCalculator.calculateValue(level),
                reflexSaveCalculator.calculateValue(level),
                willpowerSaveCalculator.calculateValue(level));
    }
    
    public String toString() {
    	return delegate.toString();
    }
    
    public CharacterClass getCharacterClass() {
    	return delegate.getCharacterClass();
    }
    
    public int getLevel() {
    	return delegate.getLevel();
    }
    
    public int getBaseAttackBonus() {
        return delegate.getBaseAttackBonus();
    }
    public int getFortituteSaveBonus() {
        return delegate.getFortituteSaveBonus();
    }
    public int getReflexSaveBonus() {
        return delegate.getReflexSaveBonus();
    }
    public int getWillpowerSaveBonus() {
        return delegate.getWillpowerSaveBonus();
    }
}
