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
package com.codecrate.shard.armorclass;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.codecrate.shard.DefaultModifier;
import com.codecrate.shard.ModifiableObject;
import com.codecrate.shard.Modifier;
import com.codecrate.shard.ModifierListener;
import com.codecrate.shard.ability.AbilityScore;
import com.codecrate.shard.ability.AbilityScoreContainer;
import com.codecrate.shard.ability.DefaultAbility;
import com.codecrate.shard.movement.Encumberance;

/**
 * ArmorClass that handles dexterity modifiers.
 * Takes into account encumberance and armor worn to limit the dex modifier.
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class DexterityArmorClass extends ModifiableObject implements ArmorClass, ModifierListener {
    private static final Log LOG = LogFactory.getLog(DexterityArmorClass.class);
    
    private final Encumberance encumberance;
    private AbilityScore abilityScore;
    private Modifier modifier;

    public DexterityArmorClass(AbilityScoreContainer abilities, Encumberance encumberance) {
        super(DefaultArmorClass.BASE_ARMOR_CLASS);
        this.encumberance = encumberance;
        
        if (abilities.hasAbilityScore(DefaultAbility.DEXTERITY)) {
            abilityScore = abilities.getAbilityScore(DefaultAbility.DEXTERITY);
            abilityScore.addListener(this);
        }
        onModify();
    }
    
    public void onModify() {
        if (null != abilityScore) {
            LOG.debug("Updating dexterity armor class modifier.");
            if (null != modifier) {
                removeModifier(modifier);
            }
            int value = abilityScore.getModifier();
            int maxValue = encumberance.getMaxDexterityModifier();
            if (value > maxValue) {
                LOG.info("Encumberance limits dexterity modifier to " + maxValue);
                value = maxValue;
            }
            modifier = new DefaultModifier(DefaultArmorClass.DEXTERITY, value);
            addModifier(modifier);
        }
    }
}
