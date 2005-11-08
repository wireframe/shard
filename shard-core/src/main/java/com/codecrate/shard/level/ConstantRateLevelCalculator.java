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

import java.math.BigDecimal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Calculate values that change at a constant rate per level.
 * can be used to compute increases in base attack bonuses as levels progress.
 * ex: fighter attack progression.
 */
public class ConstantRateLevelCalculator implements LevelCalculator {
    private static final Log LOG = LogFactory.getLog(ConstantRateLevelCalculator.class);
    
    private final BigDecimal rate;
    private final int initialValue;

    public ConstantRateLevelCalculator(int initialValue, BigDecimal rate) {
        this.initialValue = initialValue;
        this.rate = rate;
    }
    
    public int calculateValue(int level) {
        BigDecimal value = rate.multiply(new BigDecimal(level - 1));
        value = value.add(new BigDecimal(initialValue));
        value = value.setScale(0, BigDecimal.ROUND_HALF_UP);
        LOG.debug("Computed value for level " + level + " is: " + value);
        
        return value.intValue();
    }
}
