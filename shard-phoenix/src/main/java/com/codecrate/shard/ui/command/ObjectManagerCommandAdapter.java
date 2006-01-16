/*
 * Copyright 2004 codecrate consulting
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.codecrate.shard.ui.command;

import java.io.File;
import java.util.Collection;

/**
 * adapter to convert gui actions to the business layer.
 */
public interface ObjectManagerCommandAdapter {

	String getDeleteMessagePropertyName();

    void setDeleteMessagePropertyName(String deleteMessagePropertyName);

    void deleteObject(Object o);

    void updateObject(Object object);

    void importObjects(File file);

    Object createObject();

    void saveObject(Object object);

    Collection searchObjects(String query);

    Collection getObjects();

    String[] getColumnNames();

    Collection getSupportedImportFileExtensions();

    boolean isDirectoryImportSupported();
}