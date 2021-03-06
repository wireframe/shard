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

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.richclient.application.ApplicationWindow;
import org.springframework.richclient.application.config.ApplicationWindowAware;

public class EventTriggeredProgressMonitoredBackgroundCommandExecutor implements ApplicationListener, ApplicationWindowAware, MessageSourceAware, BeanNameAware  {

	private SpecificApplicationEventActionCommandExecutor eventExecutor;
	private ApplicationWindowProgressMonitorActionCommandExecutor progressExecutor;

	public EventTriggeredProgressMonitoredBackgroundCommandExecutor(Class eventClass, Object targetObject, String methodName) {
		MethodInvokingActionCommandExecutor methodExecutor = new MethodInvokingActionCommandExecutor(targetObject, methodName);
		FoxtrotJobActionCommandExecutor foxtrotExecutor = new FoxtrotJobActionCommandExecutor(methodExecutor);
		this.progressExecutor = new ApplicationWindowProgressMonitorActionCommandExecutor(foxtrotExecutor);
		EventDispatcherThreadActionCommandExecutor edtExecutor = new EventDispatcherThreadActionCommandExecutor(progressExecutor);
        BackgroundThreadActionCommandExecutor backgroundThreadExecutor = new BackgroundThreadActionCommandExecutor(edtExecutor);

		this.eventExecutor = new SpecificApplicationEventActionCommandExecutor(eventClass, backgroundThreadExecutor);
	}

	public void onApplicationEvent(ApplicationEvent event) {
		eventExecutor.onApplicationEvent(event);
	}

	public void setApplicationWindow(ApplicationWindow window) {
		progressExecutor.setApplicationWindow(window);
	}
    public void setMessageSource(MessageSource messageSource) {
    	progressExecutor.setMessageSource(messageSource);
    }

    public void setBeanName(String name) {
    	progressExecutor.setBeanName(name);
    }
}
