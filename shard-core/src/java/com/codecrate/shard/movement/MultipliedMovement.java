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

import java.math.BigDecimal;

/**
 * Movement rate that is modified by a multiplier.
 * Can be used for modifying movement rate based upon the creatures racial size. 
 * This movement rate should be used as a guide or to generate 
 * "default" rates depending on character size.   It should not be 
 * used as an explicit rule as there are several exceptions.  
 * ex: dwarf is medium size, but movement rate = 20.
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class MultipliedMovement implements Movement {
    
    private final Movement movement;
    private final BigDecimal multiplier;

    public MultipliedMovement(Movement movement, BigDecimal multiplier) {
        this.movement = movement;
        this.multiplier = multiplier;
    }

    public int getBaseMovementRate() {
        return multiplier.multiply(new BigDecimal(movement.getBaseMovementRate())).intValue();
    }
    
    public String toString() {
        return multiplier.toString() + "*" + movement;
    }
}
