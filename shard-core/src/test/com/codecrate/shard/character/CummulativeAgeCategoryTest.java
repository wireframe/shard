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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;

import org.easymock.MockControl;

import com.codecrate.shard.DefaultModifierType;
import com.codecrate.shard.ModifierType;
import com.codecrate.shard.ability.AbilityScoreModifier;
import com.codecrate.shard.ability.DefaultAbility;
import com.codecrate.shard.ability.DefaultAbilityScoreModifier;

/**
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek </a>
 */
public class CummulativeAgeCategoryTest extends TestCase {

	public void testPreviousCategoryAbilityModifiersAdded() {
        ModifierType type = new DefaultModifierType("type", true);

		List modifiers = Arrays
		.asList(new AbilityScoreModifier[] { new DefaultAbilityScoreModifier(
				type, DefaultAbility.STRENGTH, 1) });
		MockControl mockAgeCategory = MockControl.createControl(AgeCategory.class);
		AgeCategory previousAgeCategory = (AgeCategory) mockAgeCategory.getMock();
		previousAgeCategory.getAbilityModifiers();
		mockAgeCategory.setReturnValue(modifiers);
		mockAgeCategory.replay();

		CummulativeAgeCategory ageCategory = new CummulativeAgeCategory(
				previousAgeCategory, "name", new ArrayList());
		assertEquals(1, ageCategory.getAbilityModifiers().size());
	}
	
	public void testPreviousCategoryCanBeNull() {
		CummulativeAgeCategory ageCategory = new CummulativeAgeCategory(
				null, "name", new ArrayList());
		assertEquals(0, ageCategory.getAbilityModifiers().size());
	}
}