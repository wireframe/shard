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
package com.codecrate.shard.ui.view;

import java.util.Collection;

import com.codecrate.shard.race.RaceDao;
import com.codecrate.shard.ui.command.RaceCommandAdapter;
import com.codecrate.shard.ui.form.RaceFormFactory;

public class RaceManagerView extends AbstractObjectManagerView {
    private RaceDao raceDao;
    
    public RaceManagerView(RaceCommandAdapter commandAdapter, RaceFormFactory formFactory) {
    	super(commandAdapter, commandAdapter, commandAdapter, formFactory);
    }

	protected String[] getColumnNames() {
		return new String[] {
				"name"
		};                 
	}

	protected Collection createModelObjects() {
		return raceDao.getRaces();
	}
}