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
package com.codecrate.shard.domain;

import java.util.Collections;

import com.codecrate.shard.ability.AbilityScoreContainer;
import com.codecrate.shard.ability.DefaultAbility;
import com.codecrate.shard.ability.DefaultAbilityScore;
import com.codecrate.shard.ability.DefaultAbilityScoreContainer;

/**
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class PlayerCharacterModel {

    AbilityScoreContainer abilityScores = new DefaultAbilityScoreContainer(Collections.singletonMap(DefaultAbility.STRENGTH, new DefaultAbilityScore(DefaultAbility.STRENGTH, 14, null)));
    
    /**
     * @return Returns the abilityScore.
     */
    public AbilityScoreContainer getAbilityScores() {
        return abilityScores;
    }
    
    /**
     * @param abilityScore The abilityScore to set.
     */
    public void setAbilityScores(AbilityScoreContainer abilityScores) {
        this.abilityScores = abilityScores;
    }
}
