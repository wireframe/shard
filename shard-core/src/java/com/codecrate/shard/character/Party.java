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

/**
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class Party {
    private final Collection characters;

    public Party(Collection characters) {
        this.characters = characters;
    }
    
    /**
     * returns the average ECL for a 4 character party.
     * parties with more or less characters have a modified level.
     */
    public int getLevel() {
    
        return 0;
    }
    
    /**
     * average challange rating for the party.
     * @return
     */
    public BigDecimal getChallengeRating() {
        BigDecimal total = new BigDecimal(0);
        Iterator it = characters.iterator(); 
        while (it.hasNext()) {
            PlayerCharacter character = (PlayerCharacter) it.next();
            total = total.add(character.getChallengeRating());
        }
        return total.divide(new BigDecimal(characters.size()), 0);
    }
}
