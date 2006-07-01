package com.codecrate.shard.ui.command;

import java.util.Collections;
import java.util.Map;

import org.springframework.richclient.application.ApplicationWindow;
import org.springframework.richclient.application.config.ApplicationWindowAware;
import org.springframework.richclient.command.ParameterizableActionCommandExecutor;
import org.springframework.richclient.progress.BusyIndicator;
import org.springframework.richclient.progress.ProgressMonitor;
import org.springframework.richclient.progress.StatusBar;

public class ApplicationWindowProgressMonitorActionCommandExecutor implements ParameterizableActionCommandExecutor, ApplicationWindowAware {
	private final ParameterizableActionCommandExecutor delegate;
    private ApplicationWindow window;

	public ApplicationWindowProgressMonitorActionCommandExecutor(ParameterizableActionCommandExecutor delegate) {
		this.delegate = delegate;
	}

    private ProgressMonitor getProgressMonitor() {
    	return window.getStatusBar().getProgressMonitor();
    }

    public void execute() {
        execute(Collections.EMPTY_MAP);
    }

    public void setApplicationWindow(ApplicationWindow window) {
        this.window = window;
    }

    public void execute(Map params) {
        BusyIndicator.showAt(window.getControl());
        getProgressMonitor().taskStarted("TEXT GOES HERE!", StatusBar.UNKNOWN);

        try {
            delegate.execute(params);
        } finally {
            BusyIndicator.clearAt(window.getControl());
            getProgressMonitor().done();
        }
    }
}
