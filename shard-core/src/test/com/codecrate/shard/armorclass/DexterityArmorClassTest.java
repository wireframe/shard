package com.codecrate.shard.armorclass;

import junit.framework.TestCase;

import org.easymock.MockControl;

import com.codecrate.shard.ability.AbilityScoreContainer;
import com.codecrate.shard.ability.DefaultAbility;
import com.codecrate.shard.ability.DefaultAbilityScore;
import com.codecrate.shard.ability.DefaultAbilityScoreModifier;
import com.codecrate.shard.movement.DefaultEncumberance;

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
        
        new DexterityArmorClass(abilities, DefaultEncumberance.LIGHT, new DefaultArmorClass());
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
        new DexterityArmorClass(abilities, DefaultEncumberance.LIGHT, defaultArmorClass);
        assertEquals(6, defaultArmorClass.getValue());
    }
    
    public void testArmorClassUpdatedWhenAbilityChanged() {
        DefaultAbilityScore abilityScore = new DefaultAbilityScore(DefaultAbility.DEXTERITY, 1);
        
        MockControl mockAbilities = MockControl.createControl(AbilityScoreContainer.class);
        AbilityScoreContainer abilities = (AbilityScoreContainer) mockAbilities.getMock();
        abilities.hasAbilityScore(DefaultAbility.DEXTERITY);
        mockAbilities.setReturnValue(true);
        abilities.getAbilityScore(DefaultAbility.DEXTERITY);
        mockAbilities.setReturnValue(abilityScore);
        mockAbilities.replay();
        
        DefaultArmorClass defaultArmorClass = new DefaultArmorClass();
        new DexterityArmorClass(abilities, DefaultEncumberance.LIGHT, defaultArmorClass);
        abilityScore.addAbilityModifier(new DefaultAbilityScoreModifier(DefaultAbility.DEXTERITY, 9));
        assertEquals(10, defaultArmorClass.getValue());
    }
    
    public void testModifierLimitedToEncumberanceMaxValue() {
        DefaultAbilityScore abilityScore = new DefaultAbilityScore(DefaultAbility.DEXTERITY, 10);
        
        MockControl mockAbilities = MockControl.createControl(AbilityScoreContainer.class);
        AbilityScoreContainer abilities = (AbilityScoreContainer) mockAbilities.getMock();
        abilities.hasAbilityScore(DefaultAbility.DEXTERITY);
        mockAbilities.setReturnValue(true);
        abilities.getAbilityScore(DefaultAbility.DEXTERITY);
        mockAbilities.setReturnValue(abilityScore);
        mockAbilities.replay();
        
        DefaultArmorClass defaultArmorClass = new DefaultArmorClass();
        new DexterityArmorClass(abilities, DefaultEncumberance.HEAVY, defaultArmorClass);
        abilityScore.addAbilityModifier(new DefaultAbilityScoreModifier(DefaultAbility.DEXTERITY, 8));
        assertEquals(11, defaultArmorClass.getValue());
    }
}
