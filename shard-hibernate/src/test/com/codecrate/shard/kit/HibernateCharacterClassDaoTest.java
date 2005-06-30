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

import com.codecrate.shard.ShardHibernateTestCaseSupport;

/**
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class HibernateCharacterClassDaoTest extends ShardHibernateTestCaseSupport {
	private CharacterClassDao characterClassDao;
	
	public void setCharacterClassDao(CharacterClassDao dao) {
		this.characterClassDao = dao;
	}
	
	protected void onSetUpInTransaction() throws Exception {
		super.onSetUpInTransaction();
		characterClassDao.saveClass(DefaultCharacterClass.FIGHTER);
	}

    public void testLoadsClasses() throws Exception {
        Collection classes = characterClassDao.getClasses();
        assertFalse(classes.isEmpty());
    }
}
