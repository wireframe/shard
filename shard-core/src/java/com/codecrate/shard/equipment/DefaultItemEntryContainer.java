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
package com.codecrate.shard.equipment;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Iterator;

/**
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class DefaultItemEntryContainer implements ItemEntryContainer {

	private final Collection entries;

    public DefaultItemEntryContainer(Collection entries) {
		this.entries = entries;
	}

	public Collection getItems() {
		return entries;
	}
	
	public BigDecimal getTotalWeight() {
	    BigDecimal total = new BigDecimal(0);
	    Iterator it = entries.iterator();
	    while (it.hasNext()) {
	        Item item = (Item) it.next();
	        total = total.add(item.getWeight());
	    }
	    return total;
	}
	
	public Money getTotalCost() {
	    Money total = null;
	    Iterator it = entries.iterator();
	    while (it.hasNext()) {
	        Item item = (Item) it.next();
	        if (null == total) {
	            total = item.getCost();
	        } else {
		        total = total.add(item.getCost());
	        }
	    }
	    return total;
	}
}
