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

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.codecrate.shard.kit.CharacterClass;

/**
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class HibernateSkillDao implements SkillDao {
    private static final Log LOG = LogFactory.getLog(HibernateSkillDao.class);
    
    private final Session session;
    
    public HibernateSkillDao(Session session) {
        this.session = session;
    }
    
    public Collection getSkills() {
        try {
            Query query = session.createQuery("from HibernateSkill");
            return query.list();
        } catch (HibernateException e) {
            LOG.error("Unable to lookup skills.", e);
        }
        return null;
    }

    /* (non-Javadoc)
     * @see com.codecrate.shard.skill.SkillDao#getClassSkills(com.codecrate.shard.kit.CharacterClass)
     */
    public Collection getClassSkills(CharacterClass kit) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see com.codecrate.shard.skill.SkillDao#getSynergeticSkills(com.codecrate.shard.skill.Skill)
     */
    public Collection getSynergeticSkills(Skill skill) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see com.codecrate.shard.skill.SkillDao#getUntrainedSkills()
     */
    public Collection getUntrainedSkills() {
        // TODO Auto-generated method stub
        return null;
    }
}
