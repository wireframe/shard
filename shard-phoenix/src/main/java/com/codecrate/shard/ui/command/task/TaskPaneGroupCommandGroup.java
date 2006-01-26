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
