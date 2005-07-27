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

import org.springframework.binding.form.NestingFormModel;
import org.springframework.richclient.command.support.AbstractActionCommandExecutor;
import org.springframework.richclient.form.AbstractForm;

import com.codecrate.shard.race.Race;
import com.codecrate.shard.race.RaceDao;
import com.codecrate.shard.race.RaceFactory;
import com.codecrate.shard.ui.command.DeleteCommand;
import com.codecrate.shard.ui.command.PropertiesCommand;
import com.codecrate.shard.ui.form.AbstractFormFactory;
import com.codecrate.shard.ui.form.RaceForm;

public class RaceManagerView extends AbstractObjectManagerView {
    private RaceDao raceDao;
    private RaceFactory raceFactory; 
    
    public void setRaceDao(RaceDao raceDao) {
        this.raceDao = raceDao;
    }
    
    public void setRaceFactory(RaceFactory raceFactory) {
    	this.raceFactory = raceFactory;
    }

	protected AbstractActionCommandExecutor createDeleteCommand() {
    	String title = getMessage("confirmDeleteRaceDialog.title");
    	String message = getMessage("confirmDeleteRaceDialog.label");
		return new DeleteCommandExecutor(title, message, new DeleteCommand() {
			public void deleteObject(Object object) {
				raceDao.deleteRace((Race) object);
			}
		});
	}

	protected AbstractActionCommandExecutor createPropertiesCommand() {
		return new AbstractPropertiesCommandExecutor(new AbstractFormFactory() {
			public AbstractForm createForm(NestingFormModel formModel) {
				return new RaceForm(formModel);
			}
		}, new PropertiesCommand() {
			public void updateObject(Object object) {
				raceDao.updateRace((Race) object);
			}			
		}); 
	}

	protected AbstractActionCommandExecutor createNewCommand() {
		return new AbstractNewCommandExcecutor() {

			protected Object createObject() {
				return raceFactory.createRace("New Race");
			}

			protected AbstractForm createForm(NestingFormModel formModel) {
				return new RaceForm(formModel);
			}

			protected void saveObject(Object object) {
				raceDao.saveRace((Race) object);
			}
		};
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