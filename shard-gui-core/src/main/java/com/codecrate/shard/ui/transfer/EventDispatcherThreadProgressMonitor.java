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
package com.codecrate.shard.ui.transfer;

import javax.swing.SwingUtilities;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.codecrate.shard.transfer.progress.ProgressMonitor;

/**
 * makes sure calls to progress monitor occur on event dispatch thread.
 * this is very important when background threads are performing operations
 * and trying to display progress information to the user.
 */
public class EventDispatcherThreadProgressMonitor implements ProgressMonitor {
    private static final Log LOG = LogFactory.getLog(EventDispatcherThreadProgressMonitor.class);

	private final ProgressMonitor delegate;

	public EventDispatcherThreadProgressMonitor(ProgressMonitor delegate) {
		this.delegate = delegate;
	}

	public void startTask(final String description, final int totalUnitsOfWork) {
        updateProgressBar(new Runnable() {
            public void run() {
                delegate.startTask(description, totalUnitsOfWork);
            }
        });
	}

	public void completeUnitOfWork() {
        updateProgressBar(new Runnable() {
            public void run() {
                delegate.completeUnitOfWork();
            }
        });
	}

	public void finish() {
        updateProgressBar(new Runnable() {
            public void run() {
                delegate.finish();
            }
        });
	}

    private void updateProgressBar(Runnable task) {
        if (!SwingUtilities.isEventDispatchThread()) {
            try {
                SwingUtilities.invokeAndWait(task);
            } catch (Exception e) {
                LOG.warn("Error updating progress bar", e);
            }
        } else {
            task.run();
        }
    }
}
