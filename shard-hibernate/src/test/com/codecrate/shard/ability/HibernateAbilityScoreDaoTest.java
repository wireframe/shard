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
package com.codecrate.shard.ability;

import com.codecrate.shard.ShardHibernateDbUnitTestCaseSupport;

/**
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class HibernateAbilityScoreDaoTest extends ShardHibernateDbUnitTestCaseSupport {
    public HibernateAbilityScoreDaoTest(String name) throws Exception {
        super(name);
    }

    protected String getDataSetPath() {
        return "src/data/SHA_ABILITY_POINT_COST-data.xml";
    }
    
    public void testLookupOfValidScore() throws Exception {
        AbilityScoreDao abilityScoreDao = (AbilityScoreDao) getContext().getBean("abilityScoreDao");
        int pointCost = abilityScoreDao.getPointCost(10);
        assertEquals(2, pointCost);
    }
    
    public void testZeroReturnedWhenScoreNotFound() throws Exception {
        AbilityScoreDao abilityScoreDao = (AbilityScoreDao) getContext().getBean("abilityScoreDao");
        int pointCost = abilityScoreDao.getPointCost(100);
        assertEquals(0, pointCost);
    }
}