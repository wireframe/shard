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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.richclient.command.CommandGroup;
import org.springframework.richclient.command.CommandRegistry;

import com.l2fprod.common.swing.JTaskPane;

public class TaskPaneCommandGroup extends CommandGroup {
	private List groups = new ArrayList();

	public TaskPaneCommandGroup(String groupId, CommandRegistry registry) {
		super(groupId, registry);
	}
    
	public JTaskPane createTaskPane() {
		JTaskPane taskPane = new JTaskPane();
		for (Iterator it = groups.iterator(); it.hasNext();) {
			TaskPaneGroupCommandGroup member = (TaskPaneGroupCommandGroup) it.next();
			taskPane.add(member.createTaskPaneGroup());
		}
		return taskPane;
	}

	public void addTaskPaneGroup(TaskPaneGroupCommandGroup childGroup) {
		super.add(childGroup);
		groups.add(childGroup);
	}

}
