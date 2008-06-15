package com.codecrate.shard.skill;

import java.util.Collection;

import org.apache.lucene.analysis.Analyzer;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.codecrate.shard.hibernate.BasicHibernateObjectDaoSupport;

public class HibernateSkillDao extends BasicHibernateObjectDaoSupport implements SkillDao {
	
	private final Analyzer analyzer;

	public HibernateSkillDao(Analyzer analyzer) {
		this.analyzer = analyzer;
	}
	
	public void deleteSkill(Skill skill) {
        getHibernateTemplate().delete(skill);
	}

	public Skill getSkill(final String name) {
		return (Skill) getObjectByKey(name);
	}

	public Collection<Skill> getSkills() {
    	return getHibernateTemplate().loadAll(Skill.class);
	}

	public Collection<Skill> getUntrainedSkills() {
        return (Collection<Skill>) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                Criteria query = session.createCriteria(Skill.class);
                query.add(Expression.eq("usableUntrained", Boolean.TRUE));
                return query.list();
            }
        });
	}

	public Skill saveSkill(Skill skill) {
		return (Skill) saveObject(skill);
	}

	public Collection<Skill> searchSkills(final String query) {
		return searchObjects(query);
	}
	
	public void updateSkill(Skill skill) {
		updateObject(skill);
	}

	protected Analyzer getAnalyzer() {
		return analyzer;
	}

	protected String getKeyField() {
		return "name";
	}

	protected Class getManagedClass() {
		return Skill.class;
	}

	protected String[] getSearchableFieldNames() {
		return new String[] {"name"};
	}
}
