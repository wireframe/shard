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

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SavingThrowContainer {
	public static final String REFLEX = "reflex";
	public static final String FORTITUDE = "fortitude";
	public static final String WILLPOWER = "willpower";

	private static final Log LOG = LogFactory.getLog(SavingThrowContainer.class);
	private Map savingThrows;

	public SavingThrowContainer(Map savingThrows) {
		this.savingThrows = savingThrows;
	}
	
	public SavingThrow getReflexSavingThrow() {
		return getSavingThrow(REFLEX);
	}
	
	public SavingThrow getFortitudeSavingThrow() {
		return getSavingThrow(FORTITUDE);
	}
	
	public SavingThrow getWillpowerSavingThrow() {
		return getSavingThrow(WILLPOWER);
	}
	
	public SavingThrow getSavingThrow(String name) {
		SavingThrow savingThrow = (SavingThrow) savingThrows.get(name);
		if (null == savingThrow) {
			LOG.debug("No saving throw found with name: " + name);
		}
		return savingThrow;
	}
}
