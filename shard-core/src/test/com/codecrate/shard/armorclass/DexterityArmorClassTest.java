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

import junit.framework.TestCase;

import org.easymock.MockControl;

import com.codecrate.shard.DefaultModifier;
import com.codecrate.shard.DefaultModifierType;
import com.codecrate.shard.Modifier;
import com.codecrate.shard.ModifierType;
import com.codecrate.shard.ability.AbilityScoreContainer;
import com.codecrate.shard.ability.DefaultAbility;
import com.codecrate.shard.ability.DefaultAbilityScore;
import com.codecrate.shard.movement.DefaultEncumberance;

public class DexterityArmorClassTest extends TestCase {

    public void testListenerRegisteredOnDexterity() {
        DefaultAbilityScore abilityScore = new DefaultAbilityScore(DefaultAbility.DEXTERITY, 1, null);
        
        MockControl mockAbilities = MockControl.createControl(AbilityScoreContainer.class);
        AbilityScoreContainer abilities = (AbilityScoreContainer) mockAbilities.getMock();
        abilities.hasAbilityScore(DefaultAbility.DEXTERITY);
        mockAbilities.setReturnValue(true);
        abilities.getAbilityScore(DefaultAbility.DEXTERITY);
        mockAbilities.setReturnValue(abilityScore);
        mockAbilities.replay();
        
        new DexterityArmorClass(abilities, DefaultEncumberance.LIGHT);
        assertEquals(1, abilityScore.getListeners().size());
    }
    
    public void testDexModifierAttachedToArmorClass() {
        DefaultAbilityScore abilityScore = new DefaultAbilityScore(DefaultAbility.DEXTERITY, 1, null);
        
        MockControl mockAbilities = MockControl.createControl(AbilityScoreContainer.class);
        AbilityScoreContainer abilities = (AbilityScoreContainer) mockAbilities.getMock();
        abilities.hasAbilityScore(DefaultAbility.DEXTERITY);
        mockAbilities.setReturnValue(true);
        abilities.getAbilityScore(DefaultAbility.DEXTERITY);
        mockAbilities.setReturnValue(abilityScore);
        mockAbilities.replay();
        
        DexterityArmorClass defaultArmorClass = new DexterityArmorClass(abilities, DefaultEncumberance.LIGHT);
        assertEquals(6, defaultArmorClass.getModifiedValue());
    }
    
    public void testArmorClassUpdatedWhenAbilityChanged() {
        DefaultAbilityScore abilityScore = new DefaultAbilityScore(DefaultAbility.DEXTERITY, 10, null);

        ModifierType type = new DefaultModifierType("type", true);
        Modifier modifier = new DefaultModifier(type, 8);

        MockControl mockAbilities = MockControl.createControl(AbilityScoreContainer.class);
        AbilityScoreContainer abilities = (AbilityScoreContainer) mockAbilities.getMock();
        abilities.hasAbilityScore(DefaultAbility.DEXTERITY);
        mockAbilities.setReturnValue(true);
        abilities.getAbilityScore(DefaultAbility.DEXTERITY);
        mockAbilities.setReturnValue(abilityScore);
        mockAbilities.replay();
        
        DexterityArmorClass armorClass = new DexterityArmorClass(abilities, DefaultEncumberance.LIGHT);
        abilityScore.addModifier(modifier);
        assertEquals(14, armorClass.getModifiedValue());
    }
    
    public void testModifierLimitedToEncumberanceMaxValue() {
        DefaultAbilityScore abilityScore = new DefaultAbilityScore(DefaultAbility.DEXTERITY, 10, null);
        
        ModifierType type = new DefaultModifierType("type", true);
        Modifier modifier = new DefaultModifier(type, 8);

        MockControl mockAbilities = MockControl.createControl(AbilityScoreContainer.class);
        AbilityScoreContainer abilities = (AbilityScoreContainer) mockAbilities.getMock();
        abilities.hasAbilityScore(DefaultAbility.DEXTERITY);
        mockAbilities.setReturnValue(true);
        abilities.getAbilityScore(DefaultAbility.DEXTERITY);
        mockAbilities.setReturnValue(abilityScore);
        mockAbilities.replay();
        
        DexterityArmorClass defaultArmorClass = new DexterityArmorClass(abilities, DefaultEncumberance.HEAVY);
        abilityScore.addModifier(modifier);
        assertEquals(11, defaultArmorClass.getModifiedValue());
    }
}
