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

import com.codecrate.shard.kit.ClassLevel;
import com.codecrate.shard.kit.DefaultClassLevel;

/**
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class CharacterLevel {

    private int level;
    private int hitpoints;
    private ClassLevel classLevel;
    
    public CharacterLevel(int level, int hitpoints, ClassLevel classLevel) {
        this.level = level;
    	this.hitpoints = hitpoints;
    	this.classLevel = classLevel;
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
}
