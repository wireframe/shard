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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.codecrate.shard.ability.AbilityScore;
import com.codecrate.shard.ability.AbilityScoreContainer;
import com.codecrate.shard.ability.DefaultAbility;
import com.codecrate.shard.modifier.Modifiable;
import com.codecrate.shard.modifier.ModifiableObject;
import com.codecrate.shard.modifier.Modifier;
import com.codecrate.shard.modifier.ModifierListener;

/**
 * Initiative is used for determining when the character enters battle.
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class Initiative extends ModifiableObject implements Modifiable, ModifierListener {
    private static final Log LOG = LogFactory.getLog(Initiative.class);
    
    private AbilityScore abilityScore;
    private Modifier modifier;

    public Initiative(AbilityScoreContainer abilities) {
        super();
        if (abilities.hasAbilityScore(DefaultAbility.DEXTERITY)) {
            abilityScore = abilities.getAbilityScore(DefaultAbility.DEXTERITY);
            abilityScore.addListener(this);
        }
        onModify();
    }

    public void onModify() {
        if (null != abilityScore) {
            LOG.debug("Updating dexterity initiative modifier.");
            if (null != modifier) {
                removeModifier(modifier);
            }
            addModifier(abilityScore);
        }
    }
}
