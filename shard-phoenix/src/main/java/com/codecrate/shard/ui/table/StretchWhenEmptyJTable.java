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

import java.awt.Container;

import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.table.TableModel;

/**
 * 
 * @see http://weblogs.java.net/blog/shan_man/archive/2006/01/enable_dropping.html
 */
public class StretchWhenEmptyJTable extends JTable {

	public StretchWhenEmptyJTable(TableModel model) {
		super(model);
	}

	public boolean getScrollableTracksViewportHeight() {
	    // fetch the table's parent
	    Container viewport = getParent();

	    // if the parent is not a viewport, calling this isn't useful
	    if (!(viewport instanceof JViewport)) {
	        return false;
	    }

	    // return true if the table's preferred height is smaller
	    // than the viewport height, else false
	    return getPreferredSize().height < viewport.getHeight();
	}
}
