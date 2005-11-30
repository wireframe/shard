package com.codecrate.shard.search;

import java.util.Collection;

import com.codecrate.shard.ShardHibernateTestCaseSupport;
import com.codecrate.shard.skill.DefaultSkill;
import com.codecrate.shard.skill.SkillDao;

public class HibernateObjectSearcherTest extends ShardHibernateTestCaseSupport {

	private HibernateObjectSearcher searcher;
	private SkillDao skillDao;
	private ObjectIndexer indexer;
	
	public void setIndexer(ObjectIndexer indexer) {
		this.indexer = indexer;
	}

	public void setSkillDao(SkillDao skillDao) {
		this.skillDao = skillDao;
	}

	public void setSearcher(HibernateObjectSearcher searcher) {
		this.searcher = searcher;
	}
	
	protected void onSetUpInTransaction() throws Exception {
		super.onSetUpInTransaction();
		
		skillDao.saveSkill(DefaultSkill.APPRAISE);
	}
	
	protected void onTearDownAfterTransaction() throws Exception {
		super.onTearDownAfterTransaction();
		
		//the indexing interceptor is not notified of the transaction rollback.
		//need to clean the index manually
		indexer.clean();
	}
	
	public void testSearchAutomaticallyAppendsWildcard() {
		Collection results = searcher.search(DefaultSkill.class, "a");
		assertEquals(1, results.size());
	}
	
	public void testWildcardQueryDoesNotAddAnotherWildcard() {
		Collection results = searcher.search(DefaultSkill.class, "a*");
		assertEquals(1, results.size());
	}
}
