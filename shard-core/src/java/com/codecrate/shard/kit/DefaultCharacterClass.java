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

import com.codecrate.shard.character.Alignment;
import com.codecrate.shard.character.DefaultAlignment;
import com.codecrate.shard.character.prereq.AlignmentPrerequisite;
import com.codecrate.shard.character.prereq.CharacterPrerequisite;
import com.codecrate.shard.character.prereq.DeityAlignmentPrerequisite;
import com.codecrate.shard.character.prereq.NullPrerequisite;
import com.codecrate.shard.dice.DefaultDice;
import com.codecrate.shard.dice.Dice;
import com.codecrate.shard.modifier.DefaultKeyedModifier;
import com.codecrate.shard.modifier.KeyedModifier;
import com.codecrate.shard.race.DefaultLanguage;
import com.codecrate.shard.skill.DefaultSkill;
import com.codecrate.shard.skill.Feat;
import com.codecrate.shard.skill.SkillDao;

/**
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class DefaultCharacterClass implements CharacterClass {
    public static final CharacterClass BARBARIAN = new DefaultCharacterClass(
            "Barbarian", DefaultDice.d12, new SkillDao(), 4, 
            new DefaultClassProgressionDao(), new AlignmentPrerequisite(
                    new Alignment[] {
                            DefaultAlignment.NEUTRAL_GOOD, 
                            DefaultAlignment.NEUTRAL_NEUTRAL, 
                            DefaultAlignment.NEUTRAL_EVIL, 
                            DefaultAlignment.CHAOTIC_GOOD, 
                            DefaultAlignment.CHAOTIC_NEUTRAL, 
                            DefaultAlignment.CHAOTIC_EVIL 
                            }),
    		new ArrayList(), new ArrayList(), new ArrayList());
    
    public static final CharacterClass BARD = new DefaultCharacterClass(
            "Bard", DefaultDice.d6, new SkillDao(), 6, 
            new DefaultClassProgressionDao(), new AlignmentPrerequisite(
                    new Alignment[] {
                            DefaultAlignment.NEUTRAL_GOOD, 
                            DefaultAlignment.NEUTRAL_NEUTRAL, 
                            DefaultAlignment.NEUTRAL_EVIL, 
                            DefaultAlignment.CHAOTIC_GOOD, 
                            DefaultAlignment.CHAOTIC_NEUTRAL, 
                            DefaultAlignment.CHAOTIC_EVIL 
                            }),
    				new ArrayList(),
            new ArrayList(), Arrays
                    .asList(new KeyedModifier[] { new DefaultKeyedModifier(
                            DefaultSkill.LITERACY, DefaultSkill.TYPE_CLASS, 1) }));

    public static final CharacterClass CLERIC = new DefaultCharacterClass(
            "Cleric", DefaultDice.d8, new SkillDao(), 2, 
            new DefaultClassProgressionDao(), new DeityAlignmentPrerequisite(), 
            Arrays.asList(new DefaultLanguage[]{DefaultLanguage.ABYSSAL, DefaultLanguage.CELESTIAL, DefaultLanguage.INFERNAL}),
            Arrays.asList(new Feat[]{Feat.ARMOR_PROFICIENCY_LIGHT, Feat.ARMOR_PROFICIENCY_MEDIUM,
                    Feat.ARMOR_PROFICIENCY_HEAVY,
                            Feat.SHIELD_PROFICIENCY }), Arrays
                            .asList(new KeyedModifier[] { new DefaultKeyedModifier(
                                    DefaultSkill.LITERACY, DefaultSkill.TYPE_CLASS, 1) }));
    
    public static final CharacterClass DRUID = new DefaultCharacterClass(
            "Druid", DefaultDice.d8, new SkillDao(), 4, 
            new DefaultClassProgressionDao(), new AlignmentPrerequisite(
                    new Alignment[] {
                            DefaultAlignment.LAWFUL_NEUTRAL,
                            DefaultAlignment.NEUTRAL_GOOD, 
                            DefaultAlignment.NEUTRAL_NEUTRAL, 
                            DefaultAlignment.CHAOTIC_NEUTRAL, 
                            DefaultAlignment.CHAOTIC_EVIL 
                            }), 
                            Arrays.asList(new DefaultLanguage[]{DefaultLanguage.SYLVAN}),
                            new ArrayList(), Arrays
                            .asList(new KeyedModifier[] { new DefaultKeyedModifier(
                                    DefaultSkill.LITERACY, DefaultSkill.TYPE_CLASS, 1) }));
    
    public static final CharacterClass FIGHTER = new DefaultCharacterClass(
            "Fighter", DefaultDice.d10, new SkillDao(), 2, 
            new DefaultClassProgressionDao(), new NullPrerequisite(), new ArrayList(), 
            new ArrayList(), Arrays
            .asList(new KeyedModifier[] { new DefaultKeyedModifier(
                    DefaultSkill.LITERACY, DefaultSkill.TYPE_CLASS, 1) }));
    
    public static final CharacterClass MONK = new DefaultCharacterClass(
            "Monk", DefaultDice.d8, new SkillDao(), 4, 
            new DefaultClassProgressionDao(), new AlignmentPrerequisite(
                    new Alignment[] {
                            DefaultAlignment.LAWFUL_GOOD, 
                            DefaultAlignment.LAWFUL_NEUTRAL, 
                            DefaultAlignment.LAWFUL_EVIL 
                            }), new ArrayList(),
            new ArrayList(), Arrays
            .asList(new KeyedModifier[] { new DefaultKeyedModifier(
                    DefaultSkill.LITERACY, DefaultSkill.TYPE_CLASS, 1) }));

    public static final CharacterClass PALADIN = new DefaultCharacterClass(
            "Paladin", DefaultDice.d10, new SkillDao(), 2, 
            new DefaultClassProgressionDao(), new AlignmentPrerequisite(
                    new Alignment[] {
                            DefaultAlignment.LAWFUL_GOOD 
                            }),
            new ArrayList(), new ArrayList(), Arrays
            .asList(new KeyedModifier[] { new DefaultKeyedModifier(
                    DefaultSkill.LITERACY, DefaultSkill.TYPE_CLASS, 1) }));
    
    public static final CharacterClass RANGER = new DefaultCharacterClass(
            "Ranger", DefaultDice.d8, new SkillDao(), 6, 
            new DefaultClassProgressionDao(), new NullPrerequisite(), new ArrayList(), 
            new ArrayList(), Arrays
            .asList(new KeyedModifier[] { new DefaultKeyedModifier(
                    DefaultSkill.LITERACY, DefaultSkill.TYPE_CLASS, 1) }));
    
    public static final CharacterClass ROUGE = new DefaultCharacterClass(
            "Rouge", DefaultDice.d6, new SkillDao(), 8, 
            new DefaultClassProgressionDao(), new NullPrerequisite(), new ArrayList(), 
            new ArrayList(), Arrays
            .asList(new KeyedModifier[] { new DefaultKeyedModifier(
                    DefaultSkill.LITERACY, DefaultSkill.TYPE_CLASS, 1) }));
    
    public static final CharacterClass SORCERER = new DefaultCharacterClass(
            "Sorcerer", DefaultDice.d4, new SkillDao(), 2, 
            new DefaultClassProgressionDao(), new NullPrerequisite(), new ArrayList(), 
            new ArrayList(), Arrays
            .asList(new KeyedModifier[] { new DefaultKeyedModifier(
                    DefaultSkill.LITERACY, DefaultSkill.TYPE_CLASS, 1) }));
    
    public static final CharacterClass WIZARD = new DefaultCharacterClass(
            "Wizard", DefaultDice.d4, new SkillDao(), 2, 
            new DefaultClassProgressionDao(), new NullPrerequisite(), 
            Arrays
                    .asList(new DefaultLanguage[] { DefaultLanguage.DRACONIC }),
            new ArrayList(), Arrays
            .asList(new KeyedModifier[] { new DefaultKeyedModifier(
                    DefaultSkill.LITERACY, DefaultSkill.TYPE_CLASS, 1) }));

    public static final CharacterClass WARRIOR = new DefaultCharacterClass(
            "Warrior", DefaultDice.d8, new SkillDao(), 2, 
            new DefaultClassProgressionDao(), new NullPrerequisite(), 
            new ArrayList(), 
            Arrays.asList(new Feat[]{Feat.ARMOR_PROFICIENCY_LIGHT, Feat.ARMOR_PROFICIENCY_MEDIUM,
                    Feat.ARMOR_PROFICIENCY_HEAVY, Feat.SHIELD_PROFICIENCY}), new ArrayList());

    
    private Collection classSkills;
    private Collection bonusLanguages;
    private Collection feats;
    private Dice hitDicePerLevel;
    private int baseSkillPointsPerLevel;
    private String name;
    private ClassProgression progression;
    private CharacterPrerequisite prereq;
    private Collection skillModifiers;

	private SkillDao skillDao;
    
    public DefaultCharacterClass(String name, Dice hitDicePerLevel,
            SkillDao skillDao, int baseSkillPointsPerLevel, 
			ClassProgressionDao progressionDao, CharacterPrerequisite prereq, 
			Collection bonusLanguages, Collection feats, Collection skillModifiers) {
        this.name = name;
        this.hitDicePerLevel = hitDicePerLevel;
		this.skillDao = skillDao;
        this.baseSkillPointsPerLevel = baseSkillPointsPerLevel;
        this.prereq = prereq;
        this.bonusLanguages = bonusLanguages;
        this.feats = feats;
        this.skillModifiers = skillModifiers;
        this.progression = progressionDao.getClassProgress(this);
        
    }
    
    public String toString() {
    	return name;
    }

    public Dice getHitDicePerLevel() {
        return hitDicePerLevel;
    }

    public Collection getClassSkills() {
    	if (null == classSkills) {
            this.classSkills = skillDao.getClassSkills(this);
    	}
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