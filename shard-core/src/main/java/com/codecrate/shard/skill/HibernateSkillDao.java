package com.codecrate.shard.skill;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class HibernateSkillDao extends HibernateDaoSupport implements SkillDao {
	private static final Log LOG = LogFactory.getLog(HibernateSkillDao.class);
	
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
        Object object = getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                Criteria query = session.createCriteria(Skill.class);
                query.add(Expression.eq("name", name));
                return query.uniqueResult();
            }
        });

        if (null == object) {
            throw new IllegalArgumentException("Unable to find " + Skill.class.getName() + " with " + "name" + " = " + name);
        }
        return (Skill) object;
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
		Serializable id = getHibernateTemplate().save(skill);
		return (Skill) getHibernateTemplate().load(Skill.class, id);
	}

	@Override
	public Collection<Skill> searchSkills(final String query) {
		final MultiFieldQueryParser parser = new MultiFieldQueryParser( new String[]{"name"}, analyzer);

		return (Collection<Skill>) getHibernateTemplate().execute(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
				FullTextSession fullTextSession = Search.createFullTextSession(getSession());
				org.apache.lucene.search.Query lucene;
				try {
					lucene = parser.parse(query);
				} catch (ParseException e) {
					LOG.error("Error parsing query: " + query, e);
					return Collections.EMPTY_SET;
				}
				return fullTextSession.createFullTextQuery(lucene, Skill.class).list();
			}
		});
	}
	
	public void indexAll() {
		getHibernateTemplate().execute(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				FullTextSession fullTextSession = Search.createFullTextSession(getSession());
				for (Skill skill : getSkills()) {
					fullTextSession.index(skill);
				}
				return null;
			}
		});
	}

	@Override
	public void updateSkill(Skill skill) {
		getHibernateTemplate().saveOrUpdate(skill);
	}
}
