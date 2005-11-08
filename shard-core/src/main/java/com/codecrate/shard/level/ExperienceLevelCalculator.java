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
package com.codecrate.shard.level;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Calculator to compute experience needed for a level.
 * 
 * NOTE: I think there's a way to do this without recursion, but my Calculus 
 * skills have gone to crap.
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class ExperienceLevelCalculator implements LevelCalculator {
    private static final Log LOG = LogFactory.getLog(ExperienceLevelCalculator.class);
    
    public int calculateValue(int level) {
        if (hasPreviousLevel(level)) {
            int value = (1000 * previousLevel(level)) + calculateValue(previousLevel(level));
            LOG.debug("Calculated value for level " + level + " is: " + value);
            return value;
        }
        return 0;
    }        
    
    private boolean hasPreviousLevel(int level) {
        if (1 < level) {
            return true;
        }
        return false;
    }
    
    private int previousLevel(int level) {
        return level - 1;
    }
}
