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
package com.codecrate.shard.character;

import java.util.Collection;

import com.codecrate.shard.Modifiable;
import com.codecrate.shard.ModifiableObject;
import com.codecrate.shard.Modifier;

/**
 * Initiative is used for determining when the character enters battle.
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class Initiative implements Modifiable {

    private ModifiableObject delegate = new ModifiableObject();
    
    public int getValue() {
        return delegate.getValue();
    }

    /**
     * @param modifier
     */
    public void addModifier(Modifier modifier) {
        delegate.addModifier(modifier);
    }
    /**
     * @return
     */
    public int getModifiedValue() {
        return delegate.getModifiedValue();
    }
    /**
     * @return
     */
    public Collection getModifiers() {
        return delegate.getModifiers();
    }
    /**
     * @param modifier
     */
    public void removeModifier(Modifier modifier) {
        delegate.removeModifier(modifier);
    }
}
