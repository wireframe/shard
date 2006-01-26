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
