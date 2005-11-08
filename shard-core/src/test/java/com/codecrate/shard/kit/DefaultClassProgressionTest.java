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
package com.codecrate.shard.kit;

import java.util.Arrays;

import junit.framework.TestCase;

import org.easymock.MockControl;

public class DefaultClassProgressionTest extends TestCase {

    public void testGetLevelDoesNotAssumeOrder() {
		MockControl mockLevel1 = MockControl.createControl(ClassLevel.class);
		ClassLevel level1 = (ClassLevel) mockLevel1.getMock();
		level1.getLevel();
		mockLevel1.setReturnValue(1);
		mockLevel1.replay();

		MockControl mockLevel2 = MockControl.createControl(ClassLevel.class);
		ClassLevel level2 = (ClassLevel) mockLevel2.getMock();
		level2.getLevel();
		mockLevel2.setReturnValue(2);
		mockLevel2.replay();

        DefaultClassProgression progression = new DefaultClassProgression(Arrays.asList(new ClassLevel[] {level2, level1}));
        assertSame(level2, progression.getClassLevel(2));
    }
}
