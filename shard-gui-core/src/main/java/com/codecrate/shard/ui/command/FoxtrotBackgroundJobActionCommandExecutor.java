package com.codecrate.shard.ui.command;

import org.springframework.richclient.command.ActionCommandExecutor;

import foxtrot.Job;
import foxtrot.Worker;

public class FoxtrotBackgroundJobActionCommandExecutor implements ActionCommandExecutor {
	private final ActionCommandExecutor delegate;

	public FoxtrotBackgroundJobActionCommandExecutor(ActionCommandExecutor delegate) {
		this.delegate = delegate;
	}

	public void execute() {
		Worker.post(new Job() {
			public Object run() {
				delegate.execute();
				return null;
			}
		});
	}
}
