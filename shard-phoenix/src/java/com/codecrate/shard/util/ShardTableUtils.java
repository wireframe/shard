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
package com.codecrate.shard.util;

import javax.swing.JTable;
import javax.swing.table.TableModel;

import org.springframework.richclient.table.SortableTableModel;

/**
 * Custom utilities for working with table models.
 * these methods should be handled by the spring-rcp framework, but aren't.
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class ShardTableUtils {
	/**
	 * gets the index of a selected row.
	 * performs extra work if the table model is sortable.
	 * @param table
	 * @return
	 */
    public static int getSelectedIndex(JTable table) {
        int index = table.getSelectedRow();
		if (-1 == index) {
        	return -1;
        }
        
        TableModel model = table.getModel();
		if (model instanceof SortableTableModel) {
            return ((SortableTableModel) model).convertSortedIndexToDataIndex(index);
        }
		
        return index;
    }
    
    /**
     * get the indexes of all selected rows.
	 * performs extra work if the table model is sortable.
     * @param table
     * @return
     */
    public static int[] getSelectedIndexes(JTable table) {
        int[] indexes = table.getSelectedRows();
        if (null == indexes) {
        	return null;
        }
        
        TableModel model = table.getModel();
		if (model instanceof SortableTableModel) {
            return ((SortableTableModel)model).convertSortedIndexesToDataIndexes(indexes);
        }
        return indexes;
    }
}