/*
 * Copyright (C) 2004 Digital River, Inc.
 * All rights reserved.
 */
package com.codecrate.shard.character;

/**
 * @author <a href="mailto:rsonnek@digitalriver.com">Ryan Sonnek</a>
 */
public interface Gender {
    static final Gender MALE = new Male();
    static final Gender FEMALE = new Female();
    
    
    static class Male implements Gender {}
    
    
    static class Female implements Gender {}
}
