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
package com.codecrate.shard.search;

import java.io.Serializable;
import java.util.Iterator;

import net.sf.hibernate.CallbackException;
import net.sf.hibernate.Interceptor;
import net.sf.hibernate.type.Type;

public class ObjectIndexerInterceptor implements Interceptor {
    private final ObjectIndexer indexer; 

    public ObjectIndexerInterceptor(ObjectIndexer indexer) {
    	this.indexer = indexer;
    }

    public boolean onSave(Object entity, Serializable id,
            Object[] currentState, String[] propertyNames, Type[] types)
            throws CallbackException {
    	indexer.save(id, entity);

        return false;
    }

    public void onDelete(Object entity, Serializable id, Object[] state,
            String[] propertyNames, Type[] types) {
    	indexer.delete(id);
    }

    public boolean onLoad(Object entity, Serializable id, Object[] state,
            String[] propertyNames, Type[] types) {
        return false;
    }

    public boolean onFlushDirty(Object entity, Serializable id,
            Object[] currentState, Object[] previousState,
            String[] propertyNames, Type[] types) {
        return false;
    }

    public void preFlush(Iterator entities) throws CallbackException {
    }

    public void postFlush(Iterator entities) throws CallbackException {
    }

    public Boolean isUnsaved(Object entity) {
        return null;
    }

    public int[] findDirty(Object entity, Serializable id, Object[] state,
            Object[] currentState, String[] propertyNames, Type[] types) {
        return null;
    }

    public Object instantiate(Class entity, Serializable id)
            throws CallbackException {
        return null;
    }
}
