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

import com.codecrate.shard.ShardHibernateTestCaseSupport;

/**
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class HibernateSkillDaoTest extends ShardHibernateTestCaseSupport {

	private SkillDao skillDao;
	private SkillFactory skillFactory;
	
	public void setSkillDao(SkillDao dao) {
		this.skillDao = dao;
	}
	
	public void setSkillFactory(SkillFactory factory) {
		this.skillFactory = factory;
	}

	protected void onSetUpInTransaction() throws Exception {
		super.onSetUpInTransaction();
		skillDao.saveSkill(DefaultSkill.APPRAISE);
		skillDao.saveSkill(DefaultSkill.BALANCE);
		skillDao.saveSkill(DefaultSkill.CLIMB);
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
        Skill skill = skillFactory.createSkill("test skill", null, true, true);
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
