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

/**
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
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
