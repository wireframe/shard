package com.codecrate.shard.ui.binding;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

import javax.swing.JComponent;

import org.springframework.binding.form.FormModel;
import org.springframework.richclient.form.binding.support.CustomBinding;

import com.l2fprod.common.swing.JDirectoryChooser;

public class JDirectoryChooserBinding extends CustomBinding {

    private final JDirectoryChooser component;

    public JDirectoryChooserBinding(FormModel model, String property, JDirectoryChooser component) {
        super(model, property, File.class);
        this.component = component;
    }

    protected JComponent doBindControl() {
        component.setSelectedFile((File)getValue());
        component.addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {
                String prop = evt.getPropertyName();
                if(JDirectoryChooser.SELECTED_FILE_CHANGED_PROPERTY.equals(prop)) {
                    File file = (File) evt.getNewValue();
                    controlValueChanged(file);
                }
            }
        });
        return component;
    }

    protected void readOnlyChanged() {
        component.setEnabled(isEnabled() && !isReadOnly());
    }

    protected void enabledChanged() {
        component.setEnabled(isEnabled() && !isReadOnly());
    }

    protected void valueModelChanged(Object newValue) {
        component.setSelectedFile((File)newValue);
    }
}
