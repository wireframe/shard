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

import com.codecrate.shard.feat.Feat;
import com.codecrate.shard.feat.FeatDao;
import com.codecrate.shard.feat.FeatFactory;
import com.codecrate.shard.ui.command.DeleteCommand;
import com.codecrate.shard.ui.command.NewCommand;
import com.codecrate.shard.ui.command.PropertiesCommand;
import com.codecrate.shard.ui.form.AbstractFormFactory;
import com.codecrate.shard.ui.form.FeatForm;
import com.codecrate.shard.ui.form.FormFactory;

public class FeatManagerView extends ObjectManagerView {
    private FeatDao featDao;
    private FeatFactory featFactory; 
    
    public void setFeatDao(FeatDao featDao) {
        this.featDao = featDao;
    }
    
    public void setFeatFactory(FeatFactory featFactory) {
    	this.featFactory = featFactory;
    }

	protected AbstractActionCommandExecutor createDeleteCommand() {
    	String title = getMessage("confirmDeleteFeatDialog.title");
    	String message = getMessage("confirmDeleteFeatDialog.label");
		return new DeleteCommandExecutor(title, message, new DeleteCommand() {
			public void deleteObject(Object object) {
				featDao.deleteFeat((Feat) object);
			}
		});
	}

	protected AbstractActionCommandExecutor createPropertiesCommand() {
		return new PropertiesCommandExecutor(getFormFactory(), new PropertiesCommand() {
			public void updateObject(Object object) {
				featDao.updateFeat((Feat) object);
			}			
		}); 
	}
	
	private FormFactory getFormFactory() {
		return new AbstractFormFactory() {
			public AbstractForm createForm(NestingFormModel formModel) {
				return new FeatForm(formModel);
			}
		};
	}

	protected AbstractActionCommandExecutor createNewCommand() {
		return new NewCommandExcecutor(new NewCommand() {
			public Object createObject() {
				return featFactory.createFeat("New Feat");
			}

			public void saveObject(Object object) {
				featDao.saveFeat((Feat) object);
			}
			
		}, getFormFactory());
	}

	protected String[] getColumnNames() {
		return new String[] {
				"name"
				, "type"
		};
	}

	protected Collection createModelObjects() {
		return featDao.getFeats();
	}

}