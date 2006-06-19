package com.codecrate.shard;

import javax.swing.JOptionPane;

import org.springframework.richclient.application.config.DefaultApplicationLifecycleAdvisor;

public class ShardMinotaurLifecycleAdvisor extends DefaultApplicationLifecycleAdvisor {

//http://common.l2fprod.com/
//	public void onPreStartup() {
//		JOptionPane.showMessageDialog(null, "no objects found", "application error", JOptionPane.WARNING_MESSAGE);
//	}
//
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
//    public void onCommandsCreated(ApplicationWindow window) {
//        ActionCommand command = window.getCommandManager().getActionCommand("loginCommand");
//        command.execute();
//    }
}
