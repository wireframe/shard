package com.codecrate.shard.ui.command;

import javax.swing.SwingUtilities;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.richclient.command.ActionCommandExecutor;

public class EventDispatcherThreadActionCommandExecutor implements ActionCommandExecutor {
	private static final Log LOG = LogFactory.getLog(EventDispatcherThreadActionCommandExecutor.class);

	private final ActionCommandExecutor delegate;

	public EventDispatcherThreadActionCommandExecutor(ActionCommandExecutor delegate) {
		this.delegate = delegate;
	}

	public void execute() {
		if (!SwingUtilities.isEventDispatchThread()) {
			LOG.debug("Redirecting execution of " + delegate + " to event dispatch thread");
			SwingUtilities.invokeAndWait((new Runnable() {
				public void run() {
					delegate.execute();
				}
			});
		} else {
			delegate.execute();
		}
	}
}
