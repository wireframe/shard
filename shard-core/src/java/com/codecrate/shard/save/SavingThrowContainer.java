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

import java.util.Collection;
import java.util.Map;

public class SavingThrowContainer {
	private Map savingThrows;

	public SavingThrowContainer(Map savingThrows) {
		this.savingThrows = savingThrows;
	}
	
	public SavingThrowEntry getReflexSavingThrow() {
		return getSavingThrowEntry(DefaultSavingThrow.REFLEX);
	}
	
	public SavingThrowEntry getFortitudeSavingThrow() {
		return getSavingThrowEntry(DefaultSavingThrow.FORTITUDE);
	}
	
	public SavingThrowEntry getWillpowerSavingThrow() {
		return getSavingThrowEntry(DefaultSavingThrow.WILLPOWER);
	}
	
	public SavingThrowEntry getSavingThrowEntry(SavingThrow save) {
		return (SavingThrowEntry) savingThrows.get(save);
	}

	public Collection getEntries() {
		return savingThrows.values();
	}
}
