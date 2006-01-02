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
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.codecrate.shard.character.prereq.CharacterPrerequisite;
import com.codecrate.shard.dice.Dice;
import com.codecrate.shard.dice.DiceExpression;
import com.codecrate.shard.skill.Skill;

/**
 *
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class DefaultCharacterClass implements CharacterClass, Comparable {
    private String id;
    private Set classSkills = new HashSet();
    private Set bonusLanguages = new HashSet();;
    private Collection feats = new ArrayList();
    private Collection skillModifiers = new ArrayList();
    private Dice hitDicePerLevel;
    private int baseSkillPointsPerLevel;
    private String name;
    private String abbreviation;
    private ClassProgression progression;
    private Set levels = new HashSet();
    private CharacterPrerequisite prereq;

    /**
     * hibernate constructor.
     */
    private DefaultCharacterClass() {
        progression = new DefaultClassProgression(this);
    }

    public DefaultCharacterClass(String name, String abbreviation, Dice hitDicePerLevel,
            int baseSkillPointsPerLevel, CharacterPrerequisite prereq) {
        this.name = name;
        this.abbreviation = abbreviation;
        this.hitDicePerLevel = hitDicePerLevel;
        this.baseSkillPointsPerLevel = baseSkillPointsPerLevel;
        this.prereq = prereq;

        progression = new DefaultClassProgression(this);
    }

    public String toString() {
    	return name;
    }

    public int hashCode() {
    	return new HashCodeBuilder(3, 7)
    	.append(name)
    	.toHashCode();
    }

    public boolean equals(Object object) {
    	if (this == object) {
    		return true;
    	}

    	if (!(object instanceof DefaultCharacterClass)) {
    		return false;
    	}
    	DefaultCharacterClass target = (DefaultCharacterClass) object;
    	return new EqualsBuilder()
	    	.append(name, target.name)
	    	.isEquals();
    }

    public int compareTo(Object object) {
    	DefaultCharacterClass kit = (DefaultCharacterClass) object;
    	return name.compareTo(kit.name);
    }

    public Dice getHitDicePerLevel() {
        return hitDicePerLevel;
    }

    public void setHitDicePerLevel(Dice hitDice) {
        this.hitDicePerLevel = hitDice;
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

    public void setName(String name) {
    	this.name = name;
    }

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
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


    /**
     * @deprecated remove when spring-rcp binder created
     * @param cost
     */
    public void setHitDicePerLevelString(String hitDice) {
        setHitDicePerLevel(new DiceExpression(hitDice));
    }

    /**
     * @deprecated remove when spring-rcp binder created.
     * @return
     */
    public String getHitDicePerLevelString() {
        return getHitDicePerLevel().toString();
    }

	public void addClassSkill(Skill skill) {
		classSkills.add(skill);
	}


    public class DefaultClassProgression implements ClassProgression {
        private final CharacterClass kit;

        public DefaultClassProgression(CharacterClass kit) {
            this.kit = kit;
        }

        public Collection getClassLevels() {
            return levels;
        }

        public int getMaxLevel() {
            return levels.size();
        }

        public ClassLevel getClassLevel(int level) {
            ClassLevel kit = null;
            Iterator it = levels.iterator();
            while (it.hasNext()) {
                ClassLevel object = (ClassLevel) it.next();
                if (level == object.getLevel()) {
                    return object;
                }
            }
            return kit;
        }

        public void addLevel(int baseAttackBonus, int fortitudeSave, int reflexSave, int willSave) {
            levels.add(new DefaultClassLevel(getMaxLevel() + 1, kit, baseAttackBonus, fortitudeSave, reflexSave, willSave));
        }
    }
}