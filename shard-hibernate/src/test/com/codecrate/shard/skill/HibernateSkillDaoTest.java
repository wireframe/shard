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

import net.sf.hibernate.Session;

import com.codecrate.shard.ShardHibernateDbUnitTestCaseSupport;

/**
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class HibernateSkillDaoTest extends ShardHibernateDbUnitTestCaseSupport {
    public HibernateSkillDaoTest(String name) throws Exception {
        super(name);
    }

    protected String getDataSetPath() {
        return "src/data/SHA_SKILL-data.xml";
    }

    public void testLoadsSkills() throws Exception {
        Session session = getSessionFactory().openSession();
        HibernateSkillDao dao = new HibernateSkillDao(session);
        Collection races = dao.getSkills();
        assertFalse(races.isEmpty());
    }
}