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

    /**
     * @return
     */
    public Collection getListeners() {
        return delegates;
    }
}
