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

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ScrollPaneConstants;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.richclient.application.support.AbstractView;

import com.codecrate.shard.transfer.pcgen.PcgenDatasetImporter;
import com.codecrate.shard.ui.command.ImportDatasetEvent;

public class ListDatasetsView extends AbstractView implements ApplicationEventPublisherAware {

    private final PcgenDatasetImporter importer;
    private ApplicationEventPublisher publisher;
    public ListDatasetsView(PcgenDatasetImporter importer) {
        this.importer = importer;
    }
    protected JComponent createControl() {
        JPanel panel = new JPanel();

        final JList list = new JList(importer.getAvailableDatasets().toArray());
        list.setVisibleRowCount(20);
        list.setCellRenderer(new ListCellRenderer() {

            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                File dataset = (File) value;

                JLabel label = new JLabel(trimDatasetPath(dataset.getAbsolutePath()));
                if (isSelected) {
                    label.setBackground(list.getSelectionBackground());
                    label.setForeground(list.getSelectionForeground());
                } else {
                    label.setBackground(list.getBackground());
                    label.setForeground(list.getForeground());
                }
                label.setEnabled(list.isEnabled());
                label.setFont(list.getFont());
                label.setOpaque(true);

                return label;
            }
        });
        JScrollPane scroller = new JScrollPane(list);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(scroller);

        JButton importButton = getComponentFactory().createButton("import selected datasets");
        importButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                int[] selectedIndexes = list.getSelectedIndices();
                if (null != selectedIndexes) {
                    for (int x = 0; x < selectedIndexes.length; x++) {
                        int index = selectedIndexes[x];
                        File dataset = (File) list.getModel().getElementAt(index);
                        ImportDatasetEvent event = new ImportDatasetEvent(this);
                        event.setSelectedDirectory(dataset);

                        publisher.publishEvent(event);
                    }
                }
            }
        });

        panel.add(importButton);

        return panel;
    }
    private String trimDatasetPath(String path) {
        int index = path.indexOf("pcgen-");

        return path.substring(index);
    }
    public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }
}