/*
 * Copyright (C) 2004 Digital River, Inc.
 * All rights reserved.
 */
package com.codecrate.shard.character;

/**
 * @author <a href="mailto:rsonnek@digitalriver.com">Ryan Sonnek</a>
 */
public class HitPoints {
    private int currentHitPoints;
    private int maxHitPoints;
    private int subdualDamage;
    
    public void heal(int amount) {
        currentHitPoints += amount;
        
        if (currentHitPoints > maxHitPoints) {
            currentHitPoints = maxHitPoints;
        }
    }
    
    public void damage(int amount) {
        boolean beforeDamageAboveHalf = isAboveHalf();
        currentHitPoints -= amount;
        boolean afterDamageAboveHalf = isAboveHalf();
        
        if (currentHitPoints <= 0) {
            //trigger dying
        } else if (beforeDamageAboveHalf && !afterDamageAboveHalf) {
            //trigger flee
        }
    }
    
    private boolean isAboveHalf() {
        return ((currentHitPoints / 2) >= maxHitPoints);
    }
}
