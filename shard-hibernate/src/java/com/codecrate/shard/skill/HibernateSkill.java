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

import com.codecrate.shard.Identifiable;
import com.codecrate.shard.ability.Ability;

/**
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek </a>
 */
public class HibernateSkill implements Skill, Identifiable {
    private String name;
    private Ability ability;
    private boolean usableUntrained;
    private boolean armorCheckPenalty;
    private Collection skillSynergies;

    /**
     * hibernate constructor.
     */
    public HibernateSkill() {
    }
    
    public HibernateSkill(String name, boolean usableUntrained, Ability ability, 
            boolean armorPenalty) {
        this.name = name;
        this.usableUntrained = usableUntrained;
        this.ability = ability;
        this.armorCheckPenalty = armorPenalty;
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
    
    public String getName() {
        return name;
    }

    public Ability getAbility() {
        return ability;
    }

    public boolean isUsableUntrained() {
        return usableUntrained;
    }

    public Collection getSkillSynergies() {
        return skillSynergies;
    }

    public boolean hasArmorCheckPenalty() {
        return armorCheckPenalty;
    }
    
    public boolean isArmorCheckPenalty() {
        return armorCheckPenalty;
    }
    /**
     * @param skillSynergies The skillSynergies to set.
     */
    public void setSkillSynergies(Collection skillSynergies) {
        this.skillSynergies = skillSynergies;
    }
    /**
     * @param usableUntrained The usableUntrained to set.
     */
    public void setUsableUntrained(boolean usableUntrained) {
        this.usableUntrained = usableUntrained;
    }
    /**
     * @param ability The ability to set.
     */
    public void setAbility(Ability ability) {
        this.ability = ability;
    }
    /**
     * @param armorCheckPenalty The armorCheckPenalty to set.
     */
    public void setArmorCheckPenalty(boolean armorCheckPenalty) {
        this.armorCheckPenalty = armorCheckPenalty;
    }
}