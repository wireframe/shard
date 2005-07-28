package com.codecrate.shard.ui.command;

public interface NewCommand {
	Object createObject();
	
	void saveObject(Object object);
}
