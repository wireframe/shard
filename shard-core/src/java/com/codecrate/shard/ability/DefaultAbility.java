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

public class DefaultAbility implements Ability {
    public static final Ability STRENGTH = new DefaultAbility("strength");
	public static final Ability DEXTERITY = new DefaultAbility("dexterity");
	public static final Ability CONSTITUTION = new DefaultAbility("constitution");
	public static final Ability WISDOM = new DefaultAbility("wisdom");
	public static final Ability INTELLIGENCE = new DefaultAbility("intelligence");
	public static final Ability CHARISMA = new DefaultAbility("charisma");
    
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
}
