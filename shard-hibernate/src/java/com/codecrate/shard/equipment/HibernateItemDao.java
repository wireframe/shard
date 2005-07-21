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
package com.codecrate.shard.equipment;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import net.sf.hibernate.Criteria;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;

import org.springframework.orm.hibernate.HibernateCallback;
import org.springframework.orm.hibernate.support.HibernateDaoSupport;

/**
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class HibernateItemDao extends HibernateDaoSupport implements ItemDao, ItemFactory {
    public Collection getItems() {
        return (List) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                Criteria query = session.createCriteria(Item.class);
                return query.list();
            }
        });
    }

    public Item createItem(String name) {
    	return new DefaultItem(name, new BigDecimal(0), new Money(1, DefaultCurrency.COPPER));
    }
    
    public Item saveItem(Item item) {
        String id = (String) getHibernateTemplate().save(item);
        return (Item) getHibernateTemplate().load(DefaultItem.class, id);
    }
    
    public void updateItem(Item item) {
        getHibernateTemplate().saveOrUpdate(item);
    }

    public void deleteItem(Item item) {
        getHibernateTemplate().delete(item);
    }
    

}