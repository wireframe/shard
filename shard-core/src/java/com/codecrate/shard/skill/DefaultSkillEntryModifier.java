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

import com.codecrate.shard.ModifierType;


public class DefaultSkillEntryModifier implements SkillEntryModifier {
	private final int value;
	private final Skill skill;
    private final ModifierType type;

	public DefaultSkillEntryModifier(ModifierType type, int value, Skill skill) {
		this.type = type;
        this.value = value;
		this.skill = skill;
		
	}

	public Skill getSkill() {
		return skill;
	}

    public ModifierType getModifierType() {
        return type;
    }

    public int getModifier() {
        return value;
    }
}
