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

import java.sql.SQLException;
import java.util.List;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate.HibernateCallback;
import org.springframework.orm.hibernate.support.HibernateDaoSupport;

/**
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class HibernateAbilityScoreDao extends HibernateDaoSupport implements AbilityScoreDao {
    private static final Log LOG = LogFactory.getLog(HibernateAbilityScoreDao.class);
    
    public int getPointCost(final int score) {
        List values= (List) getHibernateTemplate().execute(new HibernateCallback() {

            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery("from PointCostValue value where value.id = :abilityScore");
                query.setInteger("abilityScore", score);
                return query.list();
            }
            
        });

        if (1 == values.size()) {
            PointCostValue value = (PointCostValue) values.iterator().next();
            return value.getPointCost();
        }
        LOG.info("Unable to find point cost for score " + score);
        return 0;
    }

}
