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
package com.codecrate.shard.feat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

import com.codecrate.shard.character.prereq.CharacterPrerequisite;
import com.codecrate.shard.character.prereq.FeatPrerequisite;
import com.codecrate.shard.character.prereq.NullPrerequisite;
import com.codecrate.shard.source.Source;

/**
 * Feat is a unique ability
 */
@Entity
@Indexed
public class Feat implements Comparable {
    public static final Feat ARMOR_PROFICIENCY_LIGHT= new Feat("Armor Proficiency (Light)",
            "General", "",
            new NullPrerequisite(), null);
    public static final Feat ARMOR_PROFICIENCY_MEDIUM = new Feat("Armor Proficiency (Medium)",
            "General", "",
            new FeatPrerequisite(ARMOR_PROFICIENCY_LIGHT), null);
    public static final Feat ARMOR_PROFICIENCY_HEAVY = new Feat("Armor Proficiency (Heavy)",
            "General", "",
            new FeatPrerequisite(ARMOR_PROFICIENCY_MEDIUM), null);
    public static final Feat SHIELD_PROFICIENCY = new Feat("Shield Proficiency",
            "General", "",
            new NullPrerequisite(), null);
    public static final Feat TOWER_SHIELD_PROFICIENCY = new Feat("Tower Shield Proficiency",
            "General", "",
            new FeatPrerequisite(SHIELD_PROFICIENCY), null);

    @Id
    @DocumentId
    @GeneratedValue 
    private int sequenceId;

    private String type;
    
    @Field(index=Index.TOKENIZED, store=Store.NO)    
    private String name;
    @Field(index=Index.TOKENIZED, store=Store.NO)    
    private String summary;
    private transient CharacterPrerequisite prerequisite;

    @ManyToOne
    private Source source;

    /**
     * hibernate constructor.
     */
    private Feat() {
    }

    public Feat(String name, String type,
    		String summary, CharacterPrerequisite prerequisite, Source source) {
        this.name = name;
        this.type = type;
        this.summary = summary;
        this.prerequisite = prerequisite;
        this.source = source;
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

    	if (!(object instanceof Feat)) {
    		return false;
    	}
    	Feat target = (Feat) object;
    	return new EqualsBuilder()
	    	.append(name, target.name)
	    	.isEquals();
    }

    public int compareTo(Object object) {
        Feat feat = (Feat) object;
        return name.compareTo(feat.name);
    }

    public String getName() {
        return name;
    }

    /**
     * get the type of feat.
     * ex: General, Item Creation, Meta Magic
     */
    public String getType() {
        return type;
    }

    /**
     * get a brief summary of the feat.
     * ex: Add +2 bonus to Spot and Listen checks. 
     */
    public String getSummary() {
    	return summary;
    }

    /**
     * get any prerequisites required before a character can aquire this feat.
     */
    public CharacterPrerequisite getPrerequisite() {
        return prerequisite;
    }

	public void setName(String name) {
		this.name = name;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }
}
