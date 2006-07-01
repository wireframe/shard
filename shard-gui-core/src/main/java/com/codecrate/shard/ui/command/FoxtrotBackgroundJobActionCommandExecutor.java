package com.codecrate.shard.ui.command;

import java.util.Collections;
import java.util.Map;

import org.springframework.richclient.command.ParameterizableActionCommandExecutor;

import foxtrot.Job;
import foxtrot.Worker;

public class FoxtrotBackgroundJobActionCommandExecutor implements ParameterizableActionCommandExecutor {
	private final ParameterizableActionCommandExecutor delegate;

	public FoxtrotBackgroundJobActionCommandExecutor(ParameterizableActionCommandExecutor delegate) {
		this.delegate = delegate;
	}

	public void execute() {
        execute(Collections.EMPTY_MAP);
	}

    public void execute(final Map params) {
        Worker.post(new Job() {
            public Object run() {
                delegate.execute(params);
                return null;
            }
        });
    }
}
