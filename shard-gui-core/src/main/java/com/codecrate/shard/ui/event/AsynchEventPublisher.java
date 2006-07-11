package com.codecrate.shard.ui.event;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;

public class AsynchEventPublisher implements ApplicationEventPublisher {
	private static final Log LOG = LogFactory.getLog(AsynchEventPublisher.class);
	
	private final ApplicationEventPublisher delegate;
	public AsynchEventPublisher(ApplicationEventPublisher delegate) {
		this.delegate = delegate;

	}
	public void publishEvent(final ApplicationEvent event) {
		LOG.debug("Publishing event on background thread: " + event);
		new Thread() {
			public void run() {
				delegate.publishEvent(event);
			}
		}.run();
	}

}
