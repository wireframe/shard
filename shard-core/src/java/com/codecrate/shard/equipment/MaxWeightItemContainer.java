package com.codecrate.shard.equipment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class MaxWeightItemContainer implements ItemContainer {

	private int maxWeight;
	private Collection items = new ArrayList();
	
	public MaxWeightItemContainer(int maxWeight) {
		this.maxWeight = maxWeight;
	}

	public boolean canAdd(Item equipment) {
		int weight = 0;
		Iterator it = items.iterator();
		while (it.hasNext()) {
			Item item = (Item) it.next();
			weight += item.getWeight();
		}
		
		if (weight + equipment.getWeight() <= maxWeight) {
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
}
