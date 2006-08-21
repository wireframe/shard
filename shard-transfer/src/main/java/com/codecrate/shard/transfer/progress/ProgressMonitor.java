/*
 * Copyright 2004 codecrate consulting
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.codecrate.shard.transfer.progress;

public interface ProgressMonitor {

	/**
	 * start a new task.
	 * @param description
	 * @param totalUnitsOfWork
	 */
	void startTask(String description, int totalUnitsOfWork);

	/**
	 * complete a portion of work on the current task.
	 */
	void completeUnitOfWork();

	/**
	 * finish task even if not all work has been completed.
	 */
	void finish();

	void cancel();

	boolean isCanceled();
}
