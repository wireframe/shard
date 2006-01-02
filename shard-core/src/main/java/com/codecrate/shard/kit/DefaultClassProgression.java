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
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class DefaultClassProgression implements ClassProgression {

    private String id;
    private CharacterClass kit;
	private Set levels = new HashSet();

    /**
     * hibernate constructor.
     */
    private DefaultClassProgression() {
    }

	public DefaultClassProgression(CharacterClass kit) {
        this.kit = kit;
	}

    public String toString() {
        return kit.toString();
    }

    public int hashCode() {
        return new HashCodeBuilder(3, 7)
        .append(kit)
        .toHashCode();
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof DefaultClassProgression)) {
            return false;
        }
        DefaultClassProgression target = (DefaultClassProgression) object;
        return new EqualsBuilder()
            .append(kit, target.kit)
            .isEquals();
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
        levels.add(new DefaultClassLevel(getMaxLevel() + 1, this, baseAttackBonus, fortitudeSave, reflexSave, willSave));
    }

    public CharacterClass getCharacterClass() {
        return kit;
    }
}
