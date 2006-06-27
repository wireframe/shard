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
package com.codecrate.shard.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * helper class for listening to specific application events.
 *
 *
 */
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
