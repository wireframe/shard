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
package com.codecrate.shard.ui.command.task;

import org.springframework.richclient.command.CommandGroup;
import org.springframework.richclient.command.CommandGroupFactoryBean;
import org.springframework.richclient.command.CommandRegistry;

public class TaskPaneFactoryBean extends CommandGroupFactoryBean {

    private String groupId;
	private Object[] encodedMembers;
	private CommandRegistry registry;

	public void setBeanName(String beanName) {
        this.groupId = beanName;
    }
	
	public void setMembers(Object[] members) {
		this.encodedMembers = members;
	}

	public void setCommandRegistry(CommandRegistry registry) {
        this.registry = registry;
    }

	protected CommandGroup createCommandGroup() {
		TaskPaneCommandGroup group = new TaskPaneCommandGroup(groupId, registry);

		for (int i = 0; i < encodedMembers.length; i++) {
			TaskPaneGroupCommandGroup childGroup = (TaskPaneGroupCommandGroup) encodedMembers[i];
			group.addTaskPaneGroup(childGroup);
		}

		return group;
	}
}
