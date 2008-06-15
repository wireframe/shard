package com.codecrate.shard.magic;

import java.util.Collection;

import org.apache.lucene.analysis.Analyzer;

import com.codecrate.shard.hibernate.BasicHibernateObjectDaoSupport;

public class HibernateSpellDao extends BasicHibernateObjectDaoSupport implements SpellDao {

	private final Analyzer analyzer;

	public HibernateSpellDao(Analyzer analyzer) {
		this.analyzer = analyzer;
	}

	
	protected Analyzer getAnalyzer() {
		return analyzer;
	}

	
	protected String getKeyField() {
		return "name";
	}

	
	protected Class getManagedClass() {
		return Spell.class;
	}

	
	protected String[] getSearchableFieldNames() {
		return new String[] {"name", "summary"};
	}

	
	public void deleteSpell(Spell spell) {
		deleteObject(spell);
	}

	
	public Collection<Spell> getSpells() {
		return getHibernateTemplate().loadAll(Spell.class);
	}

	
	public Spell saveSpell(Spell spell) {
		return (Spell) saveObject(spell);
	}

	
	public Collection<Spell> searchSpells(String query) {
		return searchObjects(query);
	}

	
	public void updateSpell(Spell spell) {
		updateObject(spell);
	}
}
