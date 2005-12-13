/*
 * Copyright 2002-2004 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.codecrate.shard.ui.command;

import java.util.Collection;

import com.codecrate.shard.magic.Spell;
import com.codecrate.shard.magic.SpellDao;
import com.codecrate.shard.magic.SpellFactory;
import com.codecrate.shard.transfer.ObjectImporter;

public class SpellCommandAdapter extends AbstractObjectManagerCommandAdapter
implements ObjectManagerCommandAdapter {

	private final SpellDao spellDao;
	private final SpellFactory spellFactory;

	private String deleteMessagePropertyName;

	public SpellCommandAdapter(SpellDao spellDao, SpellFactory spellFactory, ObjectImporter importer) {
        super(importer);
		this.spellDao = spellDao;
		this.spellFactory = spellFactory;
	}

	public String[] getColumnNames() {
		return new String[] {
				"name"
				, "summary"
                , "source.abbreviation"
		};
	}

	public Collection getObjects() {
		return spellDao.getSpells();
	}

	public Object createObject() {
		return spellFactory.createSpell("New Spell", "Summary", null);
	}

	public void saveObject(Object object) {
		spellDao.saveSpell((Spell) object);
	}

	public void updateObject(Object object) {
		spellDao.updateSpell((Spell) object);
	}

	public void deleteObject(Object object) {
		spellDao.deleteSpell((Spell) object);
	}

	public String getDeleteMessagePropertyName() {
		return deleteMessagePropertyName;
	}

	public void setDeleteMessagePropertyName(String deleteMessagePropertyName) {
		this.deleteMessagePropertyName = deleteMessagePropertyName;
	}

	public Collection searchObjects(String query) {
		return spellDao.searchSpells(query);
	}
}
