package com.codecrate.shard.armorclass;

import com.codecrate.shard.ability.AbilityScore;
import com.codecrate.shard.ability.AbilityScoreContainer;
import com.codecrate.shard.ability.AbilityScoreListener;
import com.codecrate.shard.ability.DefaultAbility;

/**
 * ArmorClass that handles dexterity modifiers.
 * Takes into account encumberance and armor worn to limit the dex modifier.
 * 
 * TODO need to take into account encumberance and armor worn.
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class DexterityArmorClass implements ArmorClass, AbilityScoreListener {

    private final ArmorClass delegate;
    private final AbilityScoreContainer abilities;
    private ArmorClassModifier modifier;
    private AbilityScore abilityScore;

    public DexterityArmorClass(AbilityScoreContainer abilities, ArmorClass delegate) {
        this.abilities = abilities;
        this.delegate = delegate;
        
        if (abilities.hasAbilityScore(DefaultAbility.DEXTERITY)) {
            abilityScore = abilities.getAbilityScore(DefaultAbility.DEXTERITY);
            abilityScore.addListener(this);
        }
        onModify();
    }
    
    public void addArmorClassModifier(ArmorClassModifier modifier) {
        delegate.addArmorClassModifier(modifier);
    }
    public int getValue() {
        return delegate.getValue();
    }
    public void removeArmorClassModifier(ArmorClassModifier modifier) {
        delegate.removeArmorClassModifier(modifier);
    }

    public void onModify() {
        if (null != abilityScore) {
            if (null != modifier) {
                removeArmorClassModifier(modifier);
            }
            modifier = new DefaultArmorClassModifier(DefaultArmorClassModifierType.DEXTERITY, abilityScore.getModifier());
            addArmorClassModifier(modifier);
        }
    }
}
