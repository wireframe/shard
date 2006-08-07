package com.codecrate.shard.ui;

import org.springframework.richclient.application.ApplicationLauncher;
import org.springframework.richclient.application.config.ApplicationLifecycleAdvisor;

public class ShardApplicationLauncher {

	public ShardApplicationLauncher(String startupContext, String[] rootContexts) {
        try {
    		new ApplicationLauncher(startupContext, rootContexts);
        } catch (Exception e) {
        	new ApplicationLifecycleAdvisor.DefaultEventExceptionHandler().handle(e);
            System.out.println("Error launching application: " + e);
            e.printStackTrace();
            System.exit(1);
        }
	}
}
