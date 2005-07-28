package com.codecrate.shard.ui.command;

public interface DeleteCommand {
	String getDeleteMessagePropertyName();
	
	void deleteObject(Object o);
}
