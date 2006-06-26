package com.codecrate.shard.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

public abstract class SpecificApplicationEventListener implements ApplicationListener {

	private final Class class1;

	public SpecificApplicationEventListener(Class class1) {
		this.class1 = class1;
	}

	protected abstract void onSpecificApplicationEvent(ApplicationEvent event);

	public void onApplicationEvent(ApplicationEvent event) {
		if (event.getClass().isAssignableFrom(class1)) {
			onSpecificApplicationEvent(event);
		}
	}

}
