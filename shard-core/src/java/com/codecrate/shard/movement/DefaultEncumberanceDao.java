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

public class DefaultEncumberanceDao implements EncumberanceDao {
    private static final Log LOG = LogFactory.getLog(DefaultEncumberanceDao.class);
    
    public Encumberance getEncumberance(AbilityScoreContainer abilities, ItemContainer inventory, RacialSize size) {
        if (!abilities.hasAbilityScore(DefaultAbility.STRENGTH)) {
            LOG.warn("No Strength ability score found.  Encumberance can't be calculated.");
            return null;
        }
        AbilityScore strength = abilities.getAbilityScore(DefaultAbility.STRENGTH);
        int score = strength.getModifiedValue();
        int effectiveWeight = inventory.getTotalWeight().divide(size.getEncumberanceMultiplier(), 0).intValue();
        
        if (1 == score) {
            if (effectiveWeight <= 3) {
                return DefaultEncumberance.LIGHT;
            }
            if (effectiveWeight <= 6) {
                return DefaultEncumberance.MEDIUM;
            }
            if (effectiveWeight <= 10) {
                return DefaultEncumberance.HEAVY;
            }
        }
        if (2 == score) {
            if (effectiveWeight <= 6) {
                return DefaultEncumberance.LIGHT;
            }
            if (effectiveWeight <= 13) {
                return DefaultEncumberance.MEDIUM;
            }
            if (effectiveWeight <= 20) {
                return DefaultEncumberance.HEAVY;
            }
        }
        if (3 == score) {
            if (effectiveWeight <= 10) {
                return DefaultEncumberance.LIGHT;
            }
            if (effectiveWeight <= 20) {
                return DefaultEncumberance.MEDIUM;
            }
            if (effectiveWeight <= 30) {
                return DefaultEncumberance.HEAVY;
            }
        }
        if (4 == score) {
            if (effectiveWeight <= 13) {
                return DefaultEncumberance.LIGHT;
            }
            if (effectiveWeight <= 26) {
                return DefaultEncumberance.MEDIUM;
            }
            if (effectiveWeight <= 40) {
                return DefaultEncumberance.HEAVY;
            }
        }
        if (5 == score) {
            if (effectiveWeight <= 16) {
                return DefaultEncumberance.LIGHT;
            }
            if (effectiveWeight <= 33) {
                return DefaultEncumberance.MEDIUM;
            }
            if (effectiveWeight <= 50) {
                return DefaultEncumberance.HEAVY;
            }
        }
        if (6 == score) {
            if (effectiveWeight <= 20) {
                return DefaultEncumberance.LIGHT;
            }
            if (effectiveWeight <= 40) {
                return DefaultEncumberance.MEDIUM;
            }
            if (effectiveWeight <= 60) {
                return DefaultEncumberance.HEAVY;
            }
        }
        if (7 == score) {
            if (effectiveWeight <= 23) {
                return DefaultEncumberance.LIGHT;
            }
            if (effectiveWeight <= 46) {
                return DefaultEncumberance.MEDIUM;
            }
            if (effectiveWeight <= 70) {
                return DefaultEncumberance.HEAVY;
            }
        }
        if (8 == score) {
            if (effectiveWeight <= 26) {
                return DefaultEncumberance.LIGHT;
            }
            if (effectiveWeight <= 53) {
                return DefaultEncumberance.MEDIUM;
            }
            if (effectiveWeight <= 80) {
                return DefaultEncumberance.HEAVY;
            }
        }
        if (9 == score) {
            if (effectiveWeight <= 30) {
                return DefaultEncumberance.LIGHT;
            }
            if (effectiveWeight <= 60) {
                return DefaultEncumberance.MEDIUM;
            }
            if (effectiveWeight <= 90) {
                return DefaultEncumberance.HEAVY;
            }
        }
        if (10 == score) {
            if (effectiveWeight <= 33) {
                return DefaultEncumberance.LIGHT;
            }
            if (effectiveWeight <= 66) {
                return DefaultEncumberance.MEDIUM;
            }
            if (effectiveWeight <= 100) {
                return DefaultEncumberance.HEAVY;
            }
        }
        if (11 == score) {
            if (effectiveWeight <= 38) {
                return DefaultEncumberance.LIGHT;
            }
            if (effectiveWeight <= 76) {
                return DefaultEncumberance.MEDIUM;
            }
            if (effectiveWeight <= 115) {
                return DefaultEncumberance.HEAVY;
            }
        }
        if (12 == score) {
            if (effectiveWeight <= 43) {
                return DefaultEncumberance.LIGHT;
            }
            if (effectiveWeight <= 86) {
                return DefaultEncumberance.MEDIUM;
            }
            if (effectiveWeight <= 130) {
                return DefaultEncumberance.HEAVY;
            }
        }
        if (13 == score) {
            if (effectiveWeight <= 50) {
                return DefaultEncumberance.LIGHT;
            }
            if (effectiveWeight <= 100) {
                return DefaultEncumberance.MEDIUM;
            }
            if (effectiveWeight <= 150) {
                return DefaultEncumberance.HEAVY;
            }
        }
        if (14 == score) {
            if (effectiveWeight <= 58) {
                return DefaultEncumberance.LIGHT;
            }
            if (effectiveWeight <= 116) {
                return DefaultEncumberance.MEDIUM;
            }
            if (effectiveWeight <= 175) {
                return DefaultEncumberance.HEAVY;
            }
        }
        if (15 == score) {
            if (effectiveWeight <= 66) {
                return DefaultEncumberance.LIGHT;
            }
            if (effectiveWeight <= 133) {
                return DefaultEncumberance.MEDIUM;
            }
            if (effectiveWeight <= 200) {
                return DefaultEncumberance.HEAVY;
            }
        }
        if (16 == score) {
            if (effectiveWeight <= 76) {
                return DefaultEncumberance.LIGHT;
            }
            if (effectiveWeight <= 153) {
                return DefaultEncumberance.MEDIUM;
            }
            if (effectiveWeight <= 230) {
                return DefaultEncumberance.HEAVY;
            }
        }
        if (17 == score) {
            if (effectiveWeight <= 86) {
                return DefaultEncumberance.LIGHT;
            }
            if (effectiveWeight <= 173) {
                return DefaultEncumberance.MEDIUM;
            }
            if (effectiveWeight <= 260) {
                return DefaultEncumberance.HEAVY;
            }
        }
        if (18 == score) {
            if (effectiveWeight <= 100) {
                return DefaultEncumberance.LIGHT;
            }
            if (effectiveWeight <= 200) {
                return DefaultEncumberance.MEDIUM;
            }
            if (effectiveWeight <= 300) {
                return DefaultEncumberance.HEAVY;
            }
        }
        
        
        LOG.warn("No encumberance found for strength score " + score + " and effective weight " + effectiveWeight);
        return null;
    }
}
