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

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.codecrate.shard.ability.Ability;
import com.codecrate.shard.source.Source;

/**
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek </a>
 */
public class DefaultSkill implements Skill, Comparable {
    private String id;
    private String name;
    private boolean usableUntrained;
    private Collection childSkillSynergies = new HashSet();
    private Ability ability;
    private boolean penalizedWithArmor;
    private Source source;

    /**
     * hibernate constructor.
     */
    private DefaultSkill() {
    }

    public DefaultSkill(String name, boolean usableUntrained, Ability ability,
            boolean armorPenalty, Source source) {
        this.name = name;
        this.usableUntrained = usableUntrained;
        this.ability = ability;
        this.penalizedWithArmor = armorPenalty;
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

    	if (!(object instanceof DefaultSkill)) {
    		return false;
    	}
    	DefaultSkill target = (DefaultSkill) object;
    	return new EqualsBuilder()
	    	.append(name, target.name)
	    	.isEquals();
    }

    public int compareTo(Object object) {
        DefaultSkill skill = (DefaultSkill) object;
        return name.compareTo(skill.name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
    	this.name = name;
    }

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
    public Collection getChildSkillSynergies() {
        return childSkillSynergies;
    }

    public boolean isPenalizedWithArmor() {
        return penalizedWithArmor;
    }

    public void setPenalizedWithArmor(boolean value) {
    	this.penalizedWithArmor = value;
    }

    public Source getSource() {
        return source;
    }
    public void setSource(Source source) {
        this.source = source;
    }
}