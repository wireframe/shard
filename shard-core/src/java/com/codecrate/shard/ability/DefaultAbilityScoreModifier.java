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
package com.codecrate.shard.ability;

import com.codecrate.shard.ModifierType;

/**
 * Helper class to easily work with ability modifiers.
 * provides basic defaults for how ability modifiers should work.
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class DefaultAbilityScoreModifier implements AbilityScoreModifier {

	private final Ability ability;
	private final int modifier;
    private final ModifierType type;

	public DefaultAbilityScoreModifier(ModifierType type, Ability ability, int modifier) {
		this.type = type;
        this.ability = ability;
		this.modifier = modifier;
	}

	public Ability getAbility() {
		return ability;
	}

	public int getModifier() {
		return modifier;
	}

    public ModifierType getModifierType() {
        return type;
    }

}
