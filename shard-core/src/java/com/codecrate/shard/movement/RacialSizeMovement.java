package com.codecrate.shard.movement;

import com.codecrate.shard.race.DefaultRacialSize;
import com.codecrate.shard.race.RacialSize;

/**
 * Movement rate based upon the creatures racial size. 
 * This movement rate should be used as a guide or to generate 
 * "default" rates depending on character size.   It should not be 
 * used as an explicit rule as there are several exceptions.  
 * ex: dwarf is medium size, but movement rate = 20.
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class RacialSizeMovement implements Movement {
    private int rate;
    
    public RacialSizeMovement(RacialSize size) {
        if (DefaultRacialSize.SMALL.equals(size)) {
            rate = 20;
        } else if (DefaultRacialSize.MEDIUM.equals(size)) {
            rate = 30;
        } else {
            throw new IllegalArgumentException("Unknown movement rate for racial size: " + size);
        }
    }

    public int getBaseMovementRate() {
        return rate;
    }
}
