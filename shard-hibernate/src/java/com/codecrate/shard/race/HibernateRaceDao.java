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

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import net.sf.hibernate.Criteria;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;

import org.springframework.orm.hibernate.HibernateCallback;
import org.springframework.orm.hibernate.support.HibernateDaoSupport;

import com.codecrate.shard.lucene.LuceneSearcher;

/**
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class HibernateRaceDao extends HibernateDaoSupport implements RaceDao, RaceFactory {
	private final LuceneSearcher searcher;
	
	public HibernateRaceDao(LuceneSearcher searcher) {
		this.searcher = searcher;
	}
	
    public Collection getRaces() {
        return (List) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Criteria query = session.createCriteria(DefaultRace.class);
                return query.list();
            }
        });
    }
    
    public Race saveRace(Race race) {
        String id = (String) getHibernateTemplate().save(race);
        return (Race) getHibernateTemplate().load(DefaultRace.class, id);
    }
    
    public Race createRace(String name) {
    	return new DefaultRace(name, DefaultRacialSize.MEDIUM, null, null, null, 0, null, null, null, null, null, 0 );
    }

	public void deleteRace(Race race) {
		getHibernateTemplate().delete(race);
	}

	public void updateRace(Race race) {
		getHibernateTemplate().save(race);
	}
	
	public Collection searchRaces(String query) {
		return searcher.search(DefaultRace.class, query);
	}
}
