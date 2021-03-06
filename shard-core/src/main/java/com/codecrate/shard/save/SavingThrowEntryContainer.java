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

import com.codecrate.shard.modifier.KeyedModifier;
import com.codecrate.shard.modifier.KeyedModifierContainer;

public class SavingThrowEntryContainer implements KeyedModifierContainer {
	private static final Log LOG = LogFactory.getLog(SavingThrowEntryContainer.class);
	
	private SavingThrowEntry reflexSave;
	private SavingThrowEntry fortitudeSave;
	private SavingThrowEntry willSave;
	
	public SavingThrowEntryContainer() {
		reflexSave = new SavingThrowEntry(SavingThrow.REFLEX);
		fortitudeSave = new SavingThrowEntry(SavingThrow.FORTITUDE);
		willSave = new SavingThrowEntry(SavingThrow.WILLPOWER);
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
		if (SavingThrow.REFLEX.equals(save)) {
			return reflexSave;
		} else if (SavingThrow.FORTITUDE.equals(save)) {
			return fortitudeSave;
		} else if (SavingThrow.WILLPOWER.equals(save)) {
			return willSave;
		}
		LOG.error("No saving throw entry found for " + save);
		return null;
	}

	public Collection getEntries() {
		return Arrays.asList(new SavingThrowEntry[] {reflexSave, fortitudeSave, willSave});
	}

    public void addModifier(KeyedModifier modifier) {
        SavingThrow save = (SavingThrow) modifier.getKey();
        SavingThrowEntry entry = getSavingThrowEntry(save);
        if (null == entry) {
            LOG.info("Cannot attach modifier without saving throw entry: " + save);
        } else {
            entry.addModifier(modifier);
        }
    }

    public void removeModifier(KeyedModifier modifier) {
        SavingThrow save = (SavingThrow) modifier.getKey();
        SavingThrowEntry entry = getSavingThrowEntry(save);
        if (null == entry) {
            LOG.info("Cannot remove modifier without saving throw entry: " + save);
        } else {
            entry.removeModifier(modifier);
        }
    }
}
