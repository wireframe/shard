/*
 * Copyright (C) 2004 Digital River, Inc.
 * All rights reserved.
 */
package com.codecrate.shard.util;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Map that fallsback to previous entry if value is not found.
 * example map: {1=me, 3=you} if search for 2, get the value for 1.
 * 
 * @author <a href="mailto:rsonnek@digitalriver.com">Ryan Sonnek</a>
 */
public class FallBackMap implements SortedMap {
    private SortedMap map = new TreeMap();
    
    public Object get(Object key) {
        Object value = map.get(key);
        if (null != value) {
            return value;
        }
        
        Comparator comparator = new KeyComparator();
        Object previousKey = null;
        Iterator iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            Object possibleKey = iterator.next();
            
            if (0 < comparator.compare(key, possibleKey)) {
                previousKey = possibleKey;
            }
        }
    
        return map.get(previousKey);
    }
    
    public Object put(Object arg0, Object arg1) {
        return map.put(arg0, arg1);
    }
    public Collection values() {
        return map.values();
    }
    public void clear() {
        map.clear();
    }
    public boolean containsKey(Object arg0) {
        return map.containsKey(arg0);
    }
    public boolean containsValue(Object arg0) {
        return map.containsValue(arg0);
    }
    public Set entrySet() {
        return map.entrySet();
    }
    public boolean equals(Object arg0) {
        return map.equals(arg0);
    }
    public int hashCode() {
        return map.hashCode();
    }
    public boolean isEmpty() {
        return map.isEmpty();
    }
    public Set keySet() {
        return map.keySet();
    }
    public void putAll(Map arg0) {
        map.putAll(arg0);
    }
    public Object remove(Object arg0) {
        return map.remove(arg0);
    }
    public int size() {
        return map.size();
    }
    public String toString() {
        return map.toString();
    }

    public Object firstKey() {
        return map.firstKey();
    }

    public Object lastKey() {
        return map.lastKey();
    }

    public Comparator comparator() {
        return map.comparator();
    }

    public SortedMap headMap(Object arg0) {
        return map.headMap(arg0);
    }

    public SortedMap tailMap(Object arg0) {
        return map.tailMap(arg0);
    }

    public SortedMap subMap(Object arg0, Object arg1) {
        return map.subMap(arg0, arg1);
    }
    
    
    private static class KeyComparator implements Comparator {
        
        public int compare(Object arg0, Object arg1) {
            Comparable c0 = (Comparable) arg0;
            Comparable c1 = (Comparable) arg1;
            
            return c0.compareTo(c1);
        }
    }
}
