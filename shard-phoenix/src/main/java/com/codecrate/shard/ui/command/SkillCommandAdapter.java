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

import java.io.File;
import java.util.Collection;

import com.codecrate.shard.skill.Skill;
import com.codecrate.shard.skill.SkillDao;
import com.codecrate.shard.skill.SkillFactory;
import com.codecrate.shard.transfer.excel.ExcelSkillImporter;

public class SkillCommandAdapter implements ObjectManagerCommandAdapter {

	private final SkillDao skillDao;
	private final SkillFactory skillFactory;

	private String deleteMessagePropertyName;
    private final ExcelSkillImporter importer;

	public SkillCommandAdapter(SkillDao skillDao, SkillFactory skillFactory, ExcelSkillImporter importer) {
		this.skillDao = skillDao;
		this.skillFactory = skillFactory;
        this.importer = importer;
	}

	public String[] getColumnNames() {
		return new String[] {
				"name"
				, "ability"
		};
	}

	public Collection getObjects() {
		return skillDao.getSkills();
	}

	public Object createObject() {
		return skillFactory.createSkill("New Skill");
	}

	public void saveObject(Object object) {
		skillDao.saveSkill((Skill) object);
	}

	public void updateObject(Object object) {
		skillDao.updateSkill((Skill) object);
	}

	public void deleteObject(Object object) {
		skillDao.deleteSkill((Skill) object);
	}

	public String getDeleteMessagePropertyName() {
		return deleteMessagePropertyName;
	}

	public void setDeleteMessagePropertyName(String deleteMessagePropertyName) {
		this.deleteMessagePropertyName = deleteMessagePropertyName;
	}

	public Collection searchObjects(String query) {
		return skillDao.searchSkills(query);
	}

    public Collection importObjects(File file) {
        return importer.importObjects(file);
    }
}
