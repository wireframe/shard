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
package com.codecrate.shard.character;

import java.sql.SQLException;
import java.util.List;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate.HibernateCallback;
import org.springframework.orm.hibernate.support.HibernateDaoSupport;

import com.codecrate.shard.race.Race;

/**
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class HibernateAgeCategoryDao extends HibernateDaoSupport implements AgeCategoryDao {
    private static final Log LOG = LogFactory.getLog(HibernateAgeCategoryDao.class);
    
    public AgeCategory getAgeCategory(int age, final Race race) {
        List values = (List) getHibernateTemplate().execute(new HibernateCallback() {

            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery("from RaceAgeEntry value where value.race = :race");
                query.setParameter("race", race);
                return query.list();
            }
            
        });
        if (1 == values.size()) {
            RaceAgeEntry value = (RaceAgeEntry) values.iterator().next();
            
            if (age < value.getAgeAdult()) {
                LOG.info("Age is not yet considered adult");
                return null;
            }
            if (age < value.getAgeMiddleAge()) {
                return CummulativeAgeCategory.ADULT;
            }
            if (age < value.getAgeOld()) {
                return CummulativeAgeCategory.MIDDLE_AGE;
            }
            if (age < value.getAgeVenerable()) {
                return CummulativeAgeCategory.OLD;
            }
            if (age >= value.getAgeVenerable()) {
                return CummulativeAgeCategory.VENERABLE;
            }
        }
        LOG.warn("Unable to find age category for race " + race + " and age " + age);
        return null;
    }
}
