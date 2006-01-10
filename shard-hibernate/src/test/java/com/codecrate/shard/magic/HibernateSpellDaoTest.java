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

import com.codecrate.shard.ShardHibernateTestCaseSupport;

/**
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class HibernateSpellDaoTest extends ShardHibernateTestCaseSupport {
	private SpellDao spellDao;
	private SpellFactory spellFactory;

	public void setSpellDao(SpellDao dao) {
		this.spellDao = dao;
	}

    public void setSpellFactory(SpellFactory factory) {
        this.spellFactory = factory;
    }

	protected void onSetUpInTransaction() throws Exception {
		super.onSetUpInTransaction();

        Spell fireball = spellFactory.createSpell("fireball", "burn baby burn!", "Conjuration", null);
		spellDao.saveSpell(fireball);
	}

	public void testLoadsSpells() throws Exception {
        Collection spells = spellDao.getSpells();
        assertFalse(spells.isEmpty());
    }

    public void testGetSpellName() throws Exception {
        Spell spell = (Spell) spellDao.getSpells().iterator().next();
        Spell spell2 = spellDao.getSpell(spell.getName());
        assertNotNull(spell2);
    }

    public void testGetSpellByUnknownNameThrowsException() throws Exception {

        try {
            spellDao.getSpell("invalid spell");
            fail("Exception should be thrown.");
        } catch (IllegalArgumentException expected) { }
    }

    public void testSearchForSpells() throws Exception {
    	Collection results = spellDao.searchSpells("fire");
    	assertFalse(results.isEmpty());
    }
}
