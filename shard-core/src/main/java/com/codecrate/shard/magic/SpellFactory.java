package com.codecrate.shard.magic;

import com.codecrate.shard.source.Source;

public interface SpellFactory {

    /**
     * 
     * @param name
     * @param summary
     * @param school
     * @param isDivine 
     * @param isArcane 
     * @param source
     * @return
     */
    Spell createSpell(String name, String summary, String school, boolean isArcane, boolean isDivine, Source source);
}
