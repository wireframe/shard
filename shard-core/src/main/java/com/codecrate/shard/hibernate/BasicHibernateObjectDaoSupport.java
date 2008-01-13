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
package com.codecrate.shard.hibernate;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.property.DirectPropertyAccessor;
import org.hibernate.property.PropertyAccessor;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * helper object to centralize common tasks when working with objects.
 */
public abstract class BasicHibernateObjectDaoSupport extends HibernateDaoSupport {
	private static final Log LOG = LogFactory.getLog(BasicHibernateObjectDaoSupport.class);

	protected abstract Class getManagedClass();
	
	protected abstract String getKeyField();
	
	protected abstract String[] getSearchableFieldNames();

	protected abstract Analyzer getAnalyzer();

    protected Collection getAllObjects() {
    	return getHibernateTemplate().loadAll(getManagedClass());
    }

    /**
     * ensures that the object exists.
     */
    protected Object getObjectByKey(final String key) {
        Object object = getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                Criteria query = session.createCriteria(getManagedClass());
                query.add(Expression.eq(getKeyField(), key));
                return query.uniqueResult();
            }
        });

        if (null == object) {
            throw new IllegalArgumentException("Unable to find " + getManagedClass().getName() + " with " + getKeyField() + " = " + key);
        }
        return object;
    }

    /**
     * ensures that the object is unique before saving.
     */
    protected void updateObject(Object object) {
    	assertUniqueObject(object);
        getHibernateTemplate().saveOrUpdate(object);
    }

    /**
     * ensures that the object is unique before saving.
     */
    protected  Object saveObject(Object object) {
    	assertUniqueObject(object);
        Serializable id = getHibernateTemplate().save(object);
        return getHibernateTemplate().load(getManagedClass(), id);
    }

	private void assertUniqueObject(Object object) {
		if (doesObjectAlreadyExist(object)) {
    		throw new IllegalArgumentException("Object already exists with the same " + getKeyField());
    	}
	}

    private boolean doesObjectAlreadyExist(final Object object) {
        Object result = getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                PropertyAccessor propertyAccessor = new DirectPropertyAccessor();
                Object keyValue = propertyAccessor.getGetter(getManagedClass(), getKeyField()).get(object);

            	Criteria query = session.createCriteria(getManagedClass());
                query.add(Expression.eq(getKeyField(), keyValue));

                return query.uniqueResult();
            }
        });

        return null != result;
    }

    protected void deleteObject(Object object) {
        getHibernateTemplate().delete(object);
    }

    protected Collection searchObjects(String query) {
		FullTextSession fullTextSession = Search.createFullTextSession(getSession());
		try {
			final MultiFieldQueryParser parser = new MultiFieldQueryParser(getSearchableFieldNames(), getAnalyzer());
			org.apache.lucene.search.Query luceneQuery = parser.parse(query);
			FullTextQuery hibernateQuery = fullTextSession.createFullTextQuery(luceneQuery, getManagedClass());
			return hibernateQuery.list();
		} catch (ParseException e) {
			LOG.error("Error parsing query: " + query, e);
			return Collections.EMPTY_SET;
		}
    }
}
