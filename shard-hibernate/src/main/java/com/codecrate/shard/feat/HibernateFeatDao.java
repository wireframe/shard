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

import com.codecrate.shard.BasicHibernateDao;
import com.codecrate.shard.character.prereq.NullPrerequisite;
import com.codecrate.shard.search.HibernateObjectSearcher;
import com.codecrate.shard.source.Source;

/**
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class HibernateFeatDao extends BasicHibernateDao implements FeatDao, FeatFactory {

	public HibernateFeatDao(HibernateObjectSearcher searcher) {
		super(searcher, DefaultFeat.class, "name");
	}

    public Collection getFeats() {
    	return getAllObjects();
    }

    public Feat getFeat(final String name) {
    	return (Feat) getObjectByKey(name);
    }

    public void updateFeat(Feat feat) {
    	updateObject(feat);
    }

    public Feat saveFeat(Feat feat) {
    	return (Feat) saveObject(feat);
    }

    public void deleteFeat(Feat feat) {
    	deleteObject(feat);
    }

    public Feat createFeat(String name, String summary, String type, Source source) {
    	return new DefaultFeat(name, type, summary, new NullPrerequisite(), source);
    }

    public Collection searchFeats(String query) {
    	return searchObjects(query);
    }
}