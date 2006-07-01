package com.codecrate.shard.ui.command;

import org.springframework.richclient.command.ActionCommandExecutor;
import org.springframework.richclient.command.ParameterizableActionCommandExecutor;

public class DefaultActionCommandExecutorFactory {

	public ActionCommandExecutor createDefaultParameterizableActionCommandExecutor(ParameterizableActionCommandExecutor executor) {
		return new EventDispatcherThreadActionCommandExecutor(
				new ApplicationWindowProgressMonitorActionCommandExecutor(
						new FoxtrotBackgroundJobActionCommandExecutor(executor)));
	}
}
