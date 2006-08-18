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

import com.codecrate.shard.BasicHibernateDao;
import com.codecrate.shard.character.prereq.NullPrerequisite;
import com.codecrate.shard.dice.Dice;
import com.codecrate.shard.search.HibernateObjectSearcher;
import com.codecrate.shard.source.Source;

/**
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class HibernateCharacterClassDao extends BasicHibernateDao implements CharacterClassDao, CharacterClassFactory {

	public HibernateCharacterClassDao(HibernateObjectSearcher searcher) {
		super(searcher, DefaultCharacterClass.class, "name");
	}

    public Collection getClasses() {
    	return getAllObjects();
    }

    public CharacterClass getCharacterClass(final String name) {
    	return (CharacterClass) getObjectByKey(name);
    }

    public CharacterClass saveClass(CharacterClass kit) {
    	return (CharacterClass) saveObject(kit);
    }

    public void updateClass(CharacterClass kit) {
    	updateObject(kit);
    }

    public void deleteClass(CharacterClass kit) {
    	deleteObject(kit);
    }

    public Collection searchClasses(String query) {
    	return searchObjects(query);
    }

	public CharacterClass createClass(String name, String abbreviation, Dice hitDice, Source source) {
		return new DefaultCharacterClass(name, abbreviation, hitDice, 0, new NullPrerequisite(), source);
	}
}
