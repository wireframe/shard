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

import com.codecrate.shard.BasicHibernateDao;
import com.codecrate.shard.ability.Ability;
import com.codecrate.shard.search.HibernateObjectSearcher;
import com.codecrate.shard.source.Source;

/**
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class HibernateSkillDao extends BasicHibernateDao implements SkillDao, SkillFactory {

	public HibernateSkillDao(HibernateObjectSearcher searcher) {
		super(searcher, DefaultSkill.class, "name");
	}

    public Collection getSkills() {
    	return getAllObjects();
    }

    public Collection getUntrainedSkills() {
        return (List) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                Criteria query = session.createCriteria(DefaultSkill.class);
                query.add(Expression.eq("usableUntrained", Boolean.TRUE));
                return query.list();
            }
        });
    }

    public Skill createSkill(String name, Ability ability, boolean usableUntrained, boolean penalizedWithArmor, Source source) {
        DefaultSkill skill = new DefaultSkill(name, usableUntrained, ability, penalizedWithArmor, source);
        return skill;
    }

    public Skill saveSkill(Skill skill) {
    	return (Skill) saveObject(skill);
    }

    public Skill getSkill(final String name) {
    	return (Skill) getObjectByKey(name);
    }

    public void deleteSkill(Skill skill) {
        deleteObject(skill);
    }

    public void updateSkill(Skill skill) {
        updateObject(skill);
    }

    public Collection searchSkills(String query) {
    	return searchObjects(query);
    }
}