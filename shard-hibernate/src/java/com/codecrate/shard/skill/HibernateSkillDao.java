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
package com.codecrate.shard.skill;

import java.util.Collection;
import java.util.List;

import net.sf.hibernate.Criteria;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.expression.Expression;

import org.springframework.orm.hibernate.HibernateCallback;
import org.springframework.orm.hibernate.support.HibernateDaoSupport;

import com.codecrate.shard.ability.Ability;

/**
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class HibernateSkillDao extends HibernateDaoSupport implements SkillDao {
    public Collection getSkills() {
        return (List) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                Criteria query = session.createCriteria(DefaultSkill.class);
                return query.list();
            }
        });
    }

    public Collection getUntrainedSkills() {
        return (List) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                Criteria query = session.createCriteria(DefaultSkill.class);
                query.add(Expression.eq("usableUntrained", Boolean.FALSE));
                return query.list();
            }
        });
    }
    
    public Skill createSkill(String name, boolean usableUntrained, Ability ability, boolean armorPenalty) {
        Skill skill = new DefaultSkill(name, usableUntrained, ability, armorPenalty);
        String id = (String) getHibernateTemplate().save(skill);
        return (Skill) getHibernateTemplate().load(DefaultSkill.class, id);
    }
    
    public Skill getSkill(final String name) {
        Skill skill = (Skill) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                Criteria query = session.createCriteria(DefaultSkill.class);
                query.add(Expression.eq("name", name));
                return query.uniqueResult();
            }
        });
        
        if (null == skill) {
            throw new IllegalArgumentException("Unable to find skill " + name);
        }
        return skill;
    }
    
    public void deleteSkill(Skill skill) {
        getHibernateTemplate().delete(skill);
    }
    
    public void updateSkill(Skill skill) {
        getHibernateTemplate().saveOrUpdate(skill);
    }
}