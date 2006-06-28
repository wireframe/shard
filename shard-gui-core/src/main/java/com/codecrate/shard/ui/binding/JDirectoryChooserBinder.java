package com.codecrate.shard.ui.binding;

import java.io.File;
import java.util.Map;

import javax.swing.JComponent;

import org.springframework.binding.form.FormModel;
import org.springframework.richclient.form.binding.Binding;
import org.springframework.richclient.form.binding.support.AbstractBinder;

import com.l2fprod.common.swing.JDirectoryChooser;

public class JDirectoryChooserBinder extends AbstractBinder {

    protected JDirectoryChooserBinder() {
        super(File.class);
    }

    protected JComponent createControl(Map context) {
        return new JDirectoryChooser();
    }

    protected Binding doBind(JComponent control, FormModel formModel, String formPropertyPath, Map context) {
        final JDirectoryChooser directoryChooser = (JDirectoryChooser) control;
        return new JDirectoryChooserBinding(formModel, formPropertyPath, directoryChooser);
    }
}
