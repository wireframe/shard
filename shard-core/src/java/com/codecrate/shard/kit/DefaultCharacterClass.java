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
import java.util.Collection;

import com.codecrate.shard.character.Alignment;
import com.codecrate.shard.character.DefaultAlignment;
import com.codecrate.shard.character.prereq.AlignmentPrerequisite;
import com.codecrate.shard.character.prereq.CharacterPrerequisite;
import com.codecrate.shard.character.prereq.NullPrerequisite;
import com.codecrate.shard.dice.DefaultDice;
import com.codecrate.shard.dice.Dice;

/**
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek </a>
 */
public class DefaultCharacterClass implements CharacterClass {
    public static final CharacterClass BARBARIAN = new DefaultCharacterClass(
            "Barbarian", DefaultDice.d12, new ArrayList(), 4, 
            new DefaultClassProgressionDao(), new AlignmentPrerequisite(
                    new Alignment[] {
                            DefaultAlignment.NEUTRAL_GOOD, 
                            DefaultAlignment.NEUTRAL_NEUTRAL, 
                            DefaultAlignment.NEUTRAL_EVIL, 
                            DefaultAlignment.CHAOTIC_GOOD, 
                            DefaultAlignment.CHAOTIC_NEUTRAL, 
                            DefaultAlignment.CHAOTIC_EVIL 
                            }));
    
    public static final CharacterClass BARD = new DefaultCharacterClass(
            "Bard", DefaultDice.d6, new ArrayList(), 6, 
            new DefaultClassProgressionDao(), new AlignmentPrerequisite(
                    new Alignment[] {
                            DefaultAlignment.NEUTRAL_GOOD, 
                            DefaultAlignment.NEUTRAL_NEUTRAL, 
                            DefaultAlignment.NEUTRAL_EVIL, 
                            DefaultAlignment.CHAOTIC_GOOD, 
                            DefaultAlignment.CHAOTIC_NEUTRAL, 
                            DefaultAlignment.CHAOTIC_EVIL 
                            }));

    public static final CharacterClass CLERIC = new DefaultCharacterClass(
            "Cleric", DefaultDice.d8, new ArrayList(), 2, 
            new DefaultClassProgressionDao(), new NullPrerequisite());
    
    public static final CharacterClass DRUID = new DefaultCharacterClass(
            "Druid", DefaultDice.d8, new ArrayList(), 4, 
            new DefaultClassProgressionDao(), new AlignmentPrerequisite(
                    new Alignment[] {
                            DefaultAlignment.LAWFUL_NEUTRAL,
                            DefaultAlignment.NEUTRAL_GOOD, 
                            DefaultAlignment.NEUTRAL_NEUTRAL, 
                            DefaultAlignment.CHAOTIC_NEUTRAL, 
                            DefaultAlignment.CHAOTIC_EVIL 
                            }));
    
    public static final CharacterClass FIGHTER = new DefaultCharacterClass(
            "Fighter", DefaultDice.d10, new ArrayList(), 2, 
            new DefaultClassProgressionDao(), new NullPrerequisite());
    
    public static final CharacterClass MONK = new DefaultCharacterClass(
            "Monk", DefaultDice.d8, new ArrayList(), 4, 
            new DefaultClassProgressionDao(), new AlignmentPrerequisite(
                    new Alignment[] {
                            DefaultAlignment.LAWFUL_GOOD, 
                            DefaultAlignment.LAWFUL_NEUTRAL, 
                            DefaultAlignment.LAWFUL_EVIL 
                            }));

    public static final CharacterClass PALADIN = new DefaultCharacterClass(
            "Paladin", DefaultDice.d10, new ArrayList(), 2, 
            new DefaultClassProgressionDao(), new AlignmentPrerequisite(
                    new Alignment[] {
                            DefaultAlignment.LAWFUL_GOOD 
                            }));
    
    public static final CharacterClass RANGER = new DefaultCharacterClass(
            "Ranger", DefaultDice.d8, new ArrayList(), 6, 
            new DefaultClassProgressionDao(), new NullPrerequisite());
    
    public static final CharacterClass ROUGE = new DefaultCharacterClass(
            "Rouge", DefaultDice.d6, new ArrayList(), 8, 
            new DefaultClassProgressionDao(), new NullPrerequisite());
    
    public static final CharacterClass SORCERER = new DefaultCharacterClass(
            "Sorcerer", DefaultDice.d4, new ArrayList(), 2, 
            new DefaultClassProgressionDao(), new NullPrerequisite());
    
    public static final CharacterClass WIZARD = new DefaultCharacterClass(
            "Wizard", DefaultDice.d4, new ArrayList(), 2, 
            new DefaultClassProgressionDao(), new NullPrerequisite());

    
    private Collection classSkills;
    private Dice hitDicePerLevel;
    private final int baseSkillPointsPerLevel;
    private final String name;
    private final ClassProgression progression;
    private final CharacterPrerequisite prereq;
    
    public DefaultCharacterClass(String name, Dice hitDicePerLevel,
            Collection classSkills, int baseSkillPointsPerLevel, 
			ClassProgressionDao progressionDao, CharacterPrerequisite prereq) {
        this.name = name;
        this.hitDicePerLevel = hitDicePerLevel;
        this.classSkills = classSkills;
        this.baseSkillPointsPerLevel = baseSkillPointsPerLevel;
        this.prereq = prereq;
        this.progression = progressionDao.getClassProgress(this);
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
}