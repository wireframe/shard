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

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import org.easymock.MockControl;

import com.codecrate.shard.DefaultModifier;
import com.codecrate.shard.DefaultModifierType;
import com.codecrate.shard.ModifierType;

public class DefaultAbilityScoreContainerTest extends TestCase {

	public void testGetAbilityReturnsSameObject() {
		AbilityScore ability = new DefaultAbilityScore(DefaultAbility.STRENGTH, 10, new AbilityScoreDao());
		Map abilities = new HashMap();
		abilities.put(DefaultAbility.STRENGTH, ability);
		
		DefaultAbilityScoreContainer container = new DefaultAbilityScoreContainer(abilities);
		assertSame(ability, container.getStrength());
		assertSame(ability, container.getAbilityScore(DefaultAbility.STRENGTH));
	}
	
	public void testHasAbilityFailsForNoAbility() {
		Map abilities = new HashMap();
		DefaultAbilityScoreContainer container = new DefaultAbilityScoreContainer(abilities);
		assertFalse(container.hasAbilityScore(DefaultAbility.STRENGTH));
	}
	
	public void testHasAbilitySucceedsWhenAbilityExists() {
		Map abilities = new HashMap();
		abilities.put(DefaultAbility.STRENGTH, new DefaultAbilityScore(DefaultAbility.STRENGTH, 10, new AbilityScoreDao()));
		DefaultAbilityScoreContainer container = new DefaultAbilityScoreContainer(abilities);
		assertTrue(container.hasAbilityScore(DefaultAbility.STRENGTH));
	}

	public void testTotalPointScoreIsSumOfAbilityPointScores() {
	    MockControl mockScore = MockControl.createControl(AbilityScore.class);
	    AbilityScore score = (AbilityScore) mockScore.getMock();
	    score.getPointCost();
	    mockScore.setReturnValue(1);
	    mockScore.replay();
	    
		Map abilities = new HashMap();
		abilities.put(DefaultAbility.STRENGTH, score);
		
		DefaultAbilityScoreContainer container = new DefaultAbilityScoreContainer(abilities);
		int totalPointScore = container.getTotalPointScore();
		assertEquals(1, totalPointScore);
	}

	public void testAbilityScoresReturned() {
		AbilityScore ability = new DefaultAbilityScore(DefaultAbility.STRENGTH, 10, new AbilityScoreDao());
		Map abilities = new HashMap();
		abilities.put(DefaultAbility.STRENGTH, ability);
		DefaultAbilityScoreContainer container = new DefaultAbilityScoreContainer(abilities);

		assertEquals(1, container.getAbilityScores().size());
	}
	
	public void testAddModifierAttachesModifierToScore() {
		AbilityScore ability = new DefaultAbilityScore(DefaultAbility.STRENGTH, 10, new AbilityScoreDao());
		Map abilities = new HashMap();
		abilities.put(DefaultAbility.STRENGTH, ability);
	    DefaultAbilityScoreContainer container = new DefaultAbilityScoreContainer(abilities);
	    ModifierType type = new DefaultModifierType("test", false);
	    container.addAbilityScoreModifier(new AbilityScoreModifier(DefaultAbility.STRENGTH, new DefaultModifier(type, 8)));

	    assertEquals(18, ability.getModifiedValue());
	}
	
	public void testModifierNotUsedWithoutAbilityScore() {
		Map abilities = new HashMap();
	    DefaultAbilityScoreContainer container = new DefaultAbilityScoreContainer(abilities);
	    ModifierType type = new DefaultModifierType("test", false);
	    container.addAbilityScoreModifier(new AbilityScoreModifier(DefaultAbility.STRENGTH, new DefaultModifier(type, 8)));
	    assertFalse(container.hasAbilityScore(DefaultAbility.STRENGTH));
	}
	
	
	public void testRemoveModifierModifierToScore() {
		AbilityScore ability = new DefaultAbilityScore(DefaultAbility.STRENGTH, 10, new AbilityScoreDao());
		Map abilities = new HashMap();
		abilities.put(DefaultAbility.STRENGTH, ability);
	    DefaultAbilityScoreContainer container = new DefaultAbilityScoreContainer(abilities);
	    ModifierType type = new DefaultModifierType("test", false);
	    AbilityScoreModifier modifier = new AbilityScoreModifier(DefaultAbility.STRENGTH, new DefaultModifier(type, 8));
        container.addAbilityScoreModifier(modifier);
	    container.removeAbilityScoreModifier(modifier);

	    assertEquals(10, ability.getModifiedValue());
	}
	
	public void testModifierNotRemovedWithoutAbilityScore() {
		Map abilities = new HashMap();
	    DefaultAbilityScoreContainer container = new DefaultAbilityScoreContainer(abilities);
	    ModifierType type = new DefaultModifierType("test", false);
	    container.removeAbilityScoreModifier(new AbilityScoreModifier(DefaultAbility.STRENGTH, new DefaultModifier(type, 8)));
	    assertFalse(container.hasAbilityScore(DefaultAbility.STRENGTH));
	}
}
