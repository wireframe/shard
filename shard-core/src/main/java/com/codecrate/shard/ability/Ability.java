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

public enum Ability {
    STRENGTH("Strength"), 
	DEXTERITY("Dexterity"),
	CONSTITUTION("Constitution"),
	WISDOM("Wisdom"),
	INTELLIGENCE("Intelligence"),
	CHARISMA("Charisma");
	
    private final String name;
    
    Ability(String name) {
        this.name = name;
    }
    
    public String toString() {
        return name;
    }
    
    /**
     * gets the name of the ability.
     * ex: Strength
     */
    public String getName() {
        return name;
    }

    /**
     * gets the abbreviation for the ability.
     * ex: STR
     * @return
     */
    public String getAbbreviation() {
        return name.toUpperCase().substring(0, 3);
    }
    
    public static Ability findByAbbreviation(String abbrevation) {
    	for (Ability ability : values()) {
			if (ability.getAbbreviation().equals(abbrevation)) {
				return ability;
			}
		}
    	throw new IllegalArgumentException("Unable to find ability with abbreviation: " + abbrevation);
    }
}
