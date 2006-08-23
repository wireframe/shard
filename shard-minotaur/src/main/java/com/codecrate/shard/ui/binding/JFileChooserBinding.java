/*
 * Copyright 2004 codecrate consulting
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.codecrate.shard.ui.binding;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.springframework.binding.form.FormModel;
import org.springframework.richclient.form.binding.support.CustomBinding;

public class JFileChooserBinding extends CustomBinding {
	private final JLabel label = new JLabel();
	private final JFileChooser component;

	public JFileChooserBinding(FormModel model, String property, JFileChooser component) {
		super(model, property, File.class);
		this.component = component;
	}

    protected JComponent doBindControl() {
        File value = (File) getValue();

		component.setSelectedFile(value);
        component.addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {
                String prop = evt.getPropertyName();
                if (JFileChooser.SELECTED_FILE_CHANGED_PROPERTY.equals(prop)) {
                    File file = (File) evt.getNewValue();
                    controlValueChanged(file);

                    updateLabel(file);
                }
            }
        });

        updateLabel(value);

        JPanel panel = new JPanel();
        panel.add(label);

        JButton browseButton = new JButton("Browse");
        browseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				component.showDialog(getActiveWindow().getControl(), "Ok");
			}
        });
		panel.add(browseButton);
        return panel;
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

	private void updateLabel(File file) {
		if (null == file) {
			label.setText("No File Selected");
		} else {
			label.setText(file.getName());
		}
	}
}
