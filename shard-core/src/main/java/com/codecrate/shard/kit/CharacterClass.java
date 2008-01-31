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
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

import com.codecrate.shard.character.prereq.CharacterPrerequisite;
import com.codecrate.shard.character.prereq.NullPrerequisite;
import com.codecrate.shard.dice.Dice;
import com.codecrate.shard.dice.DiceExpression;
import com.codecrate.shard.feat.Feat;
import com.codecrate.shard.modifier.Modifier;
import com.codecrate.shard.race.Language;
import com.codecrate.shard.skill.Skill;
import com.codecrate.shard.source.Source;

/**
 * Definition of a character class.
 * ex: Ranger
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
@Entity
@Indexed
public class CharacterClass implements Comparable<CharacterClass> {
    @Id
    @DocumentId
    @GeneratedValue 
    private int sequenceId;

    @ManyToMany
    private Set<Skill> classSkills = new HashSet<Skill>();
    
    @ManyToMany
    private Collection<Feat> feats = new ArrayList<Feat>();
    
    @Type(type="com.codecrate.shard.hibernate.DiceUserType")
    private Dice hitDicePerLevel;
    
    private int baseSkillPointsPerLevel;
    
    @Field(index=Index.TOKENIZED, store=Store.NO)    
    private String name;

    @Field(index=Index.TOKENIZED, store=Store.NO)    
    private String abbreviation;
    
    @ManyToOne
    private Source source;

    private transient Set<Language> bonusLanguages = new HashSet<Language>();
    private transient Collection<Modifier> skillModifiers = new ArrayList<Modifier>();
    private transient SortedSet<ClassLevel> levels = new TreeSet<ClassLevel>();
    
    private transient ClassProgression progression;
    private transient CharacterPrerequisite prereq = new NullPrerequisite();

    /**
     * hibernate constructor.
     */
    private CharacterClass() {
        progression = new DefaultClassProgression(this);
    }

    public CharacterClass(String name, String abbreviation, Dice hitDicePerLevel, Source source) {
        this.name = name;
        this.abbreviation = abbreviation;
        this.hitDicePerLevel = hitDicePerLevel;
        this.source = source;

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

    	if (!(object instanceof CharacterClass)) {
    		return false;
    	}
    	CharacterClass target = (CharacterClass) object;
    	return new EqualsBuilder()
	    	.append(name, target.name)
	    	.isEquals();
    }

    public int compareTo(CharacterClass kit) {
    	return name.compareTo(kit.name);
    }

	/**
	 * gets the dice used for each level to generate hit points (before constitution modifier)
	 * ex: d8
	 */
    public Dice getHitDicePerLevel() {
        return hitDicePerLevel;
    }

    public void setHitDicePerLevel(Dice hitDice) {
        this.hitDicePerLevel = hitDice;
    }

	/**
	 * gets the skills that are considered class skills.
	 * all other skills are considered cross class.
	 */
    public Collection<Skill> getClassSkills() {
        return classSkills;
    }

	/**
	 * gets the number of base skill points per level.
	 * this is before the intelligence modifier is added on.
	 * @return number of skill points per level (before intelligence modifier).
	 */
    public int getBaseSkillPointsPerLevel() {
        return baseSkillPointsPerLevel;
    }

    /**
     * gets the name of the class.
     * ex: Ranger
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
    	this.name = name;
    }

    /**
     * get the abbreviation for this class.
     */
	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

    /**
     * gets the progression of levels for this class.
     */
	public ClassProgression getClassProgression() {
		return progression;
	}

    /**
     * gets the prereqs for this class.
     * this is used mainly for presige classes, but some core classes need 
     * this as well (ex: Barbarian cannot be lawful)
     */
	public CharacterPrerequisite getPrerequisite() {
	    return prereq;
	}

	/**
	 * certain classes have additional languages available to them.
	 */
	public Collection<Language> getBonusLanguages() {
	    return bonusLanguages;
	}

	/**
	 * gets the feats automatically granted for this class.
	 */
	public Collection<Feat> getFeats() {
	    return feats;
	}

	/**
	 * gets the skill bonuses automatically granted to this class.
	 */
	public Collection<Modifier> getSkills() {
	    return skillModifiers;
	}


    /**
     * @deprecated remove when spring-rcp binder created
     */
    public void setHitDicePerLevelString(String hitDice) {
        setHitDicePerLevel(new DiceExpression(hitDice));
    }

    /**
     * @deprecated remove when spring-rcp binder created.
     */
    public String getHitDicePerLevelString() {
        return getHitDicePerLevel().toString();
    }

	public void addClassSkill(Skill skill) {
		classSkills.add(skill);
	}

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }


    public class DefaultClassProgression implements ClassProgression {
        private final CharacterClass kit;

        public DefaultClassProgression(CharacterClass kit) {
            this.kit = kit;
        }

        public String toString() {
            return kit.toString() + " with " + getClassLevels().size() + " levels";
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