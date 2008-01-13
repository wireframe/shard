package com.codecrate.shard.magic;

import java.util.Collection;

import org.apache.lucene.analysis.Analyzer;

import com.codecrate.shard.hibernate.BasicHibernateObjectDaoSupport;

public class HibernateSpellDao extends BasicHibernateObjectDaoSupport implements SpellDao {

	private final Analyzer analyzer;

	public HibernateSpellDao(Analyzer analyzer) {
		this.analyzer = analyzer;
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
		return Spell.class;
	}

	@Override
	protected String[] getSearchableFieldNames() {
		return new String[] {"name", "summary"};
	}

	@Override
	public void deleteSpell(Spell spell) {
		deleteObject(spell);
	}

	@Override
	public Collection<Spell> getSpells() {
		return getHibernateTemplate().loadAll(Spell.class);
	}

	@Override
	public Spell saveSpell(Spell spell) {
		return (Spell) saveObject(spell);
	}

	@Override
	public Collection<Spell> searchSpells(String query) {
		return searchObjects(query);
	}

	@Override
	public void updateSpell(Spell spell) {
		updateObject(spell);
	}
}
