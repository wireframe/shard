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

import java.util.List;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.codecrate.shard.race.Race;

/**
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class HibernateAgeCategoryDao implements AgeCategoryDao {
    private static final Log LOG = LogFactory.getLog(HibernateAgeCategoryDao.class);
    
    private final Session session;
    
    public HibernateAgeCategoryDao(Session session) {
        this.session = session;
    }
    
    public AgeCategory getAgeCategory(int age, Race race) {
        try {
            Query query = session.createQuery("from RaceAgeEntry value where value.race = :race");
            query.setParameter("race", race);
            List values = query.list();
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
        } catch (HibernateException e) {
            LOG.error("Unable to lookup age category.", e);
        }
        LOG.warn("Unable to find age category for race " + race + " and age " + age);
        return null;
    }
}
