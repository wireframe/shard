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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.codecrate.shard.kit.CharacterClass;
import com.codecrate.shard.kit.ClassLevel;

public class DefaultCharacterProgression implements CharacterProgression {

	private Collection levels;

	public DefaultCharacterProgression(Collection levels) {
		this.levels = levels;
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
	
	public int getLevel() {
		return levels.size();
	}
	
	public ClassLevel getMaxLevel(CharacterClass kit) {
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
	
	/**
	 * returns result formatted like this:
	 * Wizard (5) / Fighter (10)
	 */
	public String toString() {
		StringBuffer result = new StringBuffer();
		int x = 0;
		Iterator classes = getClasses().iterator();
		while (classes.hasNext()) {
			CharacterClass kit = (CharacterClass) classes.next();
			ClassLevel maxLevel = getMaxLevel(kit);
			result.append(maxLevel);
			
			if (x != levels.size()) {
				result.append(" / ");
			}
			x++;
		}
		return result.toString();
	}
}
