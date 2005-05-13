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
package com.codecrate.shard.race;

import java.util.Collection;

import com.codecrate.shard.ShardHibernateDbUnitTestCaseSupport;

/**
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class HibernateRaceDaoTest extends ShardHibernateDbUnitTestCaseSupport {
    public HibernateRaceDaoTest(String name) throws Exception {
        super(name);
    }

    protected String getDataSetPath() {
        return "SHA_RACE-data.xml";
    }

    public void testLoadsRaces() throws Exception {
        RaceDao dao = (RaceDao) getContext().getBean("raceDao");
        Collection races = dao.getRaces();
        assertFalse(races.isEmpty());
    }
}
