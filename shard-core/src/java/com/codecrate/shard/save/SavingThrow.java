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
package com.codecrate.shard.save;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;


public class SavingThrow {

	private final String name;
	private Collection modifiers = new ArrayList();
	
	public SavingThrow(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public int getModifier() {
		int value = 0;
		Iterator it = modifiers.iterator();
		while (it.hasNext()) {
			SavingThrowModifier modifier = (SavingThrowModifier) it.next();
			value += modifier.getModifier();
		}
		return value;
	}

	public void addSavingThrowModifier(SavingThrowModifier modifier) {
		modifiers.add(modifier);
	}

	public void removeSavingThrowModifier(SavingThrowModifier modifier) {
		modifiers.remove(modifier);
	}
}
