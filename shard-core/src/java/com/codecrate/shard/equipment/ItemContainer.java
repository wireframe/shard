package com.codecrate.shard.equipment;

public interface ItemContainer {

	boolean canAdd(Item equipment);
	
	void add(Item equipment);
	
	void remove(Item equipment);
}
