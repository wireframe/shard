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

import com.codecrate.shard.ability.AbilityDao;
import com.codecrate.shard.skill.Skill;
import com.codecrate.shard.skill.SkillDao;
import com.codecrate.shard.skill.SkillFactory;
import com.codecrate.shard.ui.command.DeleteCommand;
import com.codecrate.shard.ui.command.NewCommand;
import com.codecrate.shard.ui.command.PropertiesCommand;
import com.codecrate.shard.ui.form.AbstractFormFactory;
import com.codecrate.shard.ui.form.SkillForm;

public class SkillManagerView extends AbstractObjectManagerView {
    private SkillDao skillDao;
    private SkillFactory skillFactory;
    private AbilityDao abilityDao;
    
    public void setAbilityDao(AbilityDao abilityDao) {
        this.abilityDao = abilityDao;
    }
    
    public void setSkillDao(SkillDao skillDao) {
        this.skillDao = skillDao;
    }
    
    public void setSkillFactory(SkillFactory skillFactory) {
    	this.skillFactory = skillFactory;
    }

	protected AbstractActionCommandExecutor createDeleteCommand() {
    	String title = getMessage("confirmDeleteSkillDialog.title");
    	String message = getMessage("confirmDeleteSkillDialog.label");
		return new DeleteCommandExecutor(title, message, new DeleteCommand() {
			public void deleteObject(Object object) {
				skillDao.deleteSkill((Skill) object);
			}
		});
	}

	protected AbstractActionCommandExecutor createPropertiesCommand() {
		return new PropertiesCommandExecutor(getFormFactory(), new PropertiesCommand() {
			public void updateObject(Object object) {
				skillDao.updateSkill((Skill) object);
			}			
		}); 
	}

	private AbstractFormFactory getFormFactory() {
		return new AbstractFormFactory() {
			public AbstractForm createForm(NestingFormModel formModel) {
				return new SkillForm(formModel, abilityDao);
			}
		};
	}

	protected AbstractActionCommandExecutor createNewCommand() {
		return new NewCommandExcecutor(new NewCommand() {
			public Object createObject() {
				return skillFactory.createSkill("New Skill");
			}

			public void saveObject(Object object) {
				skillDao.saveSkill((Skill) object);
			}
		}, getFormFactory());
	}

	protected String[] getColumnNames() {
		return new String[] {
				"name"
				, "ability"
		};
	}

	protected Collection createModelObjects() {
		return skillDao.getSkills();
	}
    
}