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

import java.util.Collection;

import com.codecrate.shard.Identifiable;
import com.codecrate.shard.character.prereq.CharacterPrerequisite;
import com.codecrate.shard.dice.Dice;
import com.codecrate.shard.skill.SkillDao;

/**
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class HibernateCharacterClass implements CharacterClass, Identifiable {
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
    
	/**
	 * hibernate constructor.
	 */
	public HibernateCharacterClass() {
	}
	
    public HibernateCharacterClass(String name, Dice hitDicePerLevel,
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

    public String getId() {
        return name;
    }
    
    public void setId(String id) {
        this.name = id;
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

    /**
     */
    public int getBaseSkillPointsPerLevel() {
        return baseSkillPointsPerLevel;
    }

    /**
     * @param baseSkillPointsPerLevel The baseSkillPointsPerLevel to set.
     */
    public void setBaseSkillPointsPerLevel(int baseSkillPointsPerLevel) {
        this.baseSkillPointsPerLevel = baseSkillPointsPerLevel;
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