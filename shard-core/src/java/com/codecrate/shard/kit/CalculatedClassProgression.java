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

import java.util.ArrayList;
import java.util.Collection;

import com.codecrate.shard.level.LevelCalculator;

public class CalculatedClassProgression implements ClassProgression {

    private DefaultClassProgression delegate;
    
    public CalculatedClassProgression(int maxLevel, CharacterClass kit, LevelCalculator baseAttackCalculator) {
        Collection levels = new ArrayList();
        for (int x = 1; x <= maxLevel; x++) {
            ClassLevel level = new DefaultClassLevel(x, kit, baseAttackCalculator.calculateValue(x), 0, 0, 0);
            levels.add(level);
        }
        
        delegate = new DefaultClassProgression(levels);
    }
    
    public Collection getClassLevels() {
        return delegate.getClassLevels();
    }

    public int getMaxLevel() {
        return delegate.getMaxLevel();
    }

    public ClassLevel getClassLevel(int level) {
        return delegate.getClassLevel(level);
    }
}
