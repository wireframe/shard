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

import junit.framework.TestCase;

import org.easymock.MockControl;

import com.codecrate.shard.DefaultModifierType;
import com.codecrate.shard.ModifierType;

public class DefaultAbilityScoreTest extends TestCase {
	public void testBonusCanGoNegative() {
		DefaultAbilityScore ability = new DefaultAbilityScore(DefaultAbility.STRENGTH, 1, new AbilityScoreDao());
		assertEquals(-4, ability.getBonus());
	}
	
	public void testListenersFiredWhenModifierAdded() {
	    MockControl mockListener = MockControl.createControl(AbilityScoreListener.class);
	    AbilityScoreListener listener = (AbilityScoreListener) mockListener.getMock();
	    listener.onModify();
	    mockListener.setVoidCallable();
	    mockListener.replay();
	    
	    ModifierType type = new DefaultModifierType("type", false);
	    AbilityScoreModifier modifier = new DefaultAbilityScoreModifier(type, DefaultAbility.CHARISMA, 2);
	    DefaultAbilityScore ability = new DefaultAbilityScore(DefaultAbility.CHARISMA, 10, new AbilityScoreDao());
	    ability.addListener(listener);
	    ability.addModifier(modifier);
	    
	    mockListener.verify();
	}
	
	public void testListenersFiredWhenModifierRemoved() {
	    MockControl mockListener = MockControl.createControl(AbilityScoreListener.class);
	    AbilityScoreListener listener = (AbilityScoreListener) mockListener.getMock();
	    listener.onModify();
	    mockListener.setVoidCallable();
	    mockListener.replay();
	    
	    ModifierType type = new DefaultModifierType("type", false);
	    AbilityScoreModifier modifier = new DefaultAbilityScoreModifier(type, DefaultAbility.CHARISMA, 2);
	    DefaultAbilityScore ability = new DefaultAbilityScore(DefaultAbility.CHARISMA, 10, new AbilityScoreDao());
	    ability.addListener(listener);
	    ability.removeModifier(modifier);
	    
	    mockListener.verify();
	}
}
