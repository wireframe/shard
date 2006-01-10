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
package com.codecrate.shard.magic;

import java.util.Collection;
import java.util.List;

import net.sf.hibernate.Criteria;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.expression.Expression;

import org.springframework.orm.hibernate.HibernateCallback;
import org.springframework.orm.hibernate.support.HibernateDaoSupport;

import com.codecrate.shard.search.HibernateObjectSearcher;
import com.codecrate.shard.source.Source;

/**
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class HibernateSpellDao extends HibernateDaoSupport implements SpellDao, SpellFactory {

	private final HibernateObjectSearcher searcher;

	public HibernateSpellDao(HibernateObjectSearcher searcher) {
		this.searcher = searcher;
	}

    public Collection getSpells() {
        return (List) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                Criteria query = session.createCriteria(Spell.class);
                return query.list();
            }
        });
    }

    public Spell getSpell(final String name) {
        Spell spell = (Spell) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                Criteria query = session.createCriteria(Spell.class);
                query.add(Expression.eq("name", name));
                return query.uniqueResult();
            }
        });

        if (null == spell) {
            throw new IllegalArgumentException("Unable to find spell " + name);
        }
        return spell;
    }

    public void updateSpell(Spell spell) {
        getHibernateTemplate().saveOrUpdate(spell);
    }

    public Spell saveSpell(Spell spell) {
        String id = (String) getHibernateTemplate().save(spell);
        return (Spell) getHibernateTemplate().load(Spell.class, id);
    }

    public void deleteSpell(Spell spell) {
        getHibernateTemplate().delete(spell);
    }

    public Spell createSpell(String name, String summary, String school, Source source) {
    	return new Spell(name, summary, school, source);
    }

    public Collection searchSpells(String query) {
    	return searcher.search(Spell.class, query);
    }
}