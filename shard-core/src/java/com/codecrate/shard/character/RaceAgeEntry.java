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


/**
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 * 
 * @hibernate.class 
 *  table="SHA_RACE_AGE"
 */
public class RaceAgeEntry {

    private String race;
    private int ageAdult;
    private int ageMiddleAge;
    private int ageOld;
    private int ageVenerable;

    /**
     * 
     * @return
     * 
     * @hibernate.id
     *  column="RACE"
     *  generator-class="uuid.hex"
     */
    public String getRace() {
        return race;
    }
    
    public void setRace(String name) {
        this.race = name;
    }
    
    /**
     * @return Returns the ageAdult.
     * 
     * @hibernate.property
     *  column="AGE_ADULT"
     *  length="4"
     *  not-null="true"
     */
    public int getAgeAdult() {
        return ageAdult;
    }
    /**
     * @param ageAdult The ageAdult to set.
     */
    public void setAgeAdult(int ageAdult) {
        this.ageAdult = ageAdult;
    }
    /**
     * @return Returns the ageMiddleAge.
     * 
     * @hibernate.property
     *  column="AGE_MIDDLE_AGE"
     *  length="4"
     *  not-null="true"
     */
    public int getAgeMiddleAge() {
        return ageMiddleAge;
    }
    /**
     * @param ageMiddleAge The ageMiddleAge to set.
     */
    public void setAgeMiddleAge(int ageMiddleAge) {
        this.ageMiddleAge = ageMiddleAge;
    }
    /**
     * @return Returns the ageOld.
     * 
     * @hibernate.property
     *  column="AGE_OLD"
     *  length="4"
     *  not-null="true"
     */
    public int getAgeOld() {
        return ageOld;
    }
    /**
     * @param ageOld The ageOld to set.
     */
    public void setAgeOld(int ageOld) {
        this.ageOld = ageOld;
    }
    /**
     * @return Returns the ageVenerable.
     * 
     * @hibernate.property
     *  column="AGE_VENERABLE"
     *  length="4"
     *  not-null="true"
     */
    public int getAgeVenerable() {
        return ageVenerable;
    }
    /**
     * @param ageVenerable The ageVenerable to set.
     */
    public void setAgeVenerable(int ageVenerable) {
        this.ageVenerable = ageVenerable;
    }
}
