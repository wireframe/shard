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
package com.codecrate.shard.ui;

import org.springframework.richclient.application.config.DefaultApplicationLifecycleAdvisor;

public class ShardPhoenixLifecycleAdvisor extends DefaultApplicationLifecycleAdvisor {

//    public void onPreWindowOpen(ApplicationWindowConfigurer configurer) {
//        super.onPreWindowOpen(configurer);
//        if (getApplication().getApplicationContext().containsBean("setupWizard")) {
//            SetupWizard setupWizard = (SetupWizard)getApplication().getApplicationContext().getBean("setupWizard", SetupWizard.class);
//            setupWizard.execute();
//        }
//        // comment out to hide the menubar, toolbar, or reduce window size...
//        //configurer.setShowMenuBar(false);
//        //configurer.setShowToolBar(false);
//        //configurer.setInitialSize(new Dimension(640, 480));
//    }
//
//
//    public void onWindowOpened(ApplicationWindow window) {
//        super.onWindowOpened(window);
//
//        importCommand = (ImportDatasetCommand) getApplication().getServices().getBean("importCommandExecutor", ImportDatasetCommand.class);
//        importCommand.setApplicationWindow(window);
//        if (importCommand.isImportNeeded()) {
//            importCommand.execute();
//        }
//    }
}
