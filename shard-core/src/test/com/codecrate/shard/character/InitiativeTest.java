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

import junit.framework.TestCase;

import org.easymock.MockControl;

import com.codecrate.shard.DefaultModifier;
import com.codecrate.shard.DefaultModifierType;
import com.codecrate.shard.ModifierType;
import com.codecrate.shard.ability.AbilityScoreContainer;
import com.codecrate.shard.ability.DefaultAbility;
import com.codecrate.shard.ability.DefaultAbilityScore;

public class InitiativeTest extends TestCase {

    public void testListenerRegisteredOnDexterity() {
        DefaultAbilityScore abilityScore = new DefaultAbilityScore(DefaultAbility.DEXTERITY, 1, null);
        
        MockControl mockAbilities = MockControl.createControl(AbilityScoreContainer.class);
        AbilityScoreContainer abilities = (AbilityScoreContainer) mockAbilities.getMock();
        abilities.hasAbilityScore(DefaultAbility.DEXTERITY);
        mockAbilities.setReturnValue(true);
        abilities.getAbilityScore(DefaultAbility.DEXTERITY);
        mockAbilities.setReturnValue(abilityScore);
        mockAbilities.replay();
        
        new Initiative(abilities);
        assertEquals(1, abilityScore.getListeners().size());
    }
    
    public void testDexModifierAttachedToInitiative() {
        DefaultAbilityScore abilityScore = new DefaultAbilityScore(DefaultAbility.DEXTERITY, 18, null);
        
        MockControl mockAbilities = MockControl.createControl(AbilityScoreContainer.class);
        AbilityScoreContainer abilities = (AbilityScoreContainer) mockAbilities.getMock();
        abilities.hasAbilityScore(DefaultAbility.DEXTERITY);
        mockAbilities.setReturnValue(true);
        abilities.getAbilityScore(DefaultAbility.DEXTERITY);
        mockAbilities.setReturnValue(abilityScore);
        mockAbilities.replay();
        
        Initiative initiative = new Initiative(abilities);
        assertEquals(4, initiative.getModifiedValue());
    }
    
    public void testInitiativeUpdatedWhenAbilityChanged() {
        ModifierType type = new DefaultModifierType("type", true);
        DefaultAbilityScore abilityScore = new DefaultAbilityScore(DefaultAbility.DEXTERITY, 10, null);
        
        MockControl mockAbilities = MockControl.createControl(AbilityScoreContainer.class);
        AbilityScoreContainer abilities = (AbilityScoreContainer) mockAbilities.getMock();
        abilities.hasAbilityScore(DefaultAbility.DEXTERITY);
        mockAbilities.setReturnValue(true);
        abilities.getAbilityScore(DefaultAbility.DEXTERITY);
        mockAbilities.setReturnValue(abilityScore);
        mockAbilities.replay();
        
        Initiative initiative = new Initiative(abilities);
        abilityScore.addModifier(new DefaultModifier(type, 8));
        assertEquals(4, initiative.getModifiedValue());
    }
}
