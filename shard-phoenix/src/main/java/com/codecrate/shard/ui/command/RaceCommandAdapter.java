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

import com.codecrate.shard.race.Race;
import com.codecrate.shard.race.RaceDao;
import com.codecrate.shard.race.RaceFactory;
import com.codecrate.shard.source.SourceDao;

public class RaceCommandAdapter implements ObjectManagerCommandAdapter {

	private final RaceDao raceDao;
	private final RaceFactory raceFactory;

	private String deleteMessagePropertyName;
    private final SourceDao sourceDao;

	public RaceCommandAdapter(RaceDao raceDao, RaceFactory raceFactory, SourceDao sourceDao) {
		this.raceDao = raceDao;
		this.raceFactory = raceFactory;
        this.sourceDao = sourceDao;
	}

	public String[] getPropertyNames() {
		return new String[] {
				"name"
                , "source"
		};
	}

	public Collection getObjects() {
		return raceDao.getRaces();
	}

	public Object createObject() {
		return raceFactory.createRace("New Race", null, sourceDao.getCustomSource());
	}

	public void saveObject(Object object) {
		raceDao.saveRace((Race) object);
	}

	public void updateObject(Object object) {
		raceDao.updateRace((Race) object);
	}

	public void deleteObject(Object object) {
		raceDao.deleteRace((Race) object);
	}

	public String getDeleteMessagePropertyName() {
		return deleteMessagePropertyName;
	}

	public void setDeleteMessagePropertyName(String deleteMessagePropertyName) {
		this.deleteMessagePropertyName = deleteMessagePropertyName;
	}

	public Collection searchObjects(String query) {
		return raceDao.searchRaces(query);
	}
}
