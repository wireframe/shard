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
package com.codecrate.shard.skill;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

import com.codecrate.shard.ability.Ability;
import com.codecrate.shard.source.Source;

/**
 * Definition of a Skill.
 * A skill is something that every character class has access to.  For each class 
 * a skill is either a class skill, or a cross class skill.  If a skill should not
 * be available to a class, it should be redefined as a Feat.
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
@Entity
@Indexed
public class Skill implements Comparable {
    @Id
    @DocumentId
    @GeneratedValue 
    private int sequenceId;
    
    @Field(index=Index.TOKENIZED, store=Store.NO)    
    private String name;
    private boolean usableUntrained = true;
    private boolean penalizedWithArmor = false;
    
    @ManyToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private Collection<Skill> childSkillSynergies = new HashSet<Skill>();
    
    @Enumerated
    private Ability ability;
    
    @ManyToOne
    private Source source;

    /**
     * hibernate constructor.
     */
    private Skill() {
    }

    public Skill(String name, Ability ability, Source source) {
        this.name = name;
        this.ability = ability;
        this.source = source;
    }

    public String toString() {
        return name;
    }

    public int hashCode() {
    	return new HashCodeBuilder(3, 7).append(name).toHashCode();
    }

    public boolean equals(Object object) {
    	if (this == object) {
    		return true;
    	}

    	if (!(object instanceof Skill)) {
    		return false;
    	}
    	Skill target = (Skill) object;
    	return new EqualsBuilder()
	    	.append(name, target.name)
	    	.isEquals();
    }

    public int compareTo(Object object) {
        Skill skill = (Skill) object;
        return name.compareTo(skill.name);
    }

	/**
	 * gets the name of the skill.
	 * ex: "Disguise Self"
	 */
    public String getName() {
        return name;
    }

    public void setName(String name) {
    	this.name = name;
    }

    /**
     * gets the ability used for ability bonus for skill checks.
     * @return
     */
    public Ability getAbility() {
        return ability;
    }

    public void setAbility(Ability ability) {
    	this.ability = ability;
    }
    public boolean isUsableUntrained() {
        return usableUntrained;
    }

    public void setUsableUntrained(boolean value) {
    	usableUntrained = value;
    }
	/**
	 * gets the skills that this skill grants a synergy bonus to.
	 * a skill entry needs 5+ ranks in this skill 
	 * to grant a +2 bonus on the child skills.
	 * ex: if 5+ ranks in Tumble, +2 bonus for Jump 
	 */
    public Collection<Skill> getChildSkillSynergies() {
        return childSkillSynergies;
    }

    /**
     * boolean flag for if the skill has an armor check penalty.
     */
    public boolean isPenalizedWithArmor() {
        return penalizedWithArmor;
    }

    public void setPenalizedWithArmor(boolean value) {
    	this.penalizedWithArmor = value;
    }

    /**
     * get the source the skill comes from.
     */
    public Source getSource() {
        return source;
    }
    public void setSource(Source source) {
        this.source = source;
    }
}
