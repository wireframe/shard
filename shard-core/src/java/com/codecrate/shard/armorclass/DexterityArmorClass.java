package com.codecrate.shard.armorclass;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.codecrate.shard.ability.AbilityScore;
import com.codecrate.shard.ability.AbilityScoreContainer;
import com.codecrate.shard.ability.AbilityScoreListener;
import com.codecrate.shard.ability.DefaultAbility;
import com.codecrate.shard.movement.Encumberance;

/**
 * ArmorClass that handles dexterity modifiers.
 * Takes into account encumberance and armor worn to limit the dex modifier.
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class DexterityArmorClass implements ArmorClass, AbilityScoreListener {
    private static final Log LOG = LogFactory.getLog(DexterityArmorClass.class);
    
    private final ArmorClass delegate;
    private final Encumberance encumberance;
    private AbilityScore abilityScore;
    private ArmorClassModifier modifier;

    public DexterityArmorClass(AbilityScoreContainer abilities, Encumberance encumberance, ArmorClass delegate) {
        this.encumberance = encumberance;
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
            LOG.debug("Updating dexterity armor class modifier.");
            if (null != modifier) {
                removeArmorClassModifier(modifier);
            }
            int value = abilityScore.getModifier();
            int maxValue = encumberance.getMaxDexterityModifier();
            if (value > maxValue) {
                LOG.info("Encumberance limits dexterity modifier to " + maxValue);
                value = maxValue;
            }
            modifier = new DefaultArmorClassModifier(DefaultArmorClassModifierType.DEXTERITY, value);
            addArmorClassModifier(modifier);
        }
    }
}
