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

import com.codecrate.shard.Modifiable;
import com.codecrate.shard.ModifiableObject;
import com.codecrate.shard.Modifier;


public class SavingThrowEntry implements Modifiable {

	private final SavingThrow save;
	private final ModifiableObject delegate = new ModifiableObject();
	
	public SavingThrowEntry(SavingThrow save) {
		this.save = save;
	}
	
	public SavingThrow getSave() {
	    return save;
	}

    public int getValue() {
        return delegate.getValue();
    }

    public int getModifiedValue() {
        return delegate.getModifiedValue();
    }

    public void addModifier(Modifier modifier) {
        delegate.addModifier(modifier);
    }

    public void removeModifier(Modifier modifier) {
        delegate.removeModifier(modifier);
    }

    public Collection getModifiers() {
        return delegate.getModifiers();
    }
}
