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
package com.codecrate.shard.kit;

import java.util.Collection;
import java.util.Iterator;

public class DefaultClassProgression implements ClassProgression {
	
	private Collection levels;
	
	public DefaultClassProgression(Collection levels) {
		this.levels = levels;
	}
	
	public Collection getClassLevels() {
		return levels;
	}
	
	public int getMaxLevel() {
	    return levels.size();
	}
	
	public ClassLevel getClassLevel(int level) {
	    ClassLevel kit = null;
	    Iterator it = levels.iterator();
	    while (it.hasNext()) {
	        ClassLevel object = (ClassLevel) it.next();
	        if (level == object.getLevel()) {
	            return object;
	        }
	    }
	    return kit;
	}
}
