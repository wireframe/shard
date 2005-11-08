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

public interface CharacterLevel {
    /**
     * gets the player character this level belongs to.
     * @return
     */
    PlayerCharacter getCharacter();
    
    int getLevel();

    ClassLevel getClassLevel();

    int getHitpoints();
    
    /**
     * gets the skills selected for this level.
     * @return
     */
    Collection getSkillRanks();

    /**
     * gets the number of skill points awarded for this level.
     * the first level granted to a character will have 4 times the normal 
     * amount.
     * @return
     */
    int getSkillPoints();
}