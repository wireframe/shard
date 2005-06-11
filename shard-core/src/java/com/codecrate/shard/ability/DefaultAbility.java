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

import java.util.HashMap;
import java.util.Map;

public class DefaultAbility implements Ability {
    public static final Ability STRENGTH = new DefaultAbility("Strength");
	public static final Ability DEXTERITY = new DefaultAbility("Dexterity");
	public static final Ability CONSTITUTION = new DefaultAbility("Constitution");
	public static final Ability WISDOM = new DefaultAbility("Wisdom");
	public static final Ability INTELLIGENCE = new DefaultAbility("Intelligence");
	public static final Ability CHARISMA = new DefaultAbility("Charisma");
    
	public static final Map INSTANCES = new HashMap();
	
	static {
	    INSTANCES.put(STRENGTH.toString(), STRENGTH);
	    INSTANCES.put(DEXTERITY.toString(), DEXTERITY);
	    INSTANCES.put(CONSTITUTION.toString(), CONSTITUTION);
	    INSTANCES.put(WISDOM.toString(), WISDOM);
	    INSTANCES.put(INTELLIGENCE.toString(), INTELLIGENCE);
	    INSTANCES.put(CHARISMA.toString(), CHARISMA);
	}
	
    private final String name;
    
    public DefaultAbility(String name) {
        this.name = name;
    }
    
    public String toString() {
        return name;
    }
    
    public String getName() {
        return name;
    }

    public String getAbbreviation() {
        return name.toUpperCase().substring(0, 3);
    }
    
    public static Ability getInstance(String name) {
        return (Ability) INSTANCES.get(name);
    }
}
