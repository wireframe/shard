package com.codecrate.shard.ui.command;


public interface CommandAdapter extends ViewObjectsCommand, NewCommand,
		PropertiesCommand, DeleteCommand, SearchObjectsCommand {

	String getDeleteMessagePropertyName();

	void setDeleteMessagePropertyName(String deleteMessagePropertyName);

}