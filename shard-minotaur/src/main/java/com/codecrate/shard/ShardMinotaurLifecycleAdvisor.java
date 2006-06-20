package com.codecrate.shard;

import org.springframework.richclient.application.ApplicationWindow;
import org.springframework.richclient.application.config.DefaultApplicationLifecycleAdvisor;

import com.codecrate.shard.ui.ImportCommand;

public class ShardMinotaurLifecycleAdvisor extends DefaultApplicationLifecycleAdvisor {

private ImportCommand importCommand;

//http://common.l2fprod.com/
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

    public void onWindowOpened(ApplicationWindow window) {
        super.onWindowOpened(window);
        
        importCommand = (ImportCommand) getApplication().getServices().getBean("importCommand", ImportCommand.class);
        importCommand.setApplicationWindow(window);
        importCommand.execute();
    }
}
