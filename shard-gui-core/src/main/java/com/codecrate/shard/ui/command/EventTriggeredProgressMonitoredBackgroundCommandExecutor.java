package com.codecrate.shard.ui.command;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.richclient.application.ApplicationWindow;
import org.springframework.richclient.application.config.ApplicationWindowAware;

public class EventTriggeredProgressMonitoredBackgroundCommandExecutor implements ApplicationListener, ApplicationWindowAware, MessageSourceAware, BeanNameAware  {

	private SpecificApplicationEventActionCommandExecutor eventExecutor;
	private ApplicationWindowProgressMonitorActionCommandExecutor progressExecutor;

	public EventTriggeredProgressMonitoredBackgroundCommandExecutor(Class eventClass, ActionCommandExecutor delegate) {
		this.progressExecutor = new ApplicationWindowProgressMonitorActionCommandExecutor(new EventDispatcherThreadActionCommandExecutor(delegate));
		this.eventExecutor = new SpecificApplicationEventActionCommandExecutor(eventClass, new FoxtrotBackgroundJobActionCommandExecutor(progressExecutor));
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
