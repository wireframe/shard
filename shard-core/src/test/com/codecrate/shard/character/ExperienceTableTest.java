/*
 * Copyright (C) 2004 Digital River, Inc.
 * All rights reserved.
 */
package com.codecrate.shard.character;

import junit.framework.TestCase;

/**
 * @author <a href="mailto:rsonnek@digitalriver.com">Ryan Sonnek</a>
 */
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
