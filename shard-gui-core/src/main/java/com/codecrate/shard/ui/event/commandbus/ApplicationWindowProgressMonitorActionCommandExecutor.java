/*
 * Copyright 2002-2004 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.codecrate.shard.ui.event.commandbus;

import java.util.Collections;
import java.util.HashMap;
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
            Map newParams = new HashMap();
            newParams.putAll(params);
            newParams.put("progressMonitor", getProgressMonitor());
            delegate.execute(newParams);
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
