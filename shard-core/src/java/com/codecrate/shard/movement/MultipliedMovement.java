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
