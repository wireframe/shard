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

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Definition of a class level.
 * ex: Ranger level 1 grants +1 to hit ....
 *
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class DefaultClassLevel implements ClassLevel {
    private String id;
    private ClassProgression progression;
	private int level;
    private int baseAttackBonus;
    private int fortituteSaveBonus;
    private int reflexSaveBonus;
    private int willpowerSaveBonus;

    /**
     * hibernate constructor.
     */
    private DefaultClassLevel() {
    }

    public DefaultClassLevel(int level, ClassProgression progression, int baseAttackBonus, int fortitueSaveBonus, int reflexSaveBonus, int willpowerSaveBonus) {
    	this.level = level;
        this.progression = progression;
        this.baseAttackBonus = baseAttackBonus;
        this.fortituteSaveBonus = fortitueSaveBonus;
        this.reflexSaveBonus = reflexSaveBonus;
        this.willpowerSaveBonus = willpowerSaveBonus;
    }

    public String toString() {
    	return progression.getCharacterClass() + " (" + level + ")";
    }

    public int hashCode() {
        return new HashCodeBuilder(3, 7)
        .append(progression)
        .append(level)
        .toHashCode();
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof DefaultClassLevel)) {
            return false;
        }
        DefaultClassLevel target = (DefaultClassLevel) object;
        return new EqualsBuilder()
            .append(progression, target.progression)
            .append(level, target.level)
            .isEquals();
    }
    public CharacterClass getCharacterClass() {
    	return progression.getCharacterClass();
    }

    public int getLevel() {
    	return level;
    }

    public int getBaseAttackBonus() {
        return baseAttackBonus;
    }
    public int getFortituteSaveBonus() {
        return fortituteSaveBonus;
    }
    public int getReflexSaveBonus() {
        return reflexSaveBonus;
    }
    public int getWillpowerSaveBonus() {
        return willpowerSaveBonus;
    }
}
