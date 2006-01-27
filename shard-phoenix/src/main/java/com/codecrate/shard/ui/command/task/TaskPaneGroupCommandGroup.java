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

import org.springframework.richclient.command.ActionCommand;
import org.springframework.richclient.command.CommandGroup;
import org.springframework.richclient.command.CommandRegistry;
import org.springframework.richclient.command.SwingActionAdapter;

import com.l2fprod.common.swing.JTaskPaneGroup;

public class TaskPaneGroupCommandGroup extends CommandGroup {
	private List actions = new ArrayList();

	public TaskPaneGroupCommandGroup(String groupId, CommandRegistry registry) {
		super(groupId, registry);
	}

	public JTaskPaneGroup createTaskPaneGroup() {
		JTaskPaneGroup commonTasks = new JTaskPaneGroup();
        commonTasks.setExpanded(true);
        commonTasks.setTitle(getText());

		for (Iterator members = actions.iterator(); members.hasNext();) {
			ActionCommand member = (ActionCommand) members.next();
			
	        SwingActionAdapter adapter = new SwingActionAdapter(member);
	        commonTasks.add(adapter);
		}
		return commonTasks;
	}
	
	public void addActionCommand(ActionCommand command) {
		super.add(command);
		actions.add(command);
	}
}
