package com.codecrate.shard.ui.command;

import java.util.Collections;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.richclient.application.ApplicationWindow;
import org.springframework.richclient.application.config.ApplicationWindowAware;
import org.springframework.richclient.command.ParameterizableActionCommandExecutor;
import org.springframework.richclient.progress.BusyIndicator;
import org.springframework.richclient.progress.ProgressMonitor;
import org.springframework.richclient.progress.StatusBar;

public class ApplicationWindowProgressMonitorActionCommandExecutor implements ParameterizableActionCommandExecutor, ApplicationWindowAware, MessageSourceAware, BeanNameAware {
	private final ParameterizableActionCommandExecutor delegate;
    private ApplicationWindow window;
    private MessageSource messageSource;
    private String messageKey;

	public ApplicationWindowProgressMonitorActionCommandExecutor(ParameterizableActionCommandExecutor delegate) {
        this.delegate = delegate;
	}

    private ProgressMonitor getProgressMonitor() {
    	return window.getStatusBar().getProgressMonitor();
    }

    public void execute() {
        execute(Collections.EMPTY_MAP);
    }

    public void execute(Map params) {
        String description = messageSource.getMessage(messageKey, new Object[] {}, messageKey, Locale.getDefault());
        BusyIndicator.showAt(window.getControl());
        getProgressMonitor().taskStarted(description, StatusBar.UNKNOWN);

        try {
            delegate.execute(params);
        } finally {
            BusyIndicator.clearAt(window.getControl());
            getProgressMonitor().done();
        }
    }

    public void setApplicationWindow(ApplicationWindow window) {
        this.window = window;
    }

    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public void setBeanName(String name) {
        this.messageKey = name;
    }
}
