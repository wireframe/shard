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
package com.codecrate.shard.ui.command;

import java.util.Collections;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.richclient.command.ActionCommandExecutor;
import org.springframework.richclient.command.ParameterizableActionCommandExecutor;

import com.codecrate.shard.ui.event.AbstractSpecificApplicationEventListener;

public class SpecificApplicationEventActionCommandExecutor extends AbstractSpecificApplicationEventListener implements ActionCommandExecutor, ApplicationListener {
	private static final Log LOG = LogFactory.getLog(SpecificApplicationEventActionCommandExecutor.class);

	private final ParameterizableActionCommandExecutor delegate;

	public SpecificApplicationEventActionCommandExecutor(Class targetClass, ParameterizableActionCommandExecutor delegate) {
        super(targetClass);
		this.delegate = delegate;
	}

    protected void onSpecificApplicationEvent(ApplicationEvent event) {
        LOG.info("Executing action specific action command due to application event " + event);
        delegate.execute(Collections.singletonMap("event", event));
    }

    public void execute() {
        LOG.info("Cannot execute specific action command executor without application event");
    }
}
