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
package com.codecrate.shard;

/**
 * Common interface for persistable objects.
 * provides a convenient way for objects to map from one to another.
 * instead of relying on changing names of primary key's from object 
 * to object, can always rely on object.getId().
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public interface Identifiable {

    /**
     * persistent id of the object.
     * @return
     */
    String getId();
    
    /**
     * sets the id of the object.
     * might be saved with null id and need to be set afterwards if using 
     * a sequence.
     * @return
     */
    void setId(String id);
}
