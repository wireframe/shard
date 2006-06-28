package com.codecrate.shard.ui.command;

import org.springframework.richclient.command.ActionCommandExecutor;
import org.springframework.richclient.command.support.ApplicationWindowAwareCommand;
import org.springframework.richclient.progress.BusyIndicator;
import org.springframework.richclient.progress.ProgressMonitor;
import org.springframework.richclient.progress.StatusBar;

public class ApplicationWindowProgressMonitorActionCommandExecutor extends ApplicationWindowAwareCommand implements ActionCommandExecutor {
	private final ActionCommandExecutor delegate;

	public ApplicationWindowProgressMonitorActionCommandExecutor(ActionCommandExecutor delegate) {
		this.delegate = delegate;
	}

	protected void doExecuteCommand() {
        BusyIndicator.showAt(getApplicationWindow().getControl());
        getProgressMonitor().taskStarted(getText(), StatusBar.UNKNOWN);

        delegate.execute();

        BusyIndicator.clearAt(getApplicationWindow().getControl());
        getProgressMonitor().done();
	}

    private ProgressMonitor getProgressMonitor() {
    	return getApplicationWindow().getStatusBar().getProgressMonitor();
    }
}
