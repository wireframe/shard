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
package com.codecrate.shard.kit;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import net.sf.hibernate.Criteria;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.expression.Expression;

import org.springframework.orm.hibernate.HibernateCallback;
import org.springframework.orm.hibernate.support.HibernateDaoSupport;

import com.codecrate.shard.dice.Dice;
import com.codecrate.shard.feat.DefaultFeat;
import com.codecrate.shard.search.HibernateObjectSearcher;

/**
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class HibernateCharacterClassDao extends HibernateDaoSupport implements CharacterClassDao, CharacterClassFactory {

	private final HibernateObjectSearcher searcher;

	public HibernateCharacterClassDao(HibernateObjectSearcher searcher) {
		this.searcher = searcher;
	}

    public Collection getClasses() {
        return (List) getHibernateTemplate().execute(new HibernateCallback(){
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Criteria query = session.createCriteria(DefaultCharacterClass.class);
                return query.list();
            }
        });
    }

    public CharacterClass getCharacterClass(final String name) {
    	CharacterClass kit = (CharacterClass) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                Criteria query = session.createCriteria(DefaultFeat.class);
                query.add(Expression.eq("name", name));
                return query.uniqueResult();
            }
        });

        if (null == kit) {
            throw new IllegalArgumentException("Unable to find kit " + name);
        }
        return kit;
    }

    public CharacterClass saveClass(CharacterClass kit) {
        String id = (String) getHibernateTemplate().save(kit);
        return (CharacterClass) getHibernateTemplate().load(DefaultCharacterClass.class, id);
    }

    public void updateClass(CharacterClass kit) {
        getHibernateTemplate().saveOrUpdate(kit);
    }

    public void deleteClass(CharacterClass kit) {
        getHibernateTemplate().delete(kit);
    }
    
    public Collection searchClasses(String query) {
    	return searcher.search(DefaultCharacterClass.class, query);
    }

	public CharacterClass createClass(String name, String abbreviation, Dice hitDice) {
		return new DefaultCharacterClass(name, abbreviation, hitDice, 0, null, null, null, null);
	}
}
