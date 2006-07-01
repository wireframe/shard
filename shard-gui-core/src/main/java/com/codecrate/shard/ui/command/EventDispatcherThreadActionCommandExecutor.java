package com.codecrate.shard.ui.command;

import java.util.Collections;
import java.util.Map;

import javax.swing.SwingUtilities;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.richclient.command.ParameterizableActionCommandExecutor;

public class EventDispatcherThreadActionCommandExecutor implements ParameterizableActionCommandExecutor {
	private static final Log LOG = LogFactory.getLog(EventDispatcherThreadActionCommandExecutor.class);

	private final ParameterizableActionCommandExecutor delegate;

	public EventDispatcherThreadActionCommandExecutor(ParameterizableActionCommandExecutor delegate) {
		this.delegate = delegate;
	}

	public void execute() {
		execute(Collections.EMPTY_MAP);
	}

	public void execute(Map params) {
		if (!SwingUtilities.isEventDispatchThread()) {
			LOG.debug("Redirecting execution of " + delegate + " to event dispatch thread");
			try {
				SwingUtilities.invokeAndWait(new Runnable() {
					public void run() {
						delegate.execute();
					}
				});
			} catch (Exception e) {
				throw new RuntimeException("Error executing " + delegate + " on event dispatch thread");
			}
		} else {
			delegate.execute(params);
		}
	}
}
