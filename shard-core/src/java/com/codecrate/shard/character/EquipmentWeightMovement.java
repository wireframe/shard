package com.codecrate.shard.character;

import com.codecrate.shard.character.race.RacialSize;
import com.codecrate.shard.equipment.ItemContainer;

public class EquipmentWeightMovement implements Movement {

	public EquipmentWeightMovement(ItemContainer container, RacialSize size) {
		
	}
	
	public int getBaseMovementRate() {
		return 0;
	}
}
