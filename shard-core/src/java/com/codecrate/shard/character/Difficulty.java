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

import java.math.BigDecimal;

public class Difficulty {
    public static final Difficulty OVERPOWERING = new Difficulty("Overpowering", 
            new BigDecimal(".05"));
    public static final Difficulty VERY_DIFFICULT = new Difficulty("Very Difficult", 
            new BigDecimal(".15"));
    public static final Difficulty CHALLENGING = new Difficulty("Challenging", 
            new BigDecimal(".5"));
    public static final Difficulty VERY_EASY = new Difficulty("Very Easy", 
            new BigDecimal(0));
    public static final Difficulty EASY = new Difficulty("Easy", 
            new BigDecimal(".1"));
    public static final Difficulty UNBEATABLE = new Difficulty("Unbeatable", 
            new BigDecimal(0));
    
    private final String name;
    private final BigDecimal percentOfEncounters;

    public Difficulty(String name, BigDecimal percentOfEncounters) {
        this.name = name;
        this.percentOfEncounters = percentOfEncounters;
    }
    
    public String getName() {
        return name;
    }
    public BigDecimal getPercentOfEncounters() {
        return percentOfEncounters;
    }
}
