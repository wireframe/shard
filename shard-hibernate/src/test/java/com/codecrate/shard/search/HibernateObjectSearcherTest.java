package com.codecrate.shard.search;

import java.util.Collection;

import com.codecrate.shard.ShardHibernateTestCaseSupport;
import com.codecrate.shard.skill.DefaultSkill;
import com.codecrate.shard.skill.Skill;
import com.codecrate.shard.skill.SkillDao;
import com.codecrate.shard.skill.SkillFactory;

public class HibernateObjectSearcherTest extends ShardHibernateTestCaseSupport {

	private HibernateObjectSearcher searcher;
    private SkillDao skillDao;
    private SkillFactory skillFactory;
	private ObjectIndexer indexer;

	public void setIndexer(ObjectIndexer indexer) {
		this.indexer = indexer;
	}

    public void setSkillDao(SkillDao skillDao) {
        this.skillDao = skillDao;
    }
    public void setSkillFactory(SkillFactory skillFactory) {
        this.skillFactory = skillFactory;
    }

	public void setSearcher(HibernateObjectSearcher searcher) {
		this.searcher = searcher;
	}

	protected void onSetUpInTransaction() throws Exception {
		super.onSetUpInTransaction();

        Skill skill = skillFactory.createSkill("appraise", null, false, false, null);
		skillDao.saveSkill(skill);
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
