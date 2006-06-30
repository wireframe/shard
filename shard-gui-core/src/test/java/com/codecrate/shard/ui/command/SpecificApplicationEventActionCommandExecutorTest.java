package com.codecrate.shard.ui.command;

import java.util.Collections;
import java.util.Date;
import java.util.Map;

import junit.framework.TestCase;

import org.easymock.MockControl;
import org.springframework.context.ApplicationEvent;
import org.springframework.richclient.command.ParameterizableActionCommandExecutor;

public class SpecificApplicationEventActionCommandExecutorTest extends TestCase {

    public void testEventPassedToDelegateAsMapEntry() {
        MyEvent event = new MyEvent();
        Map params = Collections.singletonMap("event", event);
        
        MockControl mockDelegateExecutor = MockControl.createControl(ParameterizableActionCommandExecutor.class);
        ParameterizableActionCommandExecutor delegateExecutor = (ParameterizableActionCommandExecutor) mockDelegateExecutor.getMock();
        delegateExecutor.execute(params);
        mockDelegateExecutor.setVoidCallable();
        mockDelegateExecutor.replay();
        
        SpecificApplicationEventActionCommandExecutor executor = new SpecificApplicationEventActionCommandExecutor(MyEvent.class, delegateExecutor);
        executor.onApplicationEvent(event);
    }

    public class MyEvent extends ApplicationEvent {
        public MyEvent() {
            super(new Date());
        }
    }
}
