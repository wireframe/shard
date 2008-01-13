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

import com.codecrate.shard.hibernate.ShardHibernateTestSupport;

/**
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class HibernateSpellDaoTest extends ShardHibernateTestSupport {
	private SpellDao spellDao;

	public void setSpellDao(SpellDao dao) {
		this.spellDao = dao;
	}

	protected void onSetUpInTransaction() throws Exception {
		super.onSetUpInTransaction();

        Spell fireball = new Spell("fireball", "burn baby burn!", "Conjuration", null);
		spellDao.saveSpell(fireball);
	}

	public void testLoadsSpells() throws Exception {
        Collection spells = spellDao.getSpells();
        assertFalse(spells.isEmpty());
    }

    public void testSearchForSpells() throws Exception {
    	Collection results = spellDao.searchSpells("fire");
    	assertFalse(results.isEmpty());
    }
}
