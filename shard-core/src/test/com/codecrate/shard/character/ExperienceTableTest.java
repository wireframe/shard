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

import junit.framework.TestCase;

public class ExperienceTableTest extends TestCase {

    public void testExperienceMinimumCalculatedCorrectly() {
        ExperienceTable tracker = new ExperienceTable();
        assertEquals(0, tracker.getMinimumExperience(1));
        assertEquals(1000, tracker.getMinimumExperience(2));
        assertEquals(190000, tracker.getMinimumExperience(20));
    }
    
    public void testGetLevel() {
        ExperienceTable tracker = new ExperienceTable();
        assertEquals(1, tracker.getLevel(0));
        assertEquals(2, tracker.getLevel(1000));
        assertEquals(20, tracker.getLevel(190000));
    }
}
