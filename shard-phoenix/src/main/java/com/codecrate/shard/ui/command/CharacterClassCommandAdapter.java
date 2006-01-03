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

import com.codecrate.shard.dice.RandomDice;
import com.codecrate.shard.kit.CharacterClass;
import com.codecrate.shard.kit.CharacterClassDao;
import com.codecrate.shard.kit.CharacterClassFactory;
import com.codecrate.shard.transfer.ObjectImporter;

public class CharacterClassCommandAdapter extends AbstractObjectManagerCommandAdapter {

	private final CharacterClassDao characterClassDao;
	private final CharacterClassFactory characterClassFactory;

	private String deleteMessagePropertyName;

	public CharacterClassCommandAdapter(CharacterClassDao characterClassDao, CharacterClassFactory characterClassFactory, ObjectImporter importer) {
		super(importer);
		this.characterClassDao = characterClassDao;
		this.characterClassFactory = characterClassFactory;
	}

	public String[] getColumnNames() {
		return new String[] {
				"name"
				, "abbreviation"
                , "source"
		};
	}

	public Collection getObjects() {
		return characterClassDao.getClasses();
	}

	public Object createObject() {
		return characterClassFactory.createClass("New Kit", "", new RandomDice(8), null);
	}

	public void saveObject(Object object) {
		characterClassDao.saveClass((CharacterClass) object);
	}

	public void updateObject(Object object) {
		characterClassDao.updateClass((CharacterClass) object);
	}

	public void deleteObject(Object object) {
		characterClassDao.deleteClass((CharacterClass) object);
	}

	public String getDeleteMessagePropertyName() {
		return deleteMessagePropertyName;
	}

	public void setDeleteMessagePropertyName(String deleteMessagePropertyName) {
		this.deleteMessagePropertyName = deleteMessagePropertyName;
	}

	public Collection searchObjects(String query) {
		return characterClassDao.searchClasses(query);
	}
}
