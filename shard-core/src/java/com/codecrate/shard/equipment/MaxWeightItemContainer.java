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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class MaxWeightItemContainer implements ItemContainer {

	private int maxWeight;
	private Collection items = new ArrayList();
	
	public MaxWeightItemContainer(int maxWeight) {
		this.maxWeight = maxWeight;
	}

	public boolean canAdd(Item equipment) {
		BigDecimal weight = new BigDecimal(0);
		Iterator it = items.iterator();
		while (it.hasNext()) {
			Item item = (Item) it.next();
			weight = weight.add(item.getWeight());
		}
		
		if (weight.add(equipment.getWeight()).intValue() <= maxWeight) {
			return true;
		}
		return false;
	}

	public void add(Item equipment) {
		if (canAdd(equipment)) {
			items.add(equipment);
		}
	}

	public void remove(Item equipment) {
		items.remove(equipment);
	}
	
	public Collection getItems() {
		return items;
	}
	
	public BigDecimal getTotalWeight() {
	    BigDecimal total = new BigDecimal(0);
	    Iterator it = items.iterator();
	    while (it.hasNext()) {
	        Item item = (Item) it.next();
	        total = total.add(item.getWeight());
	    }
	    return total;
	}
	
	public Money getTotalCost() {
	    Money total = new Money(0, DefaultCurrency.GOLD);
	    Iterator it = items.iterator();
	    while (it.hasNext()) {
	        Item item = (Item) it.next();
	        total = total.add(item.getCost());
	    }
	    return total;
	}
}
