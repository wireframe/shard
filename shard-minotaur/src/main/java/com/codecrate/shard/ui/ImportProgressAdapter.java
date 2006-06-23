package com.codecrate.shard.ui;

import com.codecrate.shard.transfer.progress.ProgressMonitor;

public class ImportProgressAdapter implements ProgressMonitor {

	private final org.springframework.richclient.progress.ProgressMonitor delegate;
	private int unitsWorked = 0;

	public ImportProgressAdapter(org.springframework.richclient.progress.ProgressMonitor delegate) {
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
}
