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
import java.util.Collection;

import junit.framework.TestCase;

import org.easymock.MockControl;

import com.codecrate.shard.kit.CharacterClass;
import com.codecrate.shard.kit.ClassLevel;
import com.codecrate.shard.kit.DefaultClassLevel;

public class DefaultCharacterProgressionTest extends TestCase {

    public void testGetClassesReturnsAllUsedClasses() {
		MockControl mockClass2 = MockControl.createControl(CharacterClass.class);
		CharacterClass fighter = (CharacterClass) mockClass2.getMock();
		mockClass2.replay();
		
		MockControl mockClass = MockControl.createControl(CharacterClass.class);
		CharacterClass ranger = (CharacterClass) mockClass.getMock();
		mockClass.replay();

    	ClassLevel rangerLevel1 = new DefaultClassLevel(1, ranger, 1, 1, 1, 1);
    	ClassLevel fighterLevel1 = new DefaultClassLevel(1, fighter, 1, 1, 1, 1);
    	
    	CharacterLevel characterLevel1 = new DefaultCharacterLevel(1, 3, rangerLevel1);
    	CharacterLevel characterLevel2 = new DefaultCharacterLevel(2, 4, fighterLevel1);
    	
    	Collection levels = new ArrayList();
    	levels.add(characterLevel1);
    	levels.add(characterLevel2);
    	
    	DefaultCharacterProgression progression = new DefaultCharacterProgression(levels);
    	assertEquals(2, progression.getClasses().size());
    	assertSame(ranger, progression.getClasses().iterator().next());
    }

    public void testGetMaxLevelReturnsHighestLevel() {
		MockControl mockClass = MockControl.createControl(CharacterClass.class);
		CharacterClass ranger = (CharacterClass) mockClass.getMock();
		mockClass.replay();

    	ClassLevel rangerLevel1 = new DefaultClassLevel(1, ranger, 1, 1, 1, 1);
    	ClassLevel rangerLevel2 = new DefaultClassLevel(2, ranger, 1, 1, 1, 1);
    	
    	CharacterLevel characterLevel1 = new DefaultCharacterLevel(1, 2, rangerLevel1);
    	CharacterLevel characterLevel2 = new DefaultCharacterLevel(1, 1, rangerLevel2);
    	
    	Collection levels = new ArrayList();
    	levels.add(characterLevel1);
    	levels.add(characterLevel2);
    	
    	DefaultCharacterProgression progression = new DefaultCharacterProgression(levels);
    	assertSame(rangerLevel2, progression.getMaxClassLevel(ranger));
    }
    
    public void testGetMaxLevelReturnsNullForUnusedClass() {
		MockControl mockClass = MockControl.createControl(CharacterClass.class);
		CharacterClass ranger = (CharacterClass) mockClass.getMock();
		mockClass.replay();

		MockControl mockClass2 = MockControl.createControl(CharacterClass.class);
		CharacterClass fighter = (CharacterClass) mockClass2.getMock();
		mockClass2.replay();
		
    	ClassLevel rangerLevel1 = new DefaultClassLevel(1, ranger, 1, 1, 1, 1);
    	CharacterLevel characterLevel1 = new DefaultCharacterLevel(1, 5, rangerLevel1);
    	
    	Collection levels = new ArrayList();
    	levels.add(characterLevel1);
    	
    	DefaultCharacterProgression progression = new DefaultCharacterProgression(levels);
    	assertNull(progression.getMaxClassLevel(fighter));
    }
}
