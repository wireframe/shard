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
package com.codecrate.shard.ui.table;

import java.util.Locale;

import org.springframework.context.MessageSource;

import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.gui.TableFormat;
import ca.odell.glazedlists.swing.EventTableModel;

/**
 * unmodifiable table model to force users to use the "properties" dialog.
 */
public class ReadOnlyEventTableModel extends EventTableModel {

    public ReadOnlyEventTableModel(EventList objects, MessageSource messageSource, String[] propertyNames) {
        super(objects, buildTableModel(messageSource, propertyNames));
    }

    private static TableFormat buildTableModel(MessageSource messageSource, String[] propertyNames) {
        return GlazedLists.tableFormat(propertyNames, buildColumnNames(messageSource, propertyNames), fillBooleanArray(propertyNames.length));
    }

    private static boolean[] fillBooleanArray(int length) {
        boolean[] array = new boolean[length];
        for (int x = 0; x < length; x++) {
            array[x] = false;
        }
        return array;
    }

    private static String[] buildColumnNames(MessageSource messageSource, String[] propertyNames) {
        String[] columnNames = new String[propertyNames.length];
        for (int x = 0; x < propertyNames.length; x++) {
            columnNames[x] = messageSource.getMessage(propertyNames[x], null, propertyNames[x], Locale.getDefault());
        }
        return columnNames;
    }

    protected boolean isEditable(Object arg0, int arg1) {
        return false;
    }
}
