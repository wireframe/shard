package com.codecrate.shard.ui.command;


public interface CommandAdapter extends ViewObjectsCommand, NewCommand,
		PropertiesCommand, DeleteCommand, SearchObjectsCommand, ImportCommand {

	String getDeleteMessagePropertyName();

	void setDeleteMessagePropertyName(String deleteMessagePropertyName);

}