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

import java.util.List;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class HibernateAbilityScoreDao implements AbilityScoreDao {
    private static final Log LOG = LogFactory.getLog(HibernateAbilityScoreDao.class);
    
    private final Session session;
    
    public HibernateAbilityScoreDao(Session session) {
        this.session = session;
    }
    
    public int getPointCost(int score) {
        try {
            Query query = session.createQuery("from PointCostValue value where value.abilityScore = :abilityScore");
            query.setInteger("abilityScore", score);
            List values = query.list();
            if (1 == values.size()) {
                PointCostValue value = (PointCostValue) values.iterator().next();
                return value.getPointCost();
            }
        } catch (HibernateException e) {
            LOG.error("Unable to lookup point cost value.", e);
        }
        LOG.info("Unable to find point cost for score " + score);
        return 0;
    }

}
