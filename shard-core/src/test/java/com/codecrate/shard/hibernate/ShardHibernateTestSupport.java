package com.codecrate.shard.hibernate;

import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

public class ShardHibernateTestSupport extends AbstractTransactionalDataSourceSpringContextTests {

	public ShardHibernateTestSupport() {
		super();
		setDefaultRollback(true);
	}

	protected final String[] getConfigLocations() {
		return new String[] {
				"/shard-hibernate-context.xml"
				, "/test-datasource.xml"
		}; 
	}
}
