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

/**
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 * 
 * @hibernate.class 
 *  table="SHA_ENCUMBERANCE_ENTRY"
 */
public class EncumberanceEntry {
    private int abilityScore;
    private int weightLight;
    private int weightMedium;
    private int weightHeavy;
    
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
     * @return Returns the weightHeavy.
     * 
     * @hibernate.property
     *  column="WEIGHT_HEAVY"
     *  length="4"
     *  not-null="true"
     */
    public int getWeightHeavy() {
        return weightHeavy;
    }
    /**
     * @param weightHeavy The weightHeavy to set.
     */
    public void setWeightHeavy(int weightHeavy) {
        this.weightHeavy = weightHeavy;
    }
    /**
     * @return Returns the weightLight.
     * 
     * @hibernate.property
     *  column="WEIGHT_LIGHT"
     *  length="4"
     *  not-null="true"
     */
    public int getWeightLight() {
        return weightLight;
    }
    /**
     * @param weightLight The weightLight to set.
     */
    public void setWeightLight(int weightLight) {
        this.weightLight = weightLight;
    }
    /**
     * @return Returns the weightMedium.
     * 
     * @hibernate.property
     *  column="WEIGHT_MEDIUM"
     *  length="4"
     *  not-null="true"
     */
    public int getWeightMedium() {
        return weightMedium;
    }
    /**
     * @param weightMedium The weightMedium to set.
     */
    public void setWeightMedium(int weightMedium) {
        this.weightMedium = weightMedium;
    }
    /**
     * @param abilityScore The abilityScore to set.
     */
    public void setAbilityScore(int abilityScore) {
        this.abilityScore = abilityScore;
    }
}
