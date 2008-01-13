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

import org.apache.lucene.analysis.Analyzer;

import com.codecrate.shard.hibernate.BasicHibernateObjectDaoSupport;

/**
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class HibernateFeatDao extends BasicHibernateObjectDaoSupport implements FeatDao {

	private final Analyzer analyzer;

	public HibernateFeatDao(Analyzer analyzer) {
		this.analyzer = analyzer;
	}
	
	@Override
    public Collection<Feat> getFeats() {
    	return getHibernateTemplate().loadAll(Feat.class);
    }

	@Override
    public void updateFeat(Feat feat) {
		updateObject(feat);
    }

	@Override
    public Feat saveFeat(Feat feat) {
		return (Feat) saveObject(feat);
    }

	@Override
    public void deleteFeat(Feat feat) {
    	getHibernateTemplate().delete(feat);
    }

	@Override
    public Collection<Feat> searchFeats(String query) {
		return searchObjects(query);
    }

	@Override
	protected Analyzer getAnalyzer() {
		return analyzer;
	}

	@Override
	protected String getKeyField() {
		return "name";
	}

	@Override
	protected Class getManagedClass() {
		return Feat.class;
	}

	@Override
	protected String[] getSearchableFieldNames() {
		return new String[] {"name", "summary"};
	}
}