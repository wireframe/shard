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
package com.codecrate.shard.character.age;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import com.codecrate.shard.character.ability.AbilityModifier;
import com.codecrate.shard.character.ability.DefaultAbilityContainer;
import com.codecrate.shard.character.ability.DefaultAbilityModifier;

/**
 * Default age category definition.
 * effects of age are cumulative (ex: when old, also have modifiers from middle age)
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class DefaultAgeCategory implements AgeCategory {
	public static final AgeCategory ADULT = new DefaultAgeCategory("Adult", new ArrayList());
	public static final AgeCategory MIDDLE_AGE = new DefaultAgeCategory("Middle Age", Arrays.asList(new AbilityModifier[] {
			new DefaultAbilityModifier(DefaultAbilityContainer.STRENGTH, -1)
			, new DefaultAbilityModifier(DefaultAbilityContainer.DEXTERITY, -1)
			, new DefaultAbilityModifier(DefaultAbilityContainer.CONSTITUTION, -1)
			, new DefaultAbilityModifier(DefaultAbilityContainer.INTELLIGENCE, 1)
			, new DefaultAbilityModifier(DefaultAbilityContainer.WISDOM, 1)
			, new DefaultAbilityModifier(DefaultAbilityContainer.CHARISMA, 1)
		}));
	public static final AgeCategory OLD = new DefaultAgeCategory("Old", Arrays.asList(new AbilityModifier[] {
			new DefaultAbilityModifier(DefaultAbilityContainer.STRENGTH, -2)
			, new DefaultAbilityModifier(DefaultAbilityContainer.DEXTERITY, -2)
			, new DefaultAbilityModifier(DefaultAbilityContainer.CONSTITUTION, -2)
			, new DefaultAbilityModifier(DefaultAbilityContainer.INTELLIGENCE, 1)
			, new DefaultAbilityModifier(DefaultAbilityContainer.WISDOM, 1)
			, new DefaultAbilityModifier(DefaultAbilityContainer.CHARISMA, 1)
		}));
	public static final AgeCategory VENERABLE = new DefaultAgeCategory("Venerable", Arrays.asList(new AbilityModifier[] {
			new DefaultAbilityModifier(DefaultAbilityContainer.STRENGTH, -3)
			, new DefaultAbilityModifier(DefaultAbilityContainer.DEXTERITY, -3)
			, new DefaultAbilityModifier(DefaultAbilityContainer.CONSTITUTION, -3)
			, new DefaultAbilityModifier(DefaultAbilityContainer.INTELLIGENCE, 1)
			, new DefaultAbilityModifier(DefaultAbilityContainer.WISDOM, 1)
			, new DefaultAbilityModifier(DefaultAbilityContainer.CHARISMA, 1)
		}));
	
	
	private String name;
	private Collection abilityModifiers = new ArrayList();

	public DefaultAgeCategory(String name, Collection abilityModifiers) {
		this.name = name;
		this.abilityModifiers = abilityModifiers;
	}
	
	public String getName() {
		return name;
	}
	
	public Collection getAbilityModifiers() {
		return abilityModifiers;
	}
}
