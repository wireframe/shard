/*
 * Copyright (C) 2004 Digital River, Inc.
 * All rights reserved.
 */
package com.codecrate.shard.character;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author <a href="mailto:rsonnek@digitalriver.com">Ryan Sonnek</a>
 */
public class Party {
    private List characters = new ArrayList();
    private int encounterLevel;
    
    /**
     * average challange rating for the party.
     * @return
     */
    public int getChallengeRating() {
        int total = 0;
        Iterator it = characters.iterator(); 
        while (it.hasNext()) {
            DefaultPlayerCharacter character = (DefaultPlayerCharacter) it.next();
            total += character.getChallengeRating();
        }
        return (int)(total / characters.size());
    }
}
