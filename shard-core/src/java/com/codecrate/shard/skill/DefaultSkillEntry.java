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
package com.codecrate.shard.skill;

import com.codecrate.shard.modifier.DefaultModifierType;
import com.codecrate.shard.modifier.Modifiable;
import com.codecrate.shard.modifier.ModifiableObject;
import com.codecrate.shard.modifier.Modifier;
import com.codecrate.shard.modifier.ModifierType;

/**
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class DefaultSkillEntry extends ModifiableObject implements Modifiable, SkillEntry {
    public static final ModifierType TYPE_RANK = new DefaultModifierType("rank", true);
    
	private final Skill skill;

	public DefaultSkillEntry(Skill skill) {
	    super();
		this.skill = skill;
	}
	
	public Skill getSkill() {
		return skill;
	}
	
	public Modifier getRank() {
	    return getModifier(TYPE_RANK);
	}
	
	public String toString() {
	    return skill + "(" + getModifiedValue() + ")";
	}
}