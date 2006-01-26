package com.codecrate.shard.ui.command.task;

import org.springframework.richclient.command.ActionCommand;
import org.springframework.richclient.command.CommandGroup;
import org.springframework.richclient.command.CommandGroupFactoryBean;
import org.springframework.richclient.command.CommandRegistry;

public class TaskPaneGroupFactoryBean extends CommandGroupFactoryBean {

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
		TaskPaneGroupCommandGroup group = new TaskPaneGroupCommandGroup(groupId, registry);
		for (int i = 0; i < encodedMembers.length; i++) {
			String actionName = (String) encodedMembers[i];
			ActionCommand childAction = registry.getActionCommand(actionName);
			group.addActionCommand(childAction);
		}
		return group;
	}
	
}
