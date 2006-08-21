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
package com.codecrate.shard.ui.transfer.progress;

import com.codecrate.shard.transfer.progress.ProgressMonitor;

public class SpringRichImportProgressAdapter implements ProgressMonitor {

	private final org.springframework.richclient.progress.ProgressMonitor delegate;
	private int unitsWorked = 0;

	public SpringRichImportProgressAdapter(org.springframework.richclient.progress.ProgressMonitor delegate) {
		this.delegate = delegate;
	}

	public void startTask(String description, int totalUnitsOfWork) {
		unitsWorked = 0;
		delegate.taskStarted(description, totalUnitsOfWork);
	}

	public void completeUnitOfWork() {
		unitsWorked++;
		delegate.worked(unitsWorked);
	}

	public void finish() {
		delegate.done();
	}

	public void cancel() {
		delegate.setCanceled(true);
	}

	public boolean isCanceled() {
		return delegate.isCanceled();
	}
}
