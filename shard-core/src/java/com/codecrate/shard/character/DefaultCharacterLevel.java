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
package com.codecrate.shard.character;

import java.util.Collection;

import com.codecrate.shard.kit.ClassLevel;

/**
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class DefaultCharacterLevel implements CharacterLevel {

    private final int level;
    private final int hitpoints;
    private final ClassLevel classLevel;
	private final Collection skillRanks;
    
    public DefaultCharacterLevel(int level, int hitpoints, 
    		ClassLevel classLevel, Collection skillRanks) {
        this.level = level;
    	this.hitpoints = hitpoints;
    	this.classLevel = classLevel;
		this.skillRanks = skillRanks;
    }
    
    public int getLevel() {
        return level;
    }
    
	public ClassLevel getClassLevel() {
		return classLevel;
	}
	
	public int getHitpoints() {
		return hitpoints;
	}
	
	public Collection getSkillRanks() {
		return skillRanks;
	}
}
