package com.codecrate.shard.armorclass;

import junit.framework.TestCase;

import org.easymock.MockControl;

import com.codecrate.shard.ability.AbilityScoreContainer;
import com.codecrate.shard.ability.DefaultAbility;
import com.codecrate.shard.ability.DefaultAbilityScore;

public class DexterityArmorClassTest extends TestCase {

    public void testListenerRegisteredOnDexterity() {
        DefaultAbilityScore abilityScore = new DefaultAbilityScore(DefaultAbility.DEXTERITY, 1);
        
        MockControl mockAbilities = MockControl.createControl(AbilityScoreContainer.class);
        AbilityScoreContainer abilities = (AbilityScoreContainer) mockAbilities.getMock();
        abilities.hasAbilityScore(DefaultAbility.DEXTERITY);
        mockAbilities.setReturnValue(true);
        abilities.getAbilityScore(DefaultAbility.DEXTERITY);
        mockAbilities.setReturnValue(abilityScore);
        mockAbilities.replay();
        
        new DexterityArmorClass(abilities, new DefaultArmorClass());
        assertEquals(1, abilityScore.getListeners().size());
    }
    
    public void testDexModifierAttachedToArmorClass() {
        DefaultAbilityScore abilityScore = new DefaultAbilityScore(DefaultAbility.DEXTERITY, 1);
        
        MockControl mockAbilities = MockControl.createControl(AbilityScoreContainer.class);
        AbilityScoreContainer abilities = (AbilityScoreContainer) mockAbilities.getMock();
        abilities.hasAbilityScore(DefaultAbility.DEXTERITY);
        mockAbilities.setReturnValue(true);
        abilities.getAbilityScore(DefaultAbility.DEXTERITY);
        mockAbilities.setReturnValue(abilityScore);
        mockAbilities.replay();
        
        DefaultArmorClass defaultArmorClass = new DefaultArmorClass();
        new DexterityArmorClass(abilities, defaultArmorClass);
        assertEquals(6, defaultArmorClass.getValue());
    }
}
