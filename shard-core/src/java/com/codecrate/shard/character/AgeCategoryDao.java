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

import com.codecrate.shard.race.Race;

public class AgeCategoryDao {
    private static final Log LOG = LogFactory.getLog(AgeCategoryDao.class);
    
    public AgeCategory getAgeCategory(int age, Race race) {
    
        LOG.warn("No age category found for race " + race + " and age " + age
                + ".  Returning default age category.");
        return CumulativeAgeCategory.ADULT;
    }
}
