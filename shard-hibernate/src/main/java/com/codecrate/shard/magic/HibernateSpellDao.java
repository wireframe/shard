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
package com.codecrate.shard.magic;

import java.util.Collection;

import com.codecrate.shard.BasicHibernateDao;
import com.codecrate.shard.search.HibernateObjectSearcher;
import com.codecrate.shard.source.Source;

/**
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class HibernateSpellDao extends BasicHibernateDao implements SpellDao, SpellFactory {

	public HibernateSpellDao(HibernateObjectSearcher searcher) {
		super(searcher, Spell.class, "name");
	}

    public Collection getSpells() {
    	return getAllObjects();
    }

    public Spell getSpell(final String name) {
    	return (Spell) getObjectByKey(name);
    }

    public void updateSpell(Spell spell) {
    	updateObject(spell);
    }

    public Spell saveSpell(Spell spell) {
    	return (Spell) saveObject(spell);
    }

    public void deleteSpell(Spell spell) {
    	deleteObject(spell);
    }

    public Spell createSpell(String name, String summary, String school, boolean isArcane, boolean isDivine, Source source) {
    	return new Spell(name, summary, school, isArcane, isDivine, source);
    }

    public Collection searchSpells(String query) {
    	return searchObjects(query);
    }
}