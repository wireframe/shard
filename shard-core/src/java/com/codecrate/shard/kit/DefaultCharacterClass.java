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
package com.codecrate.shard.kit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.codecrate.shard.character.Alignment;
import com.codecrate.shard.character.DefaultAlignment;
import com.codecrate.shard.character.prereq.AlignmentPrerequisite;
import com.codecrate.shard.character.prereq.CharacterPrerequisite;
import com.codecrate.shard.character.prereq.DeityAlignmentPrerequisite;
import com.codecrate.shard.character.prereq.NullPrerequisite;
import com.codecrate.shard.dice.Dice;
import com.codecrate.shard.dice.RandomDice;
import com.codecrate.shard.feat.DefaultFeat;
import com.codecrate.shard.modifier.DefaultKeyedModifier;
import com.codecrate.shard.modifier.DefaultModifierType;
import com.codecrate.shard.modifier.KeyedModifier;
import com.codecrate.shard.skill.DefaultSkill;

/**
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class DefaultCharacterClass implements CharacterClass {
    public static final CharacterClass BARBARIAN = new DefaultCharacterClass(
            "Barbarian", RandomDice.d12,  4, 
            new DefaultClassProgressionDao(), new AlignmentPrerequisite(
                    new Alignment[] {
                            DefaultAlignment.NEUTRAL_GOOD, 
                            DefaultAlignment.NEUTRAL_NEUTRAL, 
                            DefaultAlignment.NEUTRAL_EVIL, 
                            DefaultAlignment.CHAOTIC_GOOD, 
                            DefaultAlignment.CHAOTIC_NEUTRAL, 
                            DefaultAlignment.CHAOTIC_EVIL 
                            }),
    		new HashSet(), new ArrayList(), new ArrayList());
    
    public static final CharacterClass BARD = new DefaultCharacterClass(
            "Bard", RandomDice.d6,  6, 
            new DefaultClassProgressionDao(), new AlignmentPrerequisite(
                    new Alignment[] {
                            DefaultAlignment.NEUTRAL_GOOD, 
                            DefaultAlignment.NEUTRAL_NEUTRAL, 
                            DefaultAlignment.NEUTRAL_EVIL, 
                            DefaultAlignment.CHAOTIC_GOOD, 
                            DefaultAlignment.CHAOTIC_NEUTRAL, 
                            DefaultAlignment.CHAOTIC_EVIL 
                            }),
    				new HashSet(),
            new ArrayList(), Arrays
                    .asList(new KeyedModifier[] { new DefaultKeyedModifier(
                            DefaultSkill.LITERACY, DefaultModifierType.CLASS, 1) }));

    public static final CharacterClass CLERIC = new DefaultCharacterClass(
            "Cleric", RandomDice.d8,  2, 
            new DefaultClassProgressionDao(), new DeityAlignmentPrerequisite(), 
            new HashSet(),
            Arrays.asList(new DefaultFeat[]{DefaultFeat.ARMOR_PROFICIENCY_LIGHT, DefaultFeat.ARMOR_PROFICIENCY_MEDIUM,
                    DefaultFeat.ARMOR_PROFICIENCY_HEAVY,
                            DefaultFeat.SHIELD_PROFICIENCY }), Arrays
                            .asList(new KeyedModifier[] { new DefaultKeyedModifier(
                                    DefaultSkill.LITERACY, DefaultModifierType.CLASS, 1) }));
    
    public static final CharacterClass DRUID = new DefaultCharacterClass(
            "Druid", RandomDice.d8,  4, 
            new DefaultClassProgressionDao(), new AlignmentPrerequisite(
                    new Alignment[] {
                            DefaultAlignment.LAWFUL_NEUTRAL,
                            DefaultAlignment.NEUTRAL_GOOD, 
                            DefaultAlignment.NEUTRAL_NEUTRAL, 
                            DefaultAlignment.CHAOTIC_NEUTRAL, 
                            DefaultAlignment.CHAOTIC_EVIL 
                            }), 
                            new HashSet(),
                            new ArrayList(), Arrays
                            .asList(new KeyedModifier[] { new DefaultKeyedModifier(
                                    DefaultSkill.LITERACY, DefaultModifierType.CLASS, 1) }));
    
    public static final CharacterClass FIGHTER = new DefaultCharacterClass(
            "Fighter", RandomDice.d10,  2, 
            new DefaultClassProgressionDao(), new NullPrerequisite(), new HashSet(), 
            new ArrayList(), Arrays
            .asList(new KeyedModifier[] { new DefaultKeyedModifier(
                    DefaultSkill.LITERACY, DefaultModifierType.CLASS, 1) }));
    
    public static final CharacterClass MONK = new DefaultCharacterClass(
            "Monk", RandomDice.d8,  4, 
            new DefaultClassProgressionDao(), new AlignmentPrerequisite(
                    new Alignment[] {
                            DefaultAlignment.LAWFUL_GOOD, 
                            DefaultAlignment.LAWFUL_NEUTRAL, 
                            DefaultAlignment.LAWFUL_EVIL 
                            }), new HashSet(),
            new ArrayList(), Arrays
            .asList(new KeyedModifier[] { new DefaultKeyedModifier(
                    DefaultSkill.LITERACY, DefaultModifierType.CLASS, 1) }));

    public static final CharacterClass PALADIN = new DefaultCharacterClass(
            "Paladin", RandomDice.d10,  2, 
            new DefaultClassProgressionDao(), new AlignmentPrerequisite(
                    new Alignment[] {
                            DefaultAlignment.LAWFUL_GOOD 
                            }),
            new HashSet(), new ArrayList(), Arrays
            .asList(new KeyedModifier[] { new DefaultKeyedModifier(
                    DefaultSkill.LITERACY, DefaultModifierType.CLASS, 1) }));
    
    public static final CharacterClass RANGER = new DefaultCharacterClass(
            "Ranger", RandomDice.d8,  6, 
            new DefaultClassProgressionDao(), new NullPrerequisite(), new HashSet(), 
            new ArrayList(), Arrays
            .asList(new KeyedModifier[] { new DefaultKeyedModifier(
                    DefaultSkill.LITERACY, DefaultModifierType.CLASS, 1) }));
    
    public static final CharacterClass ROUGE = new DefaultCharacterClass(
            "Rouge", RandomDice.d6,  8, 
            new DefaultClassProgressionDao(), new NullPrerequisite(), new HashSet(), 
            new ArrayList(), Arrays
            .asList(new KeyedModifier[] { new DefaultKeyedModifier(
                    DefaultSkill.LITERACY, DefaultModifierType.CLASS, 1) }));
    
    public static final CharacterClass SORCERER = new DefaultCharacterClass(
            "Sorcerer", RandomDice.d4,  2, 
            new DefaultClassProgressionDao(), new NullPrerequisite(), new HashSet(), 
            new ArrayList(), Arrays
            .asList(new KeyedModifier[] { new DefaultKeyedModifier(
                    DefaultSkill.LITERACY, DefaultModifierType.CLASS, 1) }));
    
    public static final CharacterClass WIZARD = new DefaultCharacterClass(
            "Wizard", RandomDice.d4,  2, 
            new DefaultClassProgressionDao(), new NullPrerequisite(), 
            new HashSet(),
            new ArrayList(), Arrays
            .asList(new KeyedModifier[] { new DefaultKeyedModifier(
                    DefaultSkill.LITERACY, DefaultModifierType.CLASS, 1) }));

    public static final CharacterClass WARRIOR = new DefaultCharacterClass(
            "Warrior", RandomDice.d8,  2, 
            new DefaultClassProgressionDao(), new NullPrerequisite(), 
            new HashSet(), 
            Arrays.asList(new DefaultFeat[]{DefaultFeat.ARMOR_PROFICIENCY_LIGHT, DefaultFeat.ARMOR_PROFICIENCY_MEDIUM,
                    DefaultFeat.ARMOR_PROFICIENCY_HEAVY, DefaultFeat.SHIELD_PROFICIENCY}), new ArrayList());

    
    private Set classSkills = new HashSet();
    private Set bonusLanguages;
    private Collection feats;
    private Dice hitDicePerLevel;
    private int baseSkillPointsPerLevel;
    private String name;
    private ClassProgression progression;
    private CharacterPrerequisite prereq;
    private Collection skillModifiers;

    /**
     * hibernate constructor.
     */
    private DefaultCharacterClass() {
    }
    
    public DefaultCharacterClass(String name, Dice hitDicePerLevel,
            int baseSkillPointsPerLevel, 
			ClassProgressionDao progressionDao, CharacterPrerequisite prereq, 
			Set bonusLanguages, Collection feats, Collection skillModifiers) {
        this.name = name;
        this.hitDicePerLevel = hitDicePerLevel;
        this.baseSkillPointsPerLevel = baseSkillPointsPerLevel;
        this.prereq = prereq;
        this.bonusLanguages = bonusLanguages;
        this.feats = feats;
        this.skillModifiers = skillModifiers;
        this.progression = progressionDao.getClassProgression(this);
    }
    
    public String toString() {
    	return name;
    }

    public Dice getHitDicePerLevel() {
        return hitDicePerLevel;
    }

    public Collection getClassSkills() {
        return classSkills;
    }

    public int getBaseSkillPointsPerLevel() {
        return baseSkillPointsPerLevel;
    }
    
    public String getName() {
        return name;
    }

	public ClassProgression getClassProgression() {
		return progression;
	}
	
	public CharacterPrerequisite getPrerequisite() {
	    return prereq;
	}
	
	public Collection getBonusLanguages() {
	    return bonusLanguages;
	}
	
	public Collection getFeats() {
	    return feats;
	}
	
	public Collection getSkills() {
	    return skillModifiers;
	}
}