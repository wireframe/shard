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
package com.codecrate.shard;

import junit.framework.TestCase;

import org.easymock.MockControl;

import com.codecrate.shard.Modifier;

public class TypeGroupedModifierTest extends TestCase {

	public void testModifierOfWrongTypeNotUsed() {
	    ModifierType modifierType = new DefaultModifierType("test", false);
	    ModifierType modifierType2 = new DefaultModifierType("test2", false);
	    
		MockControl firstModifierControl = MockControl.createControl(Modifier.class);
		Modifier firstModifier = (Modifier) firstModifierControl.getMock();
		firstModifier.getModifierType();
		firstModifierControl.setReturnValue(modifierType2);
		firstModifier.getModifier();
		firstModifierControl.setReturnValue(5);
		firstModifierControl.replay();

		
		TypeGroupedModifier modifier = new TypeGroupedModifier(modifierType);
		modifier.addModifier(firstModifier);
		assertEquals(0, modifier.getModifiers().size());
	}
}
