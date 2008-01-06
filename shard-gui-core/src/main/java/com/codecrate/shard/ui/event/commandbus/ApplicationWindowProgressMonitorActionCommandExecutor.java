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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.richclient.application.ApplicationWindow;
import org.springframework.richclient.application.config.ApplicationWindowAware;
import org.springframework.richclient.command.ParameterizableActionCommandExecutor;
import org.springframework.richclient.progress.NullProgressMonitor;
import org.springframework.richclient.progress.ProgressMonitor;
import org.springframework.richclient.progress.StatusBar;
import org.springframework.richclient.progress.StatusBarCommandGroup;

public class ApplicationWindowProgressMonitorActionCommandExecutor implements ParameterizableActionCommandExecutor, ApplicationWindowAware, MessageSourceAware, BeanNameAware {
	private static final Log LOG = LogFactory.getLog(ApplicationWindowProgressMonitorActionCommandExecutor.class);
	private final ParameterizableActionCommandExecutor delegate;
    private MessageSource messageSource;
    private String messageKey;
	private ProgressMonitor progressMonitor = new NullProgressMonitor();

	public ApplicationWindowProgressMonitorActionCommandExecutor(ParameterizableActionCommandExecutor delegate) {
        this.delegate = delegate;
	}

    public void execute() {
        execute(Collections.EMPTY_MAP);
    }

    public void execute(Map params) {
        String description = messageSource.getMessage(messageKey, new Object[] {}, messageKey, Locale.getDefault());
        progressMonitor.taskStarted(description, StatusBar.UNKNOWN);

        try {
            Map newParams = new HashMap();
            newParams.putAll(params);
            newParams.put("progressMonitor", progressMonitor);
            delegate.execute(newParams);
        } finally {
            progressMonitor.done();
        }
    }

	public void setApplicationWindow(ApplicationWindow window) {
		if (window.getStatusBar() == null) {
			LOG.warn("Status bar is null for some reason.");
		} else {
			this.progressMonitor = window.getStatusBar().getProgressMonitor();
		}
    }

    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public void setBeanName(String name) {
        this.messageKey = name;
    }
}
