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
package com.codecrate.shard;

import java.util.Collection;

import net.sf.hibernate.Criteria;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.expression.Expression;
import net.sf.hibernate.property.DirectPropertyAccessor;
import net.sf.hibernate.property.PropertyAccessor;

import org.springframework.orm.hibernate.HibernateCallback;
import org.springframework.orm.hibernate.support.HibernateDaoSupport;

import com.codecrate.shard.search.HibernateObjectSearcher;

/**
 * helper object to centralize common tasks when working with objects.
 */
public class BasicHibernateDao extends HibernateDaoSupport {

	private final HibernateObjectSearcher searcher;
	private final Class managedClass;
	private final String keyField;

	public BasicHibernateDao(HibernateObjectSearcher searcher, Class managedClass, String keyField) {
		this.searcher = searcher;
		this.managedClass = managedClass;
		this.keyField = keyField;
	}

    public Collection getAllObjects() {
    	return getHibernateTemplate().loadAll(managedClass);
    }

    public Object getObjectByKey(final String key) {
        Object object = getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                Criteria query = session.createCriteria(managedClass);
                query.add(Expression.eq(keyField, key));
                return query.uniqueResult();
            }
        });

        if (null == object) {
            throw new IllegalArgumentException("Unable to find " + managedClass.getName() + " with " + keyField + " = " + key);
        }
        return object;
    }

    /**
     * ensure that the object is unique before saving.
     * @param object
     */
    public void updateObject(Object object) {
    	assertUniqueObject(object);
        getHibernateTemplate().saveOrUpdate(object);
    }

    /**
     * ensure that the object is unique before saving.
     * @param object
     * @return
     */
    public Object saveObject(Object object) {
    	assertUniqueObject(object);
        String id = (String) getHibernateTemplate().save(object);
        return getHibernateTemplate().load(managedClass, id);
    }

	private void assertUniqueObject(Object object) {
		if (doesObjectAlreadyExist(object)) {
    		throw new IllegalArgumentException("Object already exists with the same " + keyField);
    	}
	}

    private boolean doesObjectAlreadyExist(final Object object) {
        Object result = getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                PropertyAccessor propertyAccessor = new DirectPropertyAccessor();
                Object keyValue = propertyAccessor.getGetter(managedClass, keyField).get(object);

            	Criteria query = session.createCriteria(managedClass);
                query.add(Expression.eq(keyField, keyValue));

                return query.uniqueResult();
            }
        });

        return null != result;
    }

    public void deleteObject(Object object) {
        getHibernateTemplate().delete(object);
    }

    public Collection searchObjects(String query) {
    	return searcher.search(managedClass, query);
    }
}
