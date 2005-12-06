/*
 * Copyright 2002-2004 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.codecrate.shard.ui.command;

import java.util.Collection;

import com.codecrate.shard.feat.Feat;
import com.codecrate.shard.feat.FeatDao;
import com.codecrate.shard.feat.FeatFactory;
import com.codecrate.shard.transfer.ObjectImporter;

public class FeatCommandAdapter extends AbstractObjectManagerCommandAdapter
implements ObjectManagerCommandAdapter {

	private final FeatDao featDao;
	private final FeatFactory featFactory;

	private String deleteMessagePropertyName;

	public FeatCommandAdapter(FeatDao featDao, FeatFactory featFactory, ObjectImporter importer) {
        super(importer);
		this.featDao = featDao;
		this.featFactory = featFactory;
	}

	public String[] getColumnNames() {
		return new String[] {
				"name"
				, "type"
		};
	}

	public Collection getObjects() {
		return featDao.getFeats();
	}

	public Object createObject() {
		return featFactory.createFeat("New Feat", "Summary");
	}

	public void saveObject(Object object) {
		featDao.saveFeat((Feat) object);
	}

	public void updateObject(Object object) {
		featDao.updateFeat((Feat) object);
	}

	public void deleteObject(Object object) {
		featDao.deleteFeat((Feat) object);
	}

	public String getDeleteMessagePropertyName() {
		return deleteMessagePropertyName;
	}

	public void setDeleteMessagePropertyName(String deleteMessagePropertyName) {
		this.deleteMessagePropertyName = deleteMessagePropertyName;
	}

	public Collection searchObjects(String query) {
		return featDao.searchFeats(query);
	}
}
