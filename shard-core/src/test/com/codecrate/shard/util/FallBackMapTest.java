/*
 * Copyright (C) 2004 Digital River, Inc.
 * All rights reserved.
 */
package com.codecrate.shard.util;

import junit.framework.TestCase;

/**
 * @author <a href="mailto:rsonnek@digitalriver.com">Ryan Sonnek</a>
 */
public class FallBackMapTest extends TestCase {

    public void testPreviousKeyUsed() {
        FallBackMap map = new FallBackMap();
        map.put(new Integer(3), "last");
        map.put(new Integer(1), "first");
        
        assertEquals("first", map.get(new Integer(2)));
    }
}
