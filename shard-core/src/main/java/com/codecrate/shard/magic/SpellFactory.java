package com.codecrate.shard.magic;

import com.codecrate.shard.source.Source;

public interface SpellFactory {

    /**
     * 
     * @param name
     * @param summary
     * @param school
     * @param source
     * @return
     */
    Spell createSpell(String name, String summary, String school, Source source);
}
