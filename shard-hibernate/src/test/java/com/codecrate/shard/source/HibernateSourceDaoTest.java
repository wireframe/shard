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
package com.codecrate.shard.source;

import com.codecrate.shard.ShardHibernateTestCaseSupport;

/**
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class HibernateSourceDaoTest extends ShardHibernateTestCaseSupport {
	private SourceDao sourceDao;
	private SourceFactory sourceFactory;

	public void setSourceDao(SourceDao dao) {
		this.sourceDao = dao;
	}

    public void setSourceFactory(SourceFactory factory) {
        this.sourceFactory = factory;
    }

	protected void onSetUpInTransaction() throws Exception {
		super.onSetUpInTransaction();

        Source fireball = sourceFactory.createSource("Custom Source", "CS", "http://blah.com");
		sourceDao.saveSource(fireball);
	}

    public void testGetSourceName() throws Exception {
        Source source = sourceDao.getSource("Custom Source");
        assertNotNull(source);
    }

    public void testGetSourceByUnknownNameReturnsNull() throws Exception {
        Source source = sourceDao.getSource("invalid source");
        assertNull(source);
    }

}
