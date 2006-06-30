package com.codecrate.shard.ui.command;

import java.util.Collections;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.richclient.command.ParameterizableActionCommandExecutor;

import com.codecrate.shard.ui.event.AbstractSpecificApplicationEventListener;

public class SpecificApplicationEventActionCommandExecutor extends AbstractSpecificApplicationEventListener implements ParameterizableActionCommandExecutor, ApplicationListener {
	private static final Log LOG = LogFactory.getLog(SpecificApplicationEventActionCommandExecutor.class);

	private final ParameterizableActionCommandExecutor delegate;

	public SpecificApplicationEventActionCommandExecutor(Class targetClass, ParameterizableActionCommandExecutor delegate) {
        super(targetClass);
		this.delegate = delegate;
	}

    protected void onSpecificApplicationEvent(ApplicationEvent event) {
        LOG.info("Executing action " + delegate + " due to application event " + event);
        execute(Collections.singletonMap("event", event));
    }

    public void execute() {
        execute(Collections.EMPTY_MAP);
    }


    public void execute(Map params) {
        delegate.execute(params);
    }
}
