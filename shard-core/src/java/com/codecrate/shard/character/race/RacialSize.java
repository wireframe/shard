package com.codecrate.shard.character.race;

import com.codecrate.shard.character.armorclass.ArmorClassModifier;

public interface RacialSize extends ArmorClassModifier {
    int getBaseAttackBonusModifier();
    
    //small characters 3/4 max weight of medium.
    //float getMaxWeightModifier();
    
    //small characters 2/3 max weight of medium.
    //int getMovementRateModifier();
}
