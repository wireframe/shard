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

import java.util.Arrays;
import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SavingThrowEntryContainer {
	private static final Log LOG = LogFactory.getLog(SavingThrowEntryContainer.class);
	
	private SavingThrowEntry reflexSave;
	private SavingThrowEntry fortitudeSave;
	private SavingThrowEntry willSave;
	
	public SavingThrowEntryContainer() {
		reflexSave = new SavingThrowEntry(DefaultSavingThrow.REFLEX);
		fortitudeSave = new SavingThrowEntry(DefaultSavingThrow.FORTITUDE);
		willSave = new SavingThrowEntry(DefaultSavingThrow.WILLPOWER);
	}
	
	public SavingThrowEntry getReflexSavingThrow() {
		return reflexSave;
	}
	
	public SavingThrowEntry getFortitudeSavingThrow() {
		return fortitudeSave;
	}
	
	public SavingThrowEntry getWillpowerSavingThrow() {
		return willSave;
	}
	
	public SavingThrowEntry getSavingThrowEntry(SavingThrow save) {
		if (DefaultSavingThrow.REFLEX.equals(save)) {
			return reflexSave;
		} else if (DefaultSavingThrow.FORTITUDE.equals(save)) {
			return fortitudeSave;
		} else if (DefaultSavingThrow.WILLPOWER.equals(save)) {
			return willSave;
		}
		LOG.error("No saving throw entry found for " + save);
		return null;
	}

	public Collection getEntries() {
		return Arrays.asList(new SavingThrowEntry[] {reflexSave, fortitudeSave, willSave});
	}
}
