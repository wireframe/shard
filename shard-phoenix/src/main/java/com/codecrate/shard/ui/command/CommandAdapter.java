package com.codecrate.shard.ui.command;


public interface CommandAdapter extends ViewObjectsCommand, NewObjectCommand,
		EditObjectPropertiesCommand, DeleteObjectCommand, SearchObjectsCommand, ImportObjectsCommand {

	String getDeleteMessagePropertyName();

	void setDeleteMessagePropertyName(String deleteMessagePropertyName);

}