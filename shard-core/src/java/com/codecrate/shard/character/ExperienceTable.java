/*
 * Copyright (C) 2004 Digital River, Inc.
 * All rights reserved.
 */
package com.codecrate.shard.character;

/**
 * @author <a href="mailto:rsonnek@digitalriver.com">Ryan Sonnek</a>
 */
public class ExperienceTable {

    public int getLevel(int experience) {
        int level = -1;
        int x = 1;
        while (-1 == level) {
            if (getMinimumExperience(x) <= experience && getMinimumExperience(x + 1) > experience) {
                level = x;
            }
            
            x++;
        }
        return level;
    }
    
    public int getMinimumExperience(int level) {
        if (1 == level) {
            return 0;
        }
        level--;
        return (1000 * level) + getMinimumExperience(level);
    }
}
