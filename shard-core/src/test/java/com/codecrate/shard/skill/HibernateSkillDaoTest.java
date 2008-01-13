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
package com.codecrate.shard.skill;

import java.util.Collection;

import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

/**
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class HibernateSkillDaoTest extends AbstractTransactionalDataSourceSpringContextTests {
	private SkillDao skillDao;

	public HibernateSkillDaoTest() {
		super();
		setDefaultRollback(true);
	}
	
	protected final String[] getConfigLocations() {
		return new String[] {
				"/shard-hibernate-context.xml"
				, "/test-datasource.xml"
		}; 
	}

	public void setSkillDao(SkillDao dao) {
		this.skillDao = dao;
	}

	protected void onSetUpInTransaction() throws Exception {
		super.onSetUpInTransaction();
        Skill appraise = new Skill("appraise", null, null);
        Skill balance = new Skill("balance", null, null);
        Skill climb = new Skill("climb", null, null);
		skillDao.saveSkill(appraise);
		skillDao.saveSkill(balance);
		skillDao.saveSkill(climb);
		
	}

    public void testLoadsSkills() throws Exception {
        Collection skills = skillDao.getSkills();
        assertFalse(skills.isEmpty());
    }

    public void testLoadsUntrainedSkills() throws Exception {
        Collection skills = skillDao.getUntrainedSkills();
        assertFalse(skills.isEmpty());
    }

    public void testSkillCreation() throws Exception {
        Skill skill = new Skill("test skill", null, null);
        skill = skillDao.saveSkill(skill);
        assertNotNull(skill);
    }

    public void testGetSkillByUnknownNameThrowsException() throws Exception {
        try {
            skillDao.getSkill("invalid skill");
            fail("Exception should be thrown.");
        } catch (IllegalArgumentException expected) { }
    }

    public void testSearchForSkills() throws Exception {
    	Collection results = skillDao.searchSkills("balance");
    	assertFalse(results.isEmpty());
    }
}
