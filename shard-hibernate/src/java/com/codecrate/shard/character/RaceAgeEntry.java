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

import com.codecrate.shard.Identifiable;
import com.codecrate.shard.race.Race;


/**
 * persistent object for saving age categories for a race.
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class RaceAgeEntry implements Identifiable{

    private String id;
    private Race race;
    private int ageAdult;
    private int ageMiddleAge;
    private int ageOld;
    private int ageVenerable;

    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public Race getRace() {
        return race;
    }
    
    public void setRace(Race name) {
        this.race = name;
    }
    
    public int getAgeAdult() {
        return ageAdult;
    }
    /**
     * @param ageAdult The ageAdult to set.
     */
    public void setAgeAdult(int ageAdult) {
        this.ageAdult = ageAdult;
    }
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
