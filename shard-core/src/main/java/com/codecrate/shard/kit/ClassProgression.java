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

import java.util.Collection;

/**
 * represents the progression of levels for a character class.
 * this is a simple wrapper around the individual class levels.
 *
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public interface ClassProgression {
    Collection getClassLevels();

    int getMaxLevel();

    ClassLevel getClassLevel(int level);

    /**
     * add new class level.
     * @param baseAttackBonus
     * @param fortitudeSave
     * @param reflexSave
     * @param willSave
     */
    void addLevel(int baseAttackBonus, int fortitudeSave, int reflexSave, int willSave);
}