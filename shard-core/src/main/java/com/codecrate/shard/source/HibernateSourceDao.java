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
package com.codecrate.shard.source;

import java.io.Serializable;
import java.util.Collection;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class HibernateSourceDao extends HibernateDaoSupport implements SourceDao, InitializingBean {

	
    public Source getSource(final String name) {
        return (Source) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                Criteria query = session.createCriteria(Source.class);
                query.add(Expression.eq("name", name));
                return query.uniqueResult();
            }
        });
    }

	
    public Source getCustomSource() {
		Source customSource = getSource(Source.CUSTOM.getName());
		if (customSource == null) {
			customSource = saveSource(Source.CUSTOM);
		}
		return customSource;
    }

	
    public Collection<Source> getSources() {
		return getHibernateTemplate().loadAll(Source.class);
    }

	
    public Source saveSource(Source source) {
		Serializable id = (Serializable) getHibernateTemplate().save(source);
        return (Source) getHibernateTemplate().load(Source.class, id);
    }
}