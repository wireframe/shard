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

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

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
                Query query = session.createQuery("from HibernateSkill");
                return query.list();
            }
        });
    }

    public Collection getUntrainedSkills() {
        return (List) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                Query query = session.createQuery("from HibernateSkill value where value.usableUntrained = false");
                return query.list();
            }
        });
    }
    
    public Skill createSkill(String name, boolean usableUntrained, Ability ability, boolean armorPenalty) {
        HibernateSkill skill = new HibernateSkill(name, usableUntrained, ability, armorPenalty);
        String id = (String) getHibernateTemplate().save(skill);
        return (Skill) getHibernateTemplate().load(HibernateSkill.class, id);
    }
    
    public void deleteSkill(Skill skill) {
        getHibernateTemplate().delete(skill);
    }
    
    public void updateSkill(Skill skill) {
        getHibernateTemplate().saveOrUpdate(skill);
    }
}