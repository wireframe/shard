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

/**
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 * 
 * @hibernate.class 
 *  table="SHA_ABILITY_POINT_COST"
 */
public class PointCostValue {
    private int abilityScore;
    private int pointCost;
    /**
     * @return Returns the abilityScore.
     * 
     * @hibernate.id
     *  column="ABILITY_SCORE"
     *  generator-class="uuid.hex"
     *  length="3"
     */
    public int getAbilityScore() {
        return abilityScore;
    }
    /**
     * @return Returns the pointCost.
     * 
     * @hibernate.property
     *  column="POINT_COST"
     *  length="3"
     *  not-null="true"
     */
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
