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
package com.codecrate.shard.ability;

import com.codecrate.shard.Identifiable;

/**
 * persisted class for storing point cost for an ability score.
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class PointCostValue implements Identifiable {
    private int abilityScore;
    private int pointCost;
    
    public String getId() {
        return Integer.toString(abilityScore);
    }
    
    public void setId(String id) {
        abilityScore = Integer.parseInt(id);
    }

    public int getAbilityScore() {
        return abilityScore;
    }

    public int getPointCost() {
        return pointCost;
    }
    /**
     * @param abilityScore The abilityScore to set.
     */
    public void setAbilityScore(int abilityScore) {
        this.abilityScore = abilityScore;
    }
    /**
     * @param pointCost The pointCost to set.
     */
    public void setPointCost(int pointCost) {
        this.pointCost = pointCost;
    }
}
