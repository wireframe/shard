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
import java.util.List;

import net.sf.hibernate.Criteria;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.expression.Expression;

import org.springframework.orm.hibernate.HibernateCallback;
import org.springframework.orm.hibernate.support.HibernateDaoSupport;

/**
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class HibernateFeatDao extends HibernateDaoSupport implements FeatDao, FeatFactory {
    public Collection getFeats() {
        return (List) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                Criteria query = session.createCriteria(DefaultFeat.class);
                return query.list();
            }
        });
    }

    public Feat getFeat(final String name) {
        Feat feat = (Feat) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                Criteria query = session.createCriteria(DefaultFeat.class);
                query.add(Expression.eq("name", name));
                return query.uniqueResult();
            }
        });
        
        if (null == feat) {
            throw new IllegalArgumentException("Unable to find feat " + name);
        }
        return feat;
    }
    
    public void updateFeat(Feat feat) {
        getHibernateTemplate().saveOrUpdate(feat);
    }

    public Feat saveFeat(Feat feat) {
        String id = (String) getHibernateTemplate().save(feat);
        return (Feat) getHibernateTemplate().load(DefaultFeat.class, id);
    }

    public void deleteSkill(Feat feat) {
        getHibernateTemplate().delete(feat);
    }
    
    public Feat createFeat() {
    	return new DefaultFeat();
    }
}