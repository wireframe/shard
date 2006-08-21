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
package com.codecrate.shard.transfer.progress;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class NullProgressMonitor implements ProgressMonitor {
	private static final Log LOG = LogFactory.getLog(NullProgressMonitor.class);

	private String description;
	private int totalUnitsOfWork;
	private int completedUnitsOfWork;
	private boolean canceled = false;

	public void startTask(String description, int totalUnitsOfWork) {
		this.description = description;
		this.totalUnitsOfWork = totalUnitsOfWork;
		this.completedUnitsOfWork = 0;

		LOG.info("Starting task: " + description);
	}

	public void completeUnitOfWork() {
		completedUnitsOfWork++;
		if (completedUnitsOfWork > totalUnitsOfWork) {
			completedUnitsOfWork = totalUnitsOfWork;
		}

		LOG.info(description + " is " + getPercentComplete() + " complete");
	}

	public void finish() {
		LOG.info(description + " is finished.");
	}

	private String getPercentComplete() {
		double x = (double)completedUnitsOfWork / (double)totalUnitsOfWork;
		return Math.round(x * 100) + "%";
	}

	public void cancel() {
		this.canceled = true;
	}

	public boolean isCanceled() {
		return canceled;
	}
}
