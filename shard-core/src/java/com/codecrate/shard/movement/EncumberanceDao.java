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
package com.codecrate.shard.movement;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.codecrate.shard.ability.AbilityScore;
import com.codecrate.shard.ability.AbilityScoreContainer;
import com.codecrate.shard.ability.DefaultAbility;
import com.codecrate.shard.equipment.ItemContainer;
import com.codecrate.shard.race.RacialSize;

public class EncumberanceDao {
    private static final Log LOG = LogFactory.getLog(EncumberanceDao.class);
    
    public Encumberance getEncumberance(AbilityScoreContainer abilities, ItemContainer inventory, RacialSize size) {
        if (!abilities.hasAbilityScore(DefaultAbility.STRENGTH)) {
            LOG.warn("No Strength ability score found.  Encumberance can't be calculated.");
            return null;
        }
        AbilityScore strength = abilities.getAbilityScore(DefaultAbility.STRENGTH);
        int score = strength.getScore();
        int effectiveWeight = inventory.getTotalWeight().divide(size.getEncumberanceMultiplier(), 0).intValue();
        
        return null;
    }
}
