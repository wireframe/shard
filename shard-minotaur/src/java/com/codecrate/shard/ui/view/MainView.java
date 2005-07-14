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
package com.codecrate.shard.ui.view;

import javax.swing.JComponent;
import javax.swing.JPanel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.Template;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.richclient.application.PageComponentContext;
import org.springframework.richclient.application.support.AbstractView;
import org.springframework.richclient.command.support.AbstractActionCommandExecutor;

import com.codecrate.shard.action.PrintCharacterAction;
import com.codecrate.shard.ui.ShardCommandIds;

public class MainView extends AbstractView {
	private static final Log LOGGER = LogFactory.getLog(MainView.class);
	
    private PrintCommandExecutor printExecutor;
    private VelocityEngine velocityEngine;
    
    protected JComponent createControl() {
        JPanel view = new JPanel();
        return view;
    }
    
    protected void registerLocalCommandExecutors(PageComponentContext context) {
        context.register(ShardCommandIds.PRINT, getPrintCommand());
    }
    
    public void setVelocityEngine(VelocityEngine engine) {
    	this.velocityEngine = engine;
    }
    
    private PrintCommandExecutor getPrintCommand() {
        if (null == printExecutor) {
        	printExecutor = new PrintCommandExecutor();
            printExecutor.setEnabled(true);
        }
        return printExecutor;
    }

    private class PrintCommandExecutor extends AbstractActionCommandExecutor {

        public void execute() {
			try {
	        	Template template = velocityEngine.getTemplate("default.vm");
	        	PrintCharacterAction printAction = new PrintCharacterAction(null, template);
	        	System.out.println(printAction.render().toString());
			} catch (Exception e) {
				LOGGER.warn("Error trying to print character", e);
			}
       }
    }
}