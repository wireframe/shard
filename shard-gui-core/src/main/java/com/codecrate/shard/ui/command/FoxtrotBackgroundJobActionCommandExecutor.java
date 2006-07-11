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
import java.util.Map;

import org.springframework.richclient.command.ParameterizableActionCommandExecutor;

import foxtrot.Job;
import foxtrot.Worker;

public class FoxtrotBackgroundJobActionCommandExecutor implements ParameterizableActionCommandExecutor {
	private final ParameterizableActionCommandExecutor delegate;

	public FoxtrotBackgroundJobActionCommandExecutor(ParameterizableActionCommandExecutor delegate) {
		this.delegate = delegate;
	}

	public void execute() {
        execute(Collections.EMPTY_MAP);
	}

    public void execute(final Map params) {
        Worker.post(new Job() {
            public Object run() {
                delegate.execute(params);
                return null;
            }
        });
    }
}
