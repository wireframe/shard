package com.codecrate.shard.equipment;

import com.codecrate.shard.dice.Dice;

public interface Weapon extends Item {

    Dice getDamageSmall();
    
    Dice getDamageMedium();
}
