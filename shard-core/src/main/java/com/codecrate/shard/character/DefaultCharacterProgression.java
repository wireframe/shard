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
package com.codecrate.shard.character;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.codecrate.shard.kit.CharacterClass;
import com.codecrate.shard.kit.ClassLevel;

public class DefaultCharacterProgression implements CharacterProgression {
    private String id;
    private PlayerCharacter character;
	private int experience;
	private Set levels = new HashSet();

    /**
     * hibernate constructor
     */
    private DefaultCharacterProgression() {
    }

	public DefaultCharacterProgression(PlayerCharacter playerCharacter) {
        this.character = playerCharacter;
	}

	public Collection getClasses() {
		Collection classes = new ArrayList();
		Iterator it = levels.iterator();
		while (it.hasNext()) {
			CharacterLevel level = (CharacterLevel) it.next();
			CharacterClass characterClass = level.getClassLevel().getCharacterClass();
			if (!classes.contains(characterClass)) {
				classes.add(characterClass);
			}
		}
		return classes;
	}

	public int getCharacterLevel() {
		return levels.size();
	}

	public ClassLevel getClassLevel(CharacterClass kit) {
		ClassLevel level = null;
		Iterator it = levels.iterator();
		while (it.hasNext()) {
			CharacterLevel characterLevel = (CharacterLevel) it.next();
			ClassLevel classLevel = characterLevel.getClassLevel();
			if (kit.equals(classLevel.getCharacterClass())) {
				if (null == level || level.getLevel() < classLevel.getLevel())
					level = classLevel;
			}
		}
		return level;
	}

	public String getDescription() {
		StringBuffer result = new StringBuffer();
		Iterator classes = getClasses().iterator();
		while (classes.hasNext()) {
			CharacterClass kit = (CharacterClass) classes.next();
			ClassLevel maxLevel = getClassLevel(kit);
			result.append(kit.getAbbreviation() + " " + maxLevel.getLevel());

			if (classes.hasNext()) {
				result.append(" / ");
			}
		}
		return result.toString();
	}

    public void addLevel(CharacterClass kit, int hitPoints, Collection skillRanks) {
        int level = 1;
        ClassLevel currentClassLevel = this.getClassLevel(kit);
        if (null != currentClassLevel) {
            level = currentClassLevel.getLevel() + 1;
        }
        ClassLevel nextClassLevel = kit.getClassProgression().getClassLevel(level);
        levels.add(new DefaultCharacterLevel(this, levels.size() + 1, hitPoints, nextClassLevel, skillRanks));
    }

	public String toString() {
		return getDescription();
	}

    public Collection getCharacterLevels() {
        return levels;
    }

    public CharacterLevel getCharacterLevel(int level) {
	    CharacterLevel kit = null;
	    Iterator it = levels.iterator();
	    while (it.hasNext()) {
	        CharacterLevel object = (CharacterLevel) it.next();
	        if (level == object.getLevel()) {
	            return object;
	        }
	    }
	    return kit;
    }

    public BigDecimal getMulticlassExperiencePenalty() {

        return null;
    }

	public int getExperience() {
		return experience;
	}

    public PlayerCharacter getCharacter() {
        return character;
    }
}
