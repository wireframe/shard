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

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.codecrate.shard.character.prereq.CharacterPrerequisite;
import com.codecrate.shard.character.prereq.FeatPrerequisite;
import com.codecrate.shard.character.prereq.NullPrerequisite;

public class DefaultFeat implements Feat {
    public static final DefaultFeat ARMOR_PROFICIENCY_LIGHT= new DefaultFeat("Armor Proficiency (Light)",
            "General", "",
            new NullPrerequisite());
    public static final DefaultFeat ARMOR_PROFICIENCY_MEDIUM = new DefaultFeat("Armor Proficiency (Medium)",
            "General", "",
            new FeatPrerequisite(ARMOR_PROFICIENCY_LIGHT));
    public static final DefaultFeat ARMOR_PROFICIENCY_HEAVY = new DefaultFeat("Armor Proficiency (Heavy)",
            "General", "",
            new FeatPrerequisite(ARMOR_PROFICIENCY_MEDIUM));
    public static final DefaultFeat SHIELD_PROFICIENCY = new DefaultFeat("Shield Proficiency",
            "General", "",
            new NullPrerequisite());
    public static final DefaultFeat TOWER_SHIELD_PROFICIENCY = new DefaultFeat("Tower Shield Proficiency",
            "General", "",
            new FeatPrerequisite(SHIELD_PROFICIENCY));

    private String id;
    private String name;
    private String type;
    private String summary;
    private CharacterPrerequisite prerequisite;

    /**
     * hibernate constructor.
     */
    private DefaultFeat() {
    }

    public DefaultFeat(String name, String type,
    		String summary, CharacterPrerequisite prerequisite) {
        this.name = name;
        this.type = type;
        this.summary = summary;
        this.prerequisite = prerequisite;
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
    	
    	if (!(object instanceof DefaultFeat)) {
    		return false;
    	}
    	DefaultFeat target = (DefaultFeat) object;
    	return new EqualsBuilder()
	    	.append(name, target.name)
	    	.isEquals();    	
    }
    
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getSummary() {
    	return summary;
    }

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
}
