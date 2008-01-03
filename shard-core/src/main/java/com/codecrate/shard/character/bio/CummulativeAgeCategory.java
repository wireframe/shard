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
package com.codecrate.shard.character.bio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import com.codecrate.shard.ability.Ability;
import com.codecrate.shard.modifier.DefaultKeyedModifier;
import com.codecrate.shard.modifier. DefaultModifierType;
import com.codecrate.shard.modifier.KeyedModifier;

/**
 * Age category to encasulate cumulative modifier logic.
 * effects of age are cumulative (ex: when old, also have modifiers from middle age)
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class CummulativeAgeCategory implements AgeCategory {
	public static final AgeCategory ADULT = new CummulativeAgeCategory(null, 
			"Adult", new ArrayList());

	public static final AgeCategory MIDDLE_AGE = new CummulativeAgeCategory(ADULT,
			"Middle Age", Arrays.asList(new KeyedModifier[] {
			new DefaultKeyedModifier(Ability.STRENGTH, DefaultModifierType.AGE, -1)
			, new DefaultKeyedModifier(Ability.DEXTERITY, DefaultModifierType.AGE, -1)
			, new DefaultKeyedModifier(Ability.CONSTITUTION, DefaultModifierType.AGE, -1)
			, new DefaultKeyedModifier(Ability.INTELLIGENCE, DefaultModifierType.AGE, 1)
			, new DefaultKeyedModifier(Ability.WISDOM, DefaultModifierType.AGE, 1)
			, new DefaultKeyedModifier(Ability.CHARISMA, DefaultModifierType.AGE, 1)
		}));

	public static final AgeCategory OLD = new CummulativeAgeCategory(MIDDLE_AGE,
			"Old", Arrays.asList(new DefaultKeyedModifier[] {
			new DefaultKeyedModifier(Ability.STRENGTH, DefaultModifierType.AGE, -2)
			, new DefaultKeyedModifier(Ability.DEXTERITY, DefaultModifierType.AGE, -2)
			, new DefaultKeyedModifier(Ability.CONSTITUTION, DefaultModifierType.AGE, -2)
			, new DefaultKeyedModifier(Ability.INTELLIGENCE, DefaultModifierType.AGE, 1)
			, new DefaultKeyedModifier(Ability.WISDOM, DefaultModifierType.AGE, 1)
			, new DefaultKeyedModifier(Ability.CHARISMA, DefaultModifierType.AGE, 1)
		}));
	
	public static final AgeCategory VENERABLE = new CummulativeAgeCategory(OLD,
			"Venerable", Arrays.asList(new DefaultKeyedModifier[] {
			new DefaultKeyedModifier(Ability.STRENGTH, DefaultModifierType.AGE, -3)
			, new DefaultKeyedModifier(Ability.DEXTERITY, DefaultModifierType.AGE, -3)
			, new DefaultKeyedModifier(Ability.CONSTITUTION, DefaultModifierType.AGE, -3)
			, new DefaultKeyedModifier(Ability.INTELLIGENCE, DefaultModifierType.AGE, 1)
			, new DefaultKeyedModifier(Ability.WISDOM, DefaultModifierType.AGE, 1)
			, new DefaultKeyedModifier(Ability.CHARISMA, DefaultModifierType.AGE, 1)
		}));
	
	
	private final String name;
	private Collection abilityModifiers;

	public CummulativeAgeCategory(AgeCategory previousCategory, String name, Collection abilityModifiers) {
		this.name = name;
		this.abilityModifiers = new ArrayList(abilityModifiers);
		if (null != previousCategory) {
			this.abilityModifiers.addAll(previousCategory.getAbilityModifiers());
		}
	}
	
	public String toString() {
	    return name;
	}
	
	public String getName() {
		return name;
	}
	
	public Collection getAbilityModifiers() {
		return abilityModifiers;
	}
}
