package com.codecrate.shard.ability;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class CompositeAbilityScoreListener implements AbilityScoreListener {

	private Collection delegates = new ArrayList();
	
	public void onModify() {
		Iterator it = delegates.iterator();
		while (it.hasNext()) {
			AbilityScoreListener listener = (AbilityScoreListener) it.next();
			listener.onModify();
		}
	}

	/**
	 * @param listener
	 */
	public void addListener(AbilityScoreListener listener) {
		delegates.add(listener);
	}

	/**
	 * @param listener
	 */
	public void removeListener(AbilityScoreListener listener) {
		delegates.remove(listener);
	}
}
