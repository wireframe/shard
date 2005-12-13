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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;

import com.codecrate.shard.ability.AbilityScoreContainer;
import com.codecrate.shard.armorclass.ArmorClass;
import com.codecrate.shard.armorclass.DexterityArmorClass;
import com.codecrate.shard.character.bio.CharacterBio;
import com.codecrate.shard.divine.Deity;
import com.codecrate.shard.equipment.ItemEntryContainer;
import com.codecrate.shard.feat.FeatContainer;
import com.codecrate.shard.kit.CharacterClass;
import com.codecrate.shard.movement.Encumberance;
import com.codecrate.shard.race.Race;
import com.codecrate.shard.save.SavingThrowEntryContainer;
import com.codecrate.shard.skill.CharacterProgressionSkillEntryContainer;
import com.codecrate.shard.skill.SkillEntryContainer;

/**
 * Default character.
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class DefaultPlayerCharacter implements PlayerCharacter {
    private Deity deity;
    private CharacterBio bio;
    private BigDecimal challengeRating;
    private Race race;
    private Alignment alignment;
    private AbilityScoreContainer abilities;
    private CharacterProgression characterProgression;
    private ItemEntryContainer inventory;

    private String id;
    private SkillEntryContainer skills;
	private FeatContainer feats;
	private Encumberance encumberance;
	private DexterityArmorClass armorClass;
	private HitPoints hitPoints;
	private SavingThrowEntryContainer savingThrows;
	private Initiative initiative;

	/**
	 * hibernate constructor.
	 */
	private DefaultPlayerCharacter() {
	}
	
    /**
     * default constructor.
     * @param encumberance TODO
     * @param skills skills for the character.
     * @param challengeRating challengeRating for the character.
     */
    public DefaultPlayerCharacter(AbilityScoreContainer abilities,
    		Race race, CharacterProgression characterProgression, 
    		ItemEntryContainer inventory, Encumberance encumberance,
    		Alignment alignment, CharacterBio bio, Deity deity) {
        this.race = race;
        this.abilities = abilities;
        this.deity = deity;
        this.inventory = inventory;
        this.alignment = alignment;
        this.bio = bio;
        this.characterProgression = characterProgression;
        this.encumberance = encumberance;
        
        this.challengeRating = new BigDecimal(0);
        this.feats = new FeatContainer(new ArrayList());
        this.savingThrows = new SavingThrowEntryContainer();
        this.initiative = new Initiative(abilities);
        this.armorClass = new DexterityArmorClass(abilities, encumberance);
        this.skills = new CharacterProgressionSkillEntryContainer(characterProgression);
        this.hitPoints = new CharacterProgressionHitPoints(characterProgression);
    }
    
    public CharacterBio getBio() {
        return bio;
    }
    
    public BigDecimal getChallengeRating() {
        return challengeRating;
    }

    public int getEffectiveCharacterLevel() {
    	return characterProgression.getCharacterLevel() + race.getLevelAdjustment();
    }

	public AbilityScoreContainer getAbilities() {
		return abilities;
	}

	public int getBaseAttackBonus() {
		int value = 0;
		Iterator it = characterProgression.getClasses().iterator();
		while (it.hasNext()) {
			CharacterClass kit = (CharacterClass) it.next();
			value += characterProgression.getClassLevel(kit).getBaseAttackBonus();
		}
		value += race.getSize().getBaseAttackBonusModifier();
		return value;
	}
	
	public Alignment getAlignment() {
		return alignment;
	}
	
	public HitPoints getHitPoints() {
		return hitPoints;
	}
	
	public Encumberance getEncumberance() {
		return encumberance;
	}
	public Race getRace() {
		return race;
	}
	
    public ArmorClass getArmorClass() {
        return armorClass;
    }
    
    public CharacterProgression getCharacterProgression() {
    	return characterProgression;
    }
    public SavingThrowEntryContainer getSavingThrows() {
        return savingThrows;
    }
    
    public ItemEntryContainer getInventory() {
        return inventory;
    }
    
    public SkillEntryContainer getSkills() {
    	return skills;
    }

    public Initiative getInitiative() {
        return initiative;
    }
    public FeatContainer getFeats() {
        return feats;
    }
    public Deity getDeity() {
        return deity;
    }
    
    public void setRace(Race race) {
    	this.race = race;
    }
    
    public void setAlignment(Alignment alignment) {
    	this.alignment = alignment;
    }
}
