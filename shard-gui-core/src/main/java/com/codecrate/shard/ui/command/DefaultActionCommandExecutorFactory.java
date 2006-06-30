package com.codecrate.shard.ui.command;

import org.springframework.richclient.command.ActionCommandExecutor;

public class DefaultActionCommandExecutorFactory {

	public ActionCommandExecutor createDefaultActionCommandExecutor(Class eventType, ActionCommandExecutor executor) {
		return new SpecificApplicationEventActionCommandExecutor(eventType,
				new EventDispatcherThreadActionCommandExecutor(
						new ApplicationWindowProgressMonitorActionCommandExecutor(
								new FoxtrotBackgroundJobActionCommandExecutor(executor))));
	}
}
