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

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Iterator;

import com.codecrate.shard.equipment.Money;

/**
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 * 
 * @see http://d20srd.org/encounterCalculator
 */
public class Encounter {
    private final Collection characters;

    public Encounter(Collection characters) {
        this.characters = characters;
    }
    
    public BigDecimal getEncounterLevel() {
        BigDecimal total = new BigDecimal(0);
        Iterator it = characters.iterator(); 
        while (it.hasNext()) {
            PlayerCharacter character = (PlayerCharacter) it.next();
            total = total.add(character.getChallengeRating());
        }
        return total.divide(new BigDecimal(characters.size()), 0);
    }
    
    public Difficulty getDifficulty() {
        return null;
    }
    
    public Money getTreasureValue() {
        return null;
    }
}
