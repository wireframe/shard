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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.codecrate.shard.race.DefaultRace;
import com.codecrate.shard.race.Race;

/**
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 *
 * @see http://d20srd.org/srd/description.htm#vitalStatistics
 */
public class AgeCategoryDao {
    private static final Log LOG = LogFactory.getLog(AgeCategoryDao.class);
    
    public AgeCategory getAgeCategory(int age, Race race) {
        if (DefaultRace.HUMAN.equals(race)) {
            if (15 <= age && age < 35) {
                return CummulativeAgeCategory.ADULT;
            }
            if (35 <= age && age < 53) {
                return CummulativeAgeCategory.MIDDLE_AGE;
            }
            if (53 <= age && age < 70) {
                return CummulativeAgeCategory.OLD;
            }
            if (70 <= age) {
                return CummulativeAgeCategory.VENERABLE;
            }
        }
        if (DefaultRace.DWARF.equals(race)) {
            if (40 <= age && age < 125) {
                return CummulativeAgeCategory.ADULT;
            }
            if (125 <= age && age < 188) {
                return CummulativeAgeCategory.MIDDLE_AGE;
            }
            if (188 <= age && age < 250) {
                return CummulativeAgeCategory.OLD;
            }
            if (250 <= age) {
                return CummulativeAgeCategory.VENERABLE;
            }
        }
        if (DefaultRace.ELF.equals(race)) {
            if (110 <= age && age < 175) {
                return CummulativeAgeCategory.ADULT;
            }
            if (175 <= age && age < 263) {
                return CummulativeAgeCategory.MIDDLE_AGE;
            }
            if (263 <= age && age < 350) {
                return CummulativeAgeCategory.OLD;
            }
            if (350 <= age) {
                return CummulativeAgeCategory.VENERABLE;
            }
        }
        if (DefaultRace.GNOME.equals(race)) {
            if (40 <= age && age < 100) {
                return CummulativeAgeCategory.ADULT;
            }
            if (100 <= age && age < 150) {
                return CummulativeAgeCategory.MIDDLE_AGE;
            }
            if (150 <= age && age < 200) {
                return CummulativeAgeCategory.OLD;
            }
            if (200 <= age) {
                return CummulativeAgeCategory.VENERABLE;
            }
        }
        if (DefaultRace.HALF_ELF.equals(race)) {
            if (20 <= age && age < 62) {
                return CummulativeAgeCategory.ADULT;
            }
            if (62 <= age && age < 93) {
                return CummulativeAgeCategory.MIDDLE_AGE;
            }
            if (93 <= age && age < 125) {
                return CummulativeAgeCategory.OLD;
            }
            if (125 <= age) {
                return CummulativeAgeCategory.VENERABLE;
            }
        }
        if (DefaultRace.HALF_ORC.equals(race)) {
            if (14 <= age && age < 30) {
                return CummulativeAgeCategory.ADULT;
            }
            if (30 <= age && age < 45) {
                return CummulativeAgeCategory.MIDDLE_AGE;
            }
            if (45 <= age && age < 60) {
                return CummulativeAgeCategory.OLD;
            }
            if (60 <= age) {
                return CummulativeAgeCategory.VENERABLE;
            }
        }
        if (DefaultRace.HALFLING.equals(race)) {
            if (20 <= age && age < 50) {
                return CummulativeAgeCategory.ADULT;
            }
            if (50 <= age && age < 75) {
                return CummulativeAgeCategory.MIDDLE_AGE;
            }
            if (75 <= age && age < 100) {
                return CummulativeAgeCategory.OLD;
            }
            if (100 <= age) {
                return CummulativeAgeCategory.VENERABLE;
            }
        }
        
        LOG.warn("No age category found for race " + race + " and age " + age
                + ".  Returning default age category.");
        return CummulativeAgeCategory.ADULT;
    }
}
