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
package com.codecrate.shard.race;

import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.codecrate.shard.character.AgeCategory;
import com.codecrate.shard.character.CummulativeAgeCategory;
import com.codecrate.shard.dice.Dice;

/**
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class AgeCategorization {
    private static final Log LOG = LogFactory.getLog(AgeCategorization.class);
    
    private Map categories = new TreeMap();
    private Dice maxAgeDice;

    /**
     * hibernate constructor
     */
    private AgeCategorization() {
    }

    public AgeCategorization(Dice maxAgeDice) {
        this.maxAgeDice = maxAgeDice;
    }

    /**
     * gets the dice to roll for determining max age.
     * max age is determined for each character with the formula:
     * character_max_age = racial.venerable_age + random(racial.max_age_dice)
     * @return
     */
    public Dice getMaxAgeDice() {
        return maxAgeDice;
    }

    public AgeCategory getCategory(int age) {
        int nearestAge = getNearestCategoryAge(age);
        AgeCategory category = (AgeCategory) categories.get(new Integer(nearestAge));
        if (null == category) {
            LOG.info("Unable to find age category for age " + age);
        }
        return CummulativeAgeCategory.ADULT;
    }
    
    private int getNearestCategoryAge(int age) {
        int nearestAge = 0;
        return nearestAge;
    }

    public void addAgeCategory(int age, AgeCategory category) {
        categories.put(new Integer(age), category);
    }
}
