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
package com.codecrate.shard.feat;

import java.util.Collection;

import com.codecrate.shard.hibernate.ShardHibernateTestSupport;

/**
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class HibernateFeatDaoTest extends ShardHibernateTestSupport {
	private FeatDao featDao;

	public void setFeatDao(FeatDao dao) {
		this.featDao = dao;
	}

	protected void onSetUpInTransaction() throws Exception {
		super.onSetUpInTransaction();

		featDao.saveFeat(Feat.ARMOR_PROFICIENCY_HEAVY);
	}

	public void testLoadsFeats() throws Exception {
        Collection feats = featDao.getFeats();
        assertFalse(feats.isEmpty());
    }

    public void testSearchForFeats() throws Exception {
    	Collection results = featDao.searchFeats("armor");
    	assertFalse(results.isEmpty());
    }

    public void testExceptionThrownIfSavingExistingFeat() throws Exception {
    	try {
        	featDao.saveFeat(Feat.ARMOR_PROFICIENCY_HEAVY);
    	} catch (IllegalArgumentException expected) { }
    }

    public void testSearchAddsWildcard() throws Exception {
    	Collection results = featDao.searchFeats("arm");
    	assertFalse(results.isEmpty());
    }
}
