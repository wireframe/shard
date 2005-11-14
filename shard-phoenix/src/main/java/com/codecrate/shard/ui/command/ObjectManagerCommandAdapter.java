package com.codecrate.shard.ui.command;

import java.io.File;
import java.util.Collection;


public interface ObjectManagerCommandAdapter {

	String getDeleteMessagePropertyName();

    void setDeleteMessagePropertyName(String deleteMessagePropertyName);

    void deleteObject(Object o);

    void updateObject(Object object);

    Collection importObjects(File file);

    Object createObject();

    void saveObject(Object object);

    Collection searchObjects(String query);

    Collection getObjects();

    String[] getColumnNames();
}