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
package com.codecrate.shard.save;

import com.codecrate.shard.ability.Ability;

public enum SavingThrow {
    REFLEX("Reflex", Ability.DEXTERITY),
	FORTITUDE("Fortitude", Ability.CONSTITUTION),
	WILLPOWER("Willpower", Ability.WISDOM);
    
    private final String name;
    private final Ability ability;
    
    SavingThrow(String name, Ability ability) {
        this.name = name;
        this.ability = ability;
    }
    
    /**
     * gets the ability used to add additional modifiers.
     * @return
     */
    public Ability getAbility() {
        return ability;
    }
    
    public String getName() {
        return name;
    }

    public String getAbbreviation() {
        return name.toUpperCase().substring(0, 3);
    }
}
