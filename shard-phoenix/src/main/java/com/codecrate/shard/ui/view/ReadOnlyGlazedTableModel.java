package com.codecrate.shard.ui.view;

import org.springframework.context.MessageSource;
import org.springframework.richclient.table.support.GlazedTableModel;

import ca.odell.glazedlists.EventList;

/**
 * unmodifiable table model to force users to use the "properties" dialog.
 */
public class ReadOnlyGlazedTableModel extends GlazedTableModel {

    public ReadOnlyGlazedTableModel(EventList objects, MessageSource messageSource, String[] columnNames) {
        super(objects, messageSource, columnNames);
    }

    protected boolean isEditable(Object arg0, int arg1) {
        return false;
    }
}
