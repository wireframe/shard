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
	
	@Override
	public void deleteSkill(Skill skill) {
        getHibernateTemplate().delete(skill);
	}

	@Override
	public Skill getSkill(final String name) {
		return (Skill) getObjectByKey(name);
	}

	@Override
	public Collection<Skill> getSkills() {
    	return getHibernateTemplate().loadAll(Skill.class);
	}

	@Override
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

	@Override
	public Skill saveSkill(Skill skill) {
		return (Skill) saveObject(skill);
	}

	@Override
	public Collection<Skill> searchSkills(final String query) {
		return searchObjects(query);
	}
	
	@Override
	public void updateSkill(Skill skill) {
		updateObject(skill);
	}

	@Override
	protected Analyzer getAnalyzer() {
		return analyzer;
	}

	@Override
	protected String getKeyField() {
		return "name";
	}

	@Override
	protected Class getManagedClass() {
		return Skill.class;
	}

	@Override
	protected String[] getSearchableFieldNames() {
		return new String[] {"name"};
	}
}
