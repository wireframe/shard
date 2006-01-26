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
