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

/**
 * Definition of a class level.
 * ex: Ranger level 1 grants +1 to hit ....
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class DefaultClassLevel implements ClassLevel {
	private int level;
    private int baseAttackBonus;
    private int fortituteSaveBonus;
    private int reflexSaveBonus;
    private int willpowerSaveBonus;
    private CharacterClass characterClass;

    public DefaultClassLevel(int level, CharacterClass characterClass, int baseAttackBonus, int fortitueSaveBonus, int reflexSaveBonus, int willpowerSaveBonus) {
    	this.level = level;
    	this.characterClass = characterClass;
        this.baseAttackBonus = baseAttackBonus;
        this.fortituteSaveBonus = fortitueSaveBonus;
        this.reflexSaveBonus = reflexSaveBonus;
        this.willpowerSaveBonus = willpowerSaveBonus;
    }
    
    public String toString() {
    	return characterClass + " (" + level + ")";
    }
    
    public CharacterClass getCharacterClass() {
    	return characterClass;
    }
    
    public int getLevel() {
    	return level;
    }
    
    public int getBaseAttackBonus() {
        return baseAttackBonus;
    }
    public int getFortituteSaveBonus() {
        return fortituteSaveBonus;
    }
    public int getReflexSaveBonus() {
        return reflexSaveBonus;
    }
    public int getWillpowerSaveBonus() {
        return willpowerSaveBonus;
    }
}